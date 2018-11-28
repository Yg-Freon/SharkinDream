package sharkindream.gui.gamescreen.infomation.lobby.selectdeck;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import sharkindream.actioncard.ActionCard;
import sharkindream.config.Setting;
import sharkindream.deck.Deck;
import sharkindream.gui.event.OnMoveScreenHandler;
import sharkindream.gui.gamescreen.infomation.lobby.readyscreen.ReadyScreenController;
import sharkindream.gui.gamescreen.infomation.lobby.selectminion.SelectMinionScreenController;
import sharkindream.network.event.OnUpdateGuestHandler;

public class SelectDeckController {
	private int decknum;

	@FXML
	private Text deckidtext;

	@FXML
	private AnchorPane card0;
	@FXML
	private AnchorPane card1;
	@FXML
	private AnchorPane card2;
	@FXML
	private AnchorPane card3;


	private Deck deck;

	Deck[] decklist = new Deck[Setting.MaxDecknum];

	private OnMoveScreenHandler listener;


	@FXML
	void initialize() {
		decknum = 0;
		updateDeckInfo(decknum);

	}

	@FXML
	public void returnpage() {
		FXMLLoader minioncreatefxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/gamescreen/infomation/lobby/selectminion/SelectMinionScreen.fxml"));

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
		updateDeckInfo(decknum);
	}

	@FXML
	public void onClickPrevious() {
		decknum -=1;
		if(decknum < 0) decknum = Setting.MaxDecknum-1;
		updateDeckInfo(decknum);
	}

	private void updateDeckInfo(int deckid){
		decknum = deckid;

		deck = (new Deck()).readDeckfromjson(decknum);

		Map<AnchorPane, ActionCard> deckmap = new HashMap<>();

		deckmap.put(card0, deck.cardlist[0]);
		deckmap.put(card1, deck.cardlist[1]);
		deckmap.put(card2, deck.cardlist[2]);
		deckmap.put(card3, deck.cardlist[3]);

		for(Map.Entry<AnchorPane, ActionCard> deckentry: deckmap.entrySet()) {
			((Label)((AnchorPane)((AnchorPane)deckentry.getKey().getChildren().get(0)).getChildren().get(0)).getChildren().get(0)).setText(String.valueOf( deckentry.getValue().power.getshowname()));
			((Label)((AnchorPane)((AnchorPane)deckentry.getKey().getChildren().get(0)).getChildren().get(0)).getChildren().get(0)).setTextFill(Paint.valueOf(deckentry.getValue().type.getColor()));
			((AnchorPane)((AnchorPane)((AnchorPane)deckentry.getKey().getChildren().get(0)).getChildren().get(0)).getChildren().get(1)).getChildren().clear();
			((AnchorPane)((AnchorPane)((AnchorPane)deckentry.getKey().getChildren().get(0)).getChildren().get(0)).getChildren().get(1)).getChildren().add(deckentry.getValue().resource.getIcon(Paint.valueOf(deckentry.getValue().type.getColor())));
			((Text)((AnchorPane)((AnchorPane)deckentry.getKey().getChildren().get(0)).getChildren().get(1) ).getChildren().get(0)).setText(deckentry.getValue().debufflist[0].getshowname() + "/" + deckentry.getValue().debufflist[1].getshowname() + "/" + deckentry.getValue().debufflist[2].getshowname() + "/" + deckentry.getValue().debufflist[3].getshowname());
			((Text)((AnchorPane)((AnchorPane)deckentry.getKey().getChildren().get(0)).getChildren().get(1) ).getChildren().get(0)).setFill(Paint.valueOf(deckentry.getValue().type.getColor()));
			((Text)((AnchorPane)((AnchorPane)deckentry.getKey().getChildren().get(0)).getChildren().get(1)).getChildren().get(1)).setText(deckentry.getValue().accuracy.getaccuracy() + "/" + deckentry.getValue().luck.getluck());
			((Text)((AnchorPane)((AnchorPane)deckentry.getKey().getChildren().get(0)).getChildren().get(1)).getChildren().get(1)).setFill(Paint.valueOf(deckentry.getValue().type.getColor()));
			((Label)((TilePane)((AnchorPane)deckentry.getKey().getChildren().get(0)).getChildren().get(2)).getChildren().get(0)).setText(String.valueOf(deckentry.getValue().power.getpower()));
			((Label)((TilePane)((AnchorPane)deckentry.getKey().getChildren().get(0)).getChildren().get(2)).getChildren().get(0)).setTextFill(Paint.valueOf(deckentry.getValue().type.getColor()));
		}

		deckidtext.setText(String.valueOf(decknum));

    }


	@FXML
	private void goreadypage() {
		
		

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
