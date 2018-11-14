package sharkindream.gui.editscreen.deck.resource;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import sharkindream.gui.editscreen.deck.EditDeckController;

public class SubmanuResourceController {

	@FXML
	public AnchorPane iconphysical;
	@FXML
	public AnchorPane iconmagical;
	@FXML
	public AnchorPane icondebuff;
	@FXML
	public AnchorPane iconheal;
	private EditDeckController controller;

	@FXML
	void initialize() {
		try {

			AnchorPane physicalicon;
			physicalicon = (AnchorPane)FXMLLoader.load(getClass().getResource("/danzaigame/gui/cardmodel/resource/Physical.fxml"));
			iconphysical.getChildren().add(physicalicon);

			AnchorPane magicalicon;
			magicalicon = (AnchorPane)FXMLLoader.load(getClass().getResource("/danzaigame/gui/cardmodel/resource/Magical.fxml"));
			iconmagical.getChildren().add(magicalicon);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public void getController(EditDeckController controller_) {
		controller = controller_;
	}
}
