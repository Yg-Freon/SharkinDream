package sharkindream.gui.deck.power;

import javafx.fxml.FXML;
import sharkindream.actioncard.ActionCard.Power;
import sharkindream.gui.deck.EditDeckController;

public class SubmenuPowerController {


	private EditDeckController controller;

	public void getController(EditDeckController controller_) {
		controller = controller_;
	}

	@FXML
	private void onClickO(){
		controller.updatePower(Power.O);
		controller.closesubmanu();
	}

	@FXML
	private void onClickT(){
		controller.updatePower(Power.T);
		controller.closesubmanu();
	}

	@FXML
	private void onClickF(){
		controller.updatePower(Power.F);
		controller.closesubmanu();
	}

	@FXML
	private void onClickE(){
		controller.updatePower(Power.E);
		controller.closesubmanu();
	}

	@FXML
	private void onClickN(){
		controller.updatePower(Power.N);
		controller.closesubmanu();
	}

	@FXML
	private void onClickA(){
		controller.updatePower(Power.A);
		controller.closesubmanu();
	}

	@FXML
	private void onClickK(){
		controller.updatePower(Power.K);
		controller.closesubmanu();
	}

	@FXML
	private void onClickJoker(){
		controller.updatePower(Power.Joker);
		controller.closesubmanu();
	}
}
