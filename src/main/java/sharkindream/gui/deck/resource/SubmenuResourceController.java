package sharkindream.gui.deck.resource;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
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
		System.out.println(iconphysical.getChildren());


		Canvas magicalicon;
		magicalicon = (new Magical()).geticon();
		iconmagical.getChildren().add(magicalicon);
	}

	public void getController(EditDeckController controller_) {
		controller = controller_;
	}
}
