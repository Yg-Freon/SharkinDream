package sharkindream.gui.gamescreen.infomation.lobby.createminion;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sharkindream.gui.event.OnMoveScreenHandler;
import sharkindream.gui.gamescreen.infomation.lobby.selectdeck.SelectDeckController;
import sharkindream.gui.gamescreen.infomation.lobby.selectplayertype.SelectPlayerTypeController;

public class SelectMinionScreenController {



	private OnMoveScreenHandler listener;

	@FXML
	public void onMouseClickedSelect(MouseEvent e) {
		System.out.println("saved");
		//opensav
	}

	@FXML
	public void gonextpage() {

		FXMLLoader selectdeckfxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/gamescreen/infomation/lobby/selectdeck/SelectDeck.fxml"));
		AnchorPane deckscreen;
		try {
			deckscreen = (AnchorPane)selectdeckfxml.load();
			((SelectDeckController)selectdeckfxml.getController()).addMoveScreenListener(new OnMoveScreenHandler());
			switchInfoScreen(deckscreen);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	@FXML
	public void returnpage() {

		FXMLLoader playertypescreenfxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/gamescreen/infomation/lobby/selectplayertype/SelectPlayerType.fxml"));
		AnchorPane infoscreen;
		try {
			infoscreen = playertypescreenfxml.load();
			((SelectPlayerTypeController)playertypescreenfxml.getController()).addMoveScreenListener(new OnMoveScreenHandler());
			switchInfoScreen(infoscreen);
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
