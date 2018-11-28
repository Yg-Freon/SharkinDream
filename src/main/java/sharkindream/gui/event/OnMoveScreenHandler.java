package sharkindream.gui.event;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import sharkindream.Main;
import sharkindream.gui.deck.EditDeckController;
import sharkindream.gui.gamescreen.GameScreenController;
import sharkindream.gui.mainmenu.MainMenuController;
import sharkindream.gui.minion.CreateMinionController;
import sharkindream.gui.title.TitleController;
import sharkindream.network.client.ClientGamePlayFlow;

public class OnMoveScreenHandler implements OnMoveScreenIF{


	@Override
	public void onMoveTitle() {

		FXMLLoader titlefxml =new FXMLLoader(getClass().getResource("/sharkindream/gui/title/Title.fxml"));
		AnchorPane title;
		try {
			title = (AnchorPane)titlefxml.load();
			((TitleController)titlefxml.getController()).addMoveScreenListener(this);
			((TitleController)titlefxml.getController()).readyanimation();
			Main.switchMainScreen(title);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}


	@Override
	public void onMoveMainManuScreen() {
		AnchorPane editscreen ;

		FXMLLoader editfxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/mainmenu/MainMenu.fxml"));
		try {
			editscreen = (AnchorPane)editfxml.load();
			((MainMenuController)editfxml.getController()).addMoveScreenListener(this);
			Main.switchMainScreen(editscreen);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}


	@Override
	public void onMoveEditDeckScreen() {
		AnchorPane editscreen ;

		FXMLLoader editfxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/deck/EditDeck.fxml"));
		try {
			editscreen = (AnchorPane)editfxml.load();
			((EditDeckController)editfxml.getController()).addMoveScreenListener(this);
			Main.switchMainScreen(editscreen);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}


	@Override
	public void onMoveCreateMinionScreen() {
		AnchorPane createscreen ;

		FXMLLoader createfxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/minion/CreateMinion.fxml"));
		try {
			createscreen = (AnchorPane)createfxml.load();
			((CreateMinionController)createfxml.getController()).addMoveScreenListener(this);
			Main.switchMainScreen(createscreen);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}


	@Override
	public void onMoveClientGameScreen(ClientGamePlayFlow cflow) {

		 FXMLLoader gamescenefxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/gamescreen/GameScreen.fxml"));
		 AnchorPane gamescene;

		 try {
			gamescene = (AnchorPane)gamescenefxml.load();
			cflow.setGameController(gamescenefxml.getController());
			Platform.runLater( () -> Main.switchMainScreen(gamescene));

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}


	@Override
	public void onSwitchInfomationScreen(AnchorPane infoscreen) {
		GameScreenController.switchInfoScreen(infoscreen);

	}


}
