package sharkindream.gui.mainmanu.editmanu;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import sharkindream.gui.Main;
import sharkindream.gui.editscreen.deck.EditDeckController;

public class SubManuEditController {


	@FXML
	public void onEdit() {

		AnchorPane editscreen;
		try {
			FXMLLoader editfxml = new FXMLLoader(getClass().getResource("/danzaigame/gui/editscreen/deck/EditDeck.fxml"));
			editscreen = (AnchorPane)editfxml.load();
			((EditDeckController)editfxml.getController()).initpage();

			Platform.runLater( () -> 	Main.switchMainScreen(editscreen));
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	@FXML
	public void onOrganize() {

	}

	@FXML
	public void onCheck() {

	}

}
