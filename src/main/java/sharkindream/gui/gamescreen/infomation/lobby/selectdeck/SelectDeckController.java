package sharkindream.gui.gamescreen.infomation.lobby.selectdeck;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import sharkindream.config.Setting;
import sharkindream.deck.Deck;
import sharkindream.gui.gamescreen.infomation.lobby.readyscreen.ReadyScreenController;
import sharkindream.gui.gamescreen.infomation.lobby.selectplayertype.SelectPlayerTypeController;
import sharkindream.network.client.Client;

public class SelectDeckController {
	private int decknum;

	@FXML
	public Text subtitle;

	private Deck deck;

	Deck[] decklist = new Deck[Setting.MaxDecknum];



	@FXML
	public void returnpage() {
		FXMLLoader minioncreatefxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/gamescreen/infomation/lobby/createminion/SelectMinionScreen.fxml"));

		AnchorPane minioncreatescreen;
		try {
			minioncreatescreen = (AnchorPane)minioncreatefxml.load();
			Client.switchGameInfoLobbymanu(minioncreatescreen);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//((CreateMinionScreenController)minioncreatefxml.getController()).initializeChoiceBox(2);
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



	private void updatesubtitle() {
		subtitle.setText("Deck No." + decknum);
	}


	public  void initializeDeck(int deckid){
		decknum = deckid;

		SelectPlayerTypeController.mystatus.deck.readDeckfromjson(decknum);
		updatesubtitle();

    }

	@FXML
	private void goreadypage() {
		System.out.println("clicked");

		FXMLLoader readyscreenfxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/gamescreen/infomation/lobby/readyscreen/ReadyScreen.fxml"));

		AnchorPane readyscreen;
		try {
			readyscreen = (AnchorPane)readyscreenfxml.load();
			Client.switchGameInfoLobbymanu(readyscreen);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}


		((ReadyScreenController)readyscreenfxml.getController()).setisready(true);
		((ReadyScreenController)readyscreenfxml.getController()).initreadyscreen();

	}


}
