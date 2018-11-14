package sharkindream.gui.gamescreen.infomation.lobby.createminion;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sharkindream.gui.gamescreen.infomation.lobby.selectdeck.SelectDeckController;
import sharkindream.gui.gamescreen.infomation.lobby.selectplayertype.SelectPlayerTypeController;
import sharkindream.network.client.Client;

public class SelectMinionScreenController {



	@FXML
	public void onMouseClickedSelect(MouseEvent e) {
		System.out.println("saved");
		//opensav
	}

	@FXML
	public void gonextpage() {

		FXMLLoader selectdeckfxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/gamescreen/infomation/lobby/selectdeck/SelectDeck.fxml"));

		AnchorPane deckselectscreen;
		try {
			deckselectscreen = (AnchorPane)selectdeckfxml.load();
			Client.switchGameInfoLobbymanu(deckselectscreen);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		((SelectDeckController)selectdeckfxml.getController()).initializeDeck(SelectPlayerTypeController.mystatus.deck.deckID);
	}

	@FXML
	public void returnpage() {

		FXMLLoader lobbyscreenfxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/gamescreen/infomation/lobby/selectplayertype/SelectPlayerType.fxml"));

		AnchorPane lobbyscreen;
		try {
			lobbyscreen = (AnchorPane)lobbyscreenfxml.load();
			Client.switchGameInfoLobbymanu(lobbyscreen);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
