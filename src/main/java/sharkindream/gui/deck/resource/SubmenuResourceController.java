package sharkindream.gui.deck.resource;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import sharkindream.actioncard.ActionCard.Resource;
import sharkindream.actioncard.resource.Debuff;
import sharkindream.actioncard.resource.Heal;
import sharkindream.actioncard.resource.Magical;
import sharkindream.actioncard.resource.Physical;
import sharkindream.gui.deck.EditDeckController;

public class SubmenuResourceController {

	@FXML
	private AnchorPane iconphysical;
	@FXML
	private AnchorPane iconmagical;
	@FXML
	private AnchorPane icondebuff;
	@FXML
	private AnchorPane iconheal;
	private EditDeckController controller;

	@FXML
	void initialize() {
		Canvas physicalicon;
		physicalicon = (new Physical()).geticon();
		iconphysical.getChildren().add(physicalicon);


		Canvas magicalicon;
		magicalicon = (new Magical()).geticon();
		iconmagical.getChildren().add(magicalicon);

		Canvas debufficon;
		debufficon = (new Debuff()).geticon();
		icondebuff.getChildren().add(debufficon);

		Canvas healicon;
		healicon = (new Heal()).geticon();
		iconheal.getChildren().add(healicon);
	}

	public void getController(EditDeckController controller_) {
		controller = controller_;
	}


	@FXML
	private void onClickPhysicalIcon() {
		controller.updateResource(Resource.Physical);
		controller.closesubmanu();
	}

	@FXML
	private void onClickMagicalIcon() {
		controller.updateResource(Resource.Magical);
		controller.closesubmanu();
	}

	@FXML
	private void onClickDebuffIcon() {
		controller.updateResource(Resource.Debuff);
		controller.closesubmanu();
	}

	@FXML
	private void onClickHealIcon() {
		controller.updateResource(Resource.Heal);
		controller.closesubmanu();
	}
}
