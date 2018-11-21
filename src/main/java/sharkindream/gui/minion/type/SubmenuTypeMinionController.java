package sharkindream.gui.minion.type;

import javafx.fxml.FXML;
import sharkindream.gamecharacter.Type;
import sharkindream.gui.minion.CreateMinionController;

public class SubmenuTypeMinionController {
	CreateMinionController controller;


	public void setController(CreateMinionController controller_ ) {
		this.controller = controller_;
	}

	@FXML
	private void onClickFire() {
		controller.updatetype(Type.Fire);
		controller.switchclasspane();

	}

	@FXML
	private void onClickWater() {
		controller.updatetype(Type.Water);
		controller.switchclasspane();
	}

	@FXML
	private void onClickLeaf() {
		controller.updatetype(Type.Leaf);
		controller.switchclasspane();
	}

	@FXML
	private void onClickLight() {
		controller.updatetype(Type.Light);
		controller.switchclasspane();
	}

	@FXML
	private void onClickDark() {
		controller.updatetype(Type.Dark);
		controller.switchclasspane();
	}
}
