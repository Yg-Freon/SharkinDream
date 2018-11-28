package sharkindream.network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import sharkindream.actioncard.ActionCard;
import sharkindream.config.Setting;
import sharkindream.deck.Deck;
import sharkindream.gamecharacter.GameCharacter.CharacterClass;
import sharkindream.network.client.ClientGamePlayFlow.AttackAction;
import sharkindream.network.event.OnUpdateGuestHandler;
import sharkindream.network.event.OnUpdatePlayerHandler;
import sharkindream.network.stream.playerstream.Guest;
import sharkindream.network.stream.playerstream.GuestListData;
import sharkindream.network.stream.playerstream.PlayCharacter;
import sharkindream.network.stream.playerstream.PlayerStatus;

public class GameFlow {

	private List<ActionCard> cardlist = new ArrayList<>();
	private Map<Guest, PlayerStatus> playerStatuslist = new HashMap<>();


	private GuestListData guestlist = new GuestListData();
	static private boolean isrecruit;
	static private AttackAction attackedAction = AttackAction.None;
	OnUpdateGuestHandler listner;
	private OnUpdatePlayerHandler handler;
	static private Integer attackCard;
	static private Integer attackMinion;
	static private Guest target;

	static List<SrvThread> clientlist = new ArrayList<>();


	public void onlobbymenu(ServerSocket sSocket) {

		isrecruit = true;
		while(isrecruit) {
			Socket socket = null;
			try {
				sSocket.setSoTimeout(500);
				socket = sSocket.accept();
				socket.setSoTimeout(0);
				SrvThread client = new SrvThread(socket, guestlist);
				client.addUpdatePlayerHandler(new OnUpdatePlayerHandler());
				client.start();
				clientlist.add(client);
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				//e.printStackTrace();
			}

		}
		//データ保存
	}

	public void gameInit() {
		playerStatuslist.clear();

		//カードの回収
		initcards();

		//ステータスの決定
		initCharacterStatus();

		//手札配布
		inithand();

		onInitPlayerStatuses();

	}

	//手札の補充
	private  void inithand() {

		int i = 0;
		while(i < 3) {
			for(Entry<Guest, PlayerStatus> playerent : playerStatuslist.entrySet()) {
				playerent.getValue().drawHand(cardlist);
			}
		++i;
		}
	}


	private void initcards() {
		//カードを回収してデッキにする

		for(Guest guest : guestlist.getlist()) {
			Deck deck;
			deck = guest.deck;
			for(ActionCard card : deck.cardlist) {
				cardlist.add(card);
			}
		}

		for(ActionCard card : Setting.basecards) {
			cardlist.add(card);
		}
	}

	private void initCharacterStatus() {

		CalcCharaStatus calcstatus  = new CalcCharaStatus();

		for(Guest guest : guestlist.getlist()) {
			PlayCharacter player = new PlayCharacter();
			PlayCharacter minion0 = new PlayCharacter();
			PlayCharacter minion1 = new PlayCharacter();


			int[] pst = {1, 200, 1, 200, 400, 1};

			player.setStatus(pst);
			minion0.setStatus(calcstatus.calcStatuslist(guest.party.getMinion(0)));
			minion1.setStatus(calcstatus.calcStatuslist(guest.party.getMinion(1)));

			player.setType(guest.type);
			minion0.setType(guest.party.getMinion(0).getMinionType());
			minion1.setType(guest.party.getMinion(1).getMinionType());


			player.setCharacterClass(CharacterClass.Player);
			minion0.setCharacterClass(guest.party.getMinion(0).getMinionClass());
			minion1.setCharacterClass(guest.party.getMinion(1).getMinionClass());

			PlayCharacter[] party = {minion0, minion1};

			playerStatuslist.put(guest, new PlayerStatus(player, party));
		}
	}

	public boolean playGame() {
		//1ターンの流れ
		List<Guest> glist =  guestlist.getlist();
		Collections.shuffle(glist);
		int loosercount = 0;
		for(Guest guest : glist) {
			PlayerStatus player = playerStatuslist.get(guest);
			if(player.canaction()) {

				setAttackedFlag(AttackAction.None);
				initActionAttack();

				//攻撃プレイヤーの行動
					//攻撃プレイヤーを指定
				//手札補充
				onAttackAction(guest, player);
				while(true) {
					switch(attackedAction) {
					case Attack:
						while(true) {
							if(attackCard != null && attackMinion != null && target != null) {

								//防御プレイヤーの行動

								break;
							}
						}
						break;

					case Draw:
						break;

					case Rest:
						break;

					default:
						continue;
					}
					break;
				}

				//防御プレイヤーの行動

				//ターン終了


			}else {
				++loosercount;
			}
		}
		//次のターン


		if(loosercount >= playerStatuslist.size() -1) {
			System.out.println("gameflow:win");
			return false;
		}
		return true;
	}

	private static void initActionAttack() {
		attackCard = null;
		attackMinion = null;
		target = null;
	}

	public static void setActionAttack(int attackCard_, int attackMinion_, Guest target_) {
		attackCard.valueOf(attackCard_);
		attackMinion.valueOf(attackMinion_);
		target = target_;
	}

	private void onAttackAction(Guest id, PlayerStatus player) {
		//攻撃プレイヤーを通知
		onAttackTurn(id, player);
	}


	static void initmember() {
		GuestListData.clearAllReady();

	}

	static public void setIsrecruit(boolean flag) {
		isrecruit = flag;

	}

	static public boolean getIsrecruit() {
		return isrecruit;
	}

	static public List<SrvThread> getClientMember() {
		return clientlist;

	}

	static public void setAttackedFlag(AttackAction actionResult) {
		attackedAction = actionResult;
	}



	//リスナ登録
	public void addUpdatePlayerHandler(OnUpdatePlayerHandler handler) {
		this.handler = handler;
	}

	public void onInitPlayerStatuses() {
		if(handler != null) {
			handler.OnInitMemberStatus(playerStatuslist);
		}
	}

	public void onAttackTurn(Guest id, PlayerStatus player) {
		if(handler != null) {
			handler.onAttackTurn(id, player);
		}
	}

	public void onDefenceTurn() {
		if(handler != null) {
			handler.onDefenceTurn(playerStatuslist);
		}
	}
}
