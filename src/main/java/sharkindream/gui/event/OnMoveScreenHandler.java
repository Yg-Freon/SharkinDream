package sharkindream.gui.event;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import sharkindream.gui.Main;
import sharkindream.network.client.ClientGamePlayFlow;

public class OnMoveScreenHandler implements OnMoveScreenIF{



	@Override
	public void onMoveMainManuScreen() {
		AnchorPane editscreen ;

		FXMLLoader editfxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/mainmanu/MainManu.fxml"));
		try {
			editscreen = (AnchorPane)editfxml.load();
			//((EditDeckController)editfxml.getController()).initpage();
			Main.switchMainScreen(editscreen);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	@Override
	public void onMoveClientGameScreen(ClientGamePlayFlow cflow) {

		 FXMLLoader gamescenefxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/gamescreen/NewGameScreen.fxml"));
		 AnchorPane gamescene;

		 try {
			gamescene = (AnchorPane)gamescenefxml.load();
			/*
			 * AnchorPane gameinfolobbyscreen = (AnchorPane)FXMLLoader.load(getClass().getResource("/sharkindream/gui/gamescreen/infomation/InfomationScreen.fxml"));
			AnchorPane gameinfo = (AnchorPane)FXMLLoader.load(getClass().getResource("/sharkindream/gui/gamescreen/infomation/lobby/selectplayertype/LobbyScreen.fxml"));

			((AnchorPane)gameinfo.getChildren().get(0)).getChildren().clear();
			((AnchorPane)gameinfo.getChildren().get(0)).getChildren().add(gameinfolobbyscreen);

			AnchorPane.setTopAnchor(gameinfolobbyscreen, 0d);
			AnchorPane.setBottomAnchor(gameinfolobbyscreen, 0d);
			AnchorPane.setLeftAnchor(gameinfolobbyscreen, 0d);
			AnchorPane.setRightAnchor(gameinfolobbyscreen, 0d);

			((AnchorPane)((BorderPane)gamescene.getChildren().get(0)).getChildren().get(1)).getChildren().clear();
			((AnchorPane)((BorderPane)gamescene.getChildren().get(0)).getChildren().get(1)).getChildren().add(gameinfolobbyscreen);

			AnchorPane.setTopAnchor(gameinfo, 0d);
			AnchorPane.setBottomAnchor(gameinfo, 0d);
			AnchorPane.setLeftAnchor(gameinfo, 0d);
			AnchorPane.setRightAnchor(gameinfo, 0d);

			AnchorPane lobbyscreen = (AnchorPane)FXMLLoader.load(getClass().getResource("/danzaigame/gui/gamescreen/infomation/lobby/selectplayertype/LobbyScreen.fxml"));

			((AnchorPane)gameinfolobbyscreen.getChildren().get(0)).getChildren().clear();
			((AnchorPane)gameinfolobbyscreen.getChildren().get(0)).getChildren().add(lobbyscreen);

			AnchorPane.setTopAnchor(lobbyscreen, 0d);
			AnchorPane.setBottomAnchor(lobbyscreen, 0d);
			AnchorPane.setLeftAnchor(lobbyscreen, 0d);
			AnchorPane.setRightAnchor(lobbyscreen, 0d);

			*/

			cflow.setGameController(gamescenefxml.getController());
			Platform.runLater( () -> Main.switchMainScreen(gamescene));

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}



	}

}
