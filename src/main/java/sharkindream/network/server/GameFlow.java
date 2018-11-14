package sharkindream.network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import sharkindream.actioncard.ActionCard;
import sharkindream.config.Setting;
import sharkindream.deck.Deck;
import sharkindream.network.event.OnUpdateGuestHandler;
import sharkindream.network.stream.playerstream.CharacterStatusStream;
import sharkindream.network.stream.playerstream.Guest;
import sharkindream.network.stream.playerstream.GuestStream;

public class GameFlow {

	private List<ActionCard> cardlist = new ArrayList<>();
	private CharacterStatusStream charastream = new CharacterStatusStream();


	static GuestStream gueststream = new GuestStream();
	static boolean isrecruit;
	OnUpdateGuestHandler listner;

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

	public void gameStart() {

		//カードの回収
		initcards();

		//ステータスの決定
		initCharacterStatus();
	}


	private void initcards() {
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
		int[] st = new int[6];

		for(Guest guest : gueststream.getList()) {
			st = calcstatus.calcStatuslist(guest.party.getMinion(0).getstatuslist(), guest.party.getMinion(0).getMinionClass() );

			System.out.println(st[0]);
		}

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
}
