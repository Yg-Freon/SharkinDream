package sharkindream.gui.gamescreen.infomation.lobby.selectdeck;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import sharkindream.config.Setting;
import sharkindream.deck.Deck;
import sharkindream.gui.event.OnMoveScreenHandler;
import sharkindream.gui.gamescreen.infomation.lobby.createminion.SelectMinionScreenController;
import sharkindream.gui.gamescreen.infomation.lobby.readyscreen.ReadyScreenController;
import sharkindream.network.client.Client;
import sharkindream.network.event.OnUpdateGuestHandler;

public class SelectDeckController {
	private int decknum;

	@FXML
	public Text subtitle;

	private Deck deck;

	Deck[] decklist = new Deck[Setting.MaxDecknum];

	private OnMoveScreenHandler listener;


	@FXML
	void initialize() {
		initializeDeck(Client.getMyStatus().deck.getDeckID());
	}

	@FXML
	public void returnpage() {
		FXMLLoader minioncreatefxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/gamescreen/infomation/lobby/createminion/SelectMinionScreen.fxml"));

		AnchorPane minioncreatescreen;
		try {
			minioncreatescreen = (AnchorPane)minioncreatefxml.load();
			((SelectMinionScreenController)minioncreatefxml.getController()).addMoveScreenListener(new OnMoveScreenHandler());
			switchInfoScreen(minioncreatescreen);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	@FXML
	public void onClickNext() {
		decknum += 1;
		if(decknum > Setting.MaxDecknum-1) decknum = 0;
		updatesubtitle();
		initializeDeck(decknum);

	}

	@FXML
	public void onClickPrevious() {
		decknum -=1;
		if(decknum < 0) decknum = Setting.MaxDecknum-1;
		updatesubtitle();
		initializeDeck(decknum);
	}

	private void initializeDeck(int deckid){
		decknum = deckid;

		Client.getMyStatus().deck.readDeckfromjson(decknum);
		updatesubtitle();

    }



	private void updatesubtitle() {
		subtitle.setText("Deck No." + decknum);
	}

	@FXML
	private void goreadypage() {
		System.out.println("clicked");

		FXMLLoader readyscreenfxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/gamescreen/infomation/lobby/readyscreen/ReadyScreen.fxml"));

		AnchorPane readyscreen;
		try {
			readyscreen = (AnchorPane)readyscreenfxml.load();
			((ReadyScreenController)readyscreenfxml.getController()).addOnUpdateGuestInfoHandler(new OnUpdateGuestHandler());
			((ReadyScreenController)readyscreenfxml.getController()).addMoveScreenListener(new OnMoveScreenHandler());
			((ReadyScreenController)readyscreenfxml.getController()).setisready(true);
			switchInfoScreen(readyscreen);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	//リスナ登録
	public void addMoveScreenListener(OnMoveScreenHandler handler) {
		this.listener = handler;
	}

	public void removeMoveScreenListener() {
		this.listener = null;
	}

	private void switchInfoScreen(AnchorPane infoscreen) {
		if(listener != null) {
			listener.onSwitchInfomationScreen(infoscreen);
		}
	}

}
