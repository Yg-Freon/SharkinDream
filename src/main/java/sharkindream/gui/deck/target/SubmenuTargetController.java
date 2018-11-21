package sharkindream.gui.deck.target;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import sharkindream.actioncard.ActionCard.Target;
import sharkindream.gui.deck.EditDeckController;

public class SubmenuTargetController {


	private EditDeckController controller;


	public void getController(EditDeckController controller_) {
		controller = controller_;
	}


	@FXML
	private void onClickTarget(MouseEvent e){
		switch(((Text)((AnchorPane)e.getSource()).getChildren().get(0)).getText()) {
		case "Enemy":
			controller.updateTarget(Target.Enemy);
			controller.closesubmanu();
			break;

		case "Self":
			controller.updateTarget(Target.Self);
			controller.closesubmanu();
			break;

		case "All":
			controller.updateTarget(Target.AllEnemies);
			controller.closesubmanu();
			break;

		case "Random":
			controller.updateTarget(Target.Random);
			controller.closesubmanu();
			break;

		}


	}
}
