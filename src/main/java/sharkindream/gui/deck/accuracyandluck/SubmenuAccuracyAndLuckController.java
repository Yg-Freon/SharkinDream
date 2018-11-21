package sharkindream.gui.deck.accuracyandluck;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import sharkindream.actioncard.ActionCard.Accuracy;
import sharkindream.actioncard.ActionCard.Luck;
import sharkindream.gui.deck.EditDeckController;

public class SubmenuAccuracyAndLuckController {

	private Accuracy tempaccuracy = Accuracy.a100;
	private Luck templuck = Luck.l0;
	private EditDeckController controller;

	@FXML
	private Text accuracytext;
	@FXML
	private Text lucktext;

	public void getController(EditDeckController controller_) {
		controller = controller_;
	}


	@FXML
	private void onSlideAccuracy(MouseEvent e) {
		switch((int)(((Slider)e.getSource()).getValue() + 0.5)) {
		case 0:
			tempaccuracy = Accuracy.a30;
			break;

		case 1:
			tempaccuracy = Accuracy.a50;
			break;

		case 2:
			tempaccuracy = Accuracy.a60;
			break;

		case 3:
			tempaccuracy = Accuracy.a80;
			break;

		case 4:
			tempaccuracy = Accuracy.a90;
			break;

		case 5:
			tempaccuracy = Accuracy.a100;
			break;
		}
		accuracytext.setText(String.valueOf(tempaccuracy.getaccuracy()));

	}

	@FXML
	private void onSlideLuck(MouseEvent e) {

		switch((int)(((Slider)e.getSource()).getValue() + 0.5)) {
		case 0:
			templuck = Luck.l0;
			break;

		case 1:
			templuck = Luck.l5;
			break;


		case 2:
			templuck = Luck.l10;
			break;


		case 3:
			templuck = Luck.l30;
			break;


		case 4:
			templuck = Luck.l50;
			break;


		case 5:
			templuck = Luck.l100;
			break;

		}
		lucktext.setText(String.valueOf(templuck.getluck()));
	}

	@FXML
	private void onClickApply() {
		controller.updateAccuracyAndLuck(tempaccuracy, templuck);
		controller.closesubmanu();
	}


}
