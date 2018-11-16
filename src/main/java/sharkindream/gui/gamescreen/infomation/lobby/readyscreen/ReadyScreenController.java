package sharkindream.gui.gamescreen.infomation.lobby.readyscreen;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import sharkindream.gui.event.OnMoveScreenHandler;
import sharkindream.gui.gamescreen.infomation.lobby.selectdeck.SelectDeckController;
import sharkindream.network.client.Client;
import sharkindream.network.event.OnUpdateGuestHandler;
import sharkindream.network.stream.playerstream.Guest;

public class ReadyScreenController {

	private OnUpdateGuestHandler guestupdatelistener = null;
	private OnMoveScreenHandler movescreenlistener = null;


	@FXML
	public void returnpage() {


		setisready(false);
		onUpdateGuestInfo(Client.getMyStatus());
		FXMLLoader selectdeckfxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/gamescreen/infomation/lobby/selectdeck/SelectDeck.fxml"));

		AnchorPane deckselectscreen;
		try {
			deckselectscreen = (AnchorPane)selectdeckfxml.load();
			((SelectDeckController)selectdeckfxml.getController()).addMoveScreenListener(new OnMoveScreenHandler());
			switchInfoScreen(deckselectscreen );
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public void setisready(boolean flag) {
		Client.getMyStatus().isready = flag;
		onUpdateGuestInfo(Client.getMyStatus());
	}



	//リスナ登録
	public void addOnUpdateGuestInfoHandler(OnUpdateGuestHandler handler) {
		this.guestupdatelistener = handler;
	}

	public void removeOnUpdateGuestInfoHandler() {
		this.guestupdatelistener = null;
	}

	private void onUpdateGuestInfo(Guest guest) {
		if(guestupdatelistener != null) {
			guestupdatelistener.onUpdateGuestInfo(guest);
		}
	}


	//リスナ登録
	public void addMoveScreenListener(OnMoveScreenHandler handler) {
		this.movescreenlistener = handler;
	}

	public void removeMoveScreenListener() {
		this.movescreenlistener = null;
	}

	private void switchInfoScreen(AnchorPane infoscreen) {
		if(movescreenlistener != null) {
			movescreenlistener.onSwitchInfomationScreen(infoscreen);
		}
	}
}
