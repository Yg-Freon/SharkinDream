package sharkindream.gui.deck.type;

import javafx.fxml.FXML;
import sharkindream.gamecharacter.Type;
import sharkindream.gui.deck.EditDeckController;

public class SubmenuTypeController {

	EditDeckController controller;


	public void setController(EditDeckController controller_ ) {
		this.controller = controller_;
	}

	@FXML
	public void onClickFire() {
		controller.updatetype(Type.Fire);
		controller.closesubmanu();
	}

	@FXML
	public void onClickWater() {
		System.out.println("water");
		controller.updatetype(Type.Water);
		controller.closesubmanu();
	}

	@FXML
	public void onClickLeaf() {
		controller.updatetype(Type.Leaf);
		controller.closesubmanu();
	}

	@FXML
	public void onClickLight() {
		controller.updatetype(Type.Light);
		controller.closesubmanu();
	}

	@FXML
	public void onClickDark() {
		controller.updatetype(Type.Dark);
		controller.closesubmanu();
	}
}
