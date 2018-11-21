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
import sharkindream.network.event.OnUpdateGuestHandler;
import sharkindream.network.event.OnUpdatePlayerHandler;
import sharkindream.network.stream.playerstream.Guest;
import sharkindream.network.stream.playerstream.GuestStream;
import sharkindream.network.stream.playerstream.PlayCharacter;
import sharkindream.network.stream.playerstream.PlayerStatus;

public class GameFlow {

	private List<ActionCard> cardlist = new ArrayList<>();
	private Map<Guest, PlayerStatus> playerStatuslist = new HashMap<>();
	private List<PlayerStatus> playerlist = new ArrayList<>();


	static GuestStream gueststream = new GuestStream();
	static boolean isrecruit;
	OnUpdateGuestHandler listner;
	private OnUpdatePlayerHandler handler;

	static List<SrvThread> clientlist = new ArrayList<>();


	public void onlobbymenu(ServerSocket sSocket) {
		isrecruit = true;
		while(isrecruit) {
			Socket socket = null;
			try {
				sSocket.setSoTimeout(500);
				socket = sSocket.accept();
				SrvThread client = new SrvThread(socket, gueststream);
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

	}

	//手札の補充
	public void inithand() {

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

		for(Guest guest : gueststream.getList()) {
			Deck deck;
			deck = gueststream.getDeckByID(guest.playerID);
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

		for(Guest guest : gueststream.getList()) {
			PlayCharacter player = new PlayCharacter();
			PlayCharacter minion0 = new PlayCharacter();
			PlayCharacter minion1 = new PlayCharacter();

			int[] pst = {1, 300, 1, 300, 400, 1};
			player.setStatus(pst);
			minion0.setStatus(calcstatus.calcStatuslist(guest.party.getMinion(0)));
			minion1.setStatus(calcstatus.calcStatuslist(guest.party.getMinion(1)));

			PlayCharacter[] party = {minion0, minion1};

			playerlist.add(new PlayerStatus(player, party));
			playerStatuslist.put(guest, new PlayerStatus(player, party));
		}
	}

	public boolean playGame() {
		//1ターンの流れ
		List<Guest> glist =  gueststream.getList();
		Collections.shuffle(glist);
		int loosercount = 0;
		for(Guest guest : glist) {
			PlayerStatus player = playerStatuslist.get(guest);
			if(player.canaction()) {


				//攻撃プレイヤーの行動

				onAttackAction(guest, player);
				//防御プレイヤーの行動
				//手札補充


			}else {
				++loosercount;
			}
			onUpdateMemberStatus();
		}
		if(loosercount >= playerStatuslist.size() -1) {
			return false;
		}
		return true;
	}

	private void onAttackAction(Guest id, PlayerStatus player) {

	}


	static void initmember() {
		gueststream.clearAllReady();

	}

	static public void setIsrecruit(boolean flag) {
		isrecruit = flag;

	}

	static public List<SrvThread> getClientMember() {
		return clientlist;

	}

	static public void setUpdateHandler(OnUpdateGuestHandler handler) {
		gueststream.addUpdateGuestListner(handler);
	}



	//リスナ登録
	public void addUpdatePlayerHandler(OnUpdatePlayerHandler handler) {
		this.handler = handler;
	}

	public void onUpdateMemberStatus() {
		if(handler != null) {
			handler.OnInitMemberStatus(playerlist);
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
