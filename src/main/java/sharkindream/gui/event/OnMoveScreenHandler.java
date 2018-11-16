package sharkindream.gui.event;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import sharkindream.Main;
import sharkindream.gui.gamescreen.GameScreenController;
import sharkindream.gui.mainmanu.MainManuController;
import sharkindream.gui.title.TitleController;
import sharkindream.network.client.ClientGamePlayFlow;

public class OnMoveScreenHandler implements OnMoveScreenIF{


	@Override
	public void onMoveTitle() {

		FXMLLoader titlefxml =new FXMLLoader(getClass().getResource("/sharkindream/gui/title/Title.fxml"));
		AnchorPane title;
		try {
			title = (AnchorPane)titlefxml.load();
			((TitleController)titlefxml.getController()).addMoveScreenListener(new OnMoveScreenHandler());
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

		FXMLLoader editfxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/mainmanu/MainManu.fxml"));
		try {
			editscreen = (AnchorPane)editfxml.load();
			((MainManuController)editfxml.getController()).addMoveScreenListener(this);
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
			Main.switchMainScreen(editscreen);
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
