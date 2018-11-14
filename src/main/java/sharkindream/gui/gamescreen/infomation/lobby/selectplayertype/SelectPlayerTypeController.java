package sharkindream.gui.gamescreen.infomation.lobby.selectplayertype;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.shape.SVGPath;
import sharkindream.gamecharacter.Type;
import sharkindream.network.client.Client;
import sharkindream.network.stream.playerstream.Guest;

public class SelectPlayerTypeController {


	public static Guest mystatus = new Guest();

	public static HBox minioninfoes;

	@FXML
	public SVGPath svg;



	/*
	@FXML
	public void onClick() {

		AnchorPane gameinfoplayscreen;
		try {
			gameinfoplayscreen = (AnchorPane)FXMLLoader.load(getClass().getResource("/danzaigame/gui/gamescreen/infomation/gameplay/GamePlayScreen.fxml"));
			minioninfoes = (HBox)((VBox)gameinfoplayscreen.getChildren().get(0)).getChildren().get(1);

			AnchorPane minioninfo0 = (AnchorPane)FXMLLoader.load(getClass().getResource("/danzaigame/gui/gamescreen/infomation/gameplay/minioninfo/MinionInfo.fxml"));
			AnchorPane minioninfo1 = (AnchorPane)FXMLLoader.load(getClass().getResource("/danzaigame/gui/gamescreen/infomation/gameplay/minioninfo/MinionInfo.fxml"));
			addMinionInfo(minioninfo0, minioninfo1);

			Client.switchGameInfoscreen(gameinfoplayscreen);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	*/

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
		mystatus.type = Type.Fire;
		nextScreen();
	}

	@FXML
	public void onClickWater() {
		mystatus.type = Type.Water;
		nextScreen();
	}

	@FXML
	public void onClickLeaf() {
		mystatus.type = Type.Leaf;
		nextScreen();
	}

	@FXML
	public void onClickLight() {
		mystatus.type = Type.Light;
		nextScreen();
	}

	@FXML
	public void onClickDark() {
		mystatus.type = Type.Dark;
		nextScreen();
	}



	private void nextScreen() {

		FXMLLoader minioncreatefxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/gamescreen/infomation/lobby/createminion/SelectMinionScreen.fxml"));

		AnchorPane minioncreatescreen;
		try {
			minioncreatescreen = (AnchorPane)minioncreatefxml.load();
			Client.switchGameInfoLobbymanu(minioncreatescreen);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		//((CreateMinionScreenController)minioncreatefxml.getController()).initializeChoiceBox(1);
	}



}
