package sharkindream.gui.gamescreen.infomation.lobby.readyscreen;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import sharkindream.gui.gamescreen.infomation.lobby.selectdeck.SelectDeckController;
import sharkindream.gui.gamescreen.infomation.lobby.selectplayertype.SelectPlayerTypeController;
import sharkindream.network.client.Client;
import sharkindream.network.event.OnUpdateGuestHandler;

public class ReadyScreenController {

	OnUpdateGuestHandler handler = new OnUpdateGuestHandler();

	public void initreadyscreen() {
			handler.onUpdateGuestInfo(SelectPlayerTypeController.mystatus);
	}


	@FXML
	public void returnpage() {


		setisready(false);
		handler.onUpdateGuestInfo(SelectPlayerTypeController.mystatus);
		FXMLLoader selectdeckfxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/gamescreen/infomation/lobby/selectdeck/SelectDeck.fxml"));

		AnchorPane deckselectscreen;
		try {
			deckselectscreen = (AnchorPane)selectdeckfxml.load();
			Client.switchGameInfoLobbymanu(deckselectscreen);
			((SelectDeckController)selectdeckfxml.getController()).initializeDeck(SelectPlayerTypeController.mystatus.deck.deckID);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public void setisready(boolean flag) {
		SelectPlayerTypeController.mystatus.isready = flag;
	}
}
