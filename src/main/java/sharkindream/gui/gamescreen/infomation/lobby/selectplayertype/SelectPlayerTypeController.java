package sharkindream.gui.gamescreen.infomation.lobby.selectplayertype;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import sharkindream.gamecharacter.Type;
import sharkindream.gui.event.OnMoveScreenHandler;
import sharkindream.gui.gamescreen.infomation.lobby.createminion.SelectMinionScreenController;
import sharkindream.network.client.Client;

public class SelectPlayerTypeController {

	@FXML
	private static HBox minioninfoes;

	@FXML
	public SVGPath svg;

	private OnMoveScreenHandler listener;




	@FXML
	public void onClick() {

		AnchorPane gameinfoplayscreen;
		try {
			gameinfoplayscreen = (AnchorPane)FXMLLoader.load(getClass().getResource("/danzaigame/gui/gamescreen/infomation/gameplay/GamePlayScreen.fxml"));
			minioninfoes = (HBox)((VBox)gameinfoplayscreen.getChildren().get(0)).getChildren().get(1);

			AnchorPane minioninfo0 = (AnchorPane)FXMLLoader.load(getClass().getResource("/danzaigame/gui/gamescreen/infomation/gameplay/minioninfo/MinionInfo.fxml"));
			AnchorPane minioninfo1 = (AnchorPane)FXMLLoader.load(getClass().getResource("/danzaigame/gui/gamescreen/infomation/gameplay/minioninfo/MinionInfo.fxml"));
			addMinionInfo(minioninfo0, minioninfo1);

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}


	public static void addMinionInfo(AnchorPane minioninfo0, AnchorPane minioninfo1) {
		minioninfoes.getChildren().clear();
		minioninfoes.getChildren().add(minioninfo0);
		minioninfoes.getChildren().add(minioninfo1);
		setScreenSetting(minioninfo0);
		setScreenSetting(minioninfo1);
	}

	private static void setScreenSetting(AnchorPane screen) {
		HBox.setHgrow(screen, Priority.ALWAYS);
	}

	@FXML
	public void onClickFire() {
		Client.getMyStatus().type = Type.Fire;
		nextScreen();
	}

	@FXML
	public void onClickWater() {
		Client.getMyStatus().type = Type.Water;
		nextScreen();
	}

	@FXML
	public void onClickLeaf() {
		Client.getMyStatus().type = Type.Leaf;
		nextScreen();
	}

	@FXML
	public void onClickLight() {
		Client.getMyStatus().type = Type.Light;
		nextScreen();
	}

	@FXML
	public void onClickDark() {
		Client.getMyStatus().type = Type.Dark;
		nextScreen();
	}



	private void nextScreen() {

		FXMLLoader minioncreatefxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/gamescreen/infomation/lobby/createminion/SelectMinionScreen.fxml"));
		AnchorPane infoscreen;
		try {
			infoscreen = minioncreatefxml.load();
			((SelectMinionScreenController)minioncreatefxml.getController()).addMoveScreenListener(new OnMoveScreenHandler());
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
