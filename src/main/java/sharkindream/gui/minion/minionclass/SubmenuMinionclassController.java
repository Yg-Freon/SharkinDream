package sharkindream.gui.minion.minionclass;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import sharkindream.gamecharacter.minion.MinionClass;
import sharkindream.gamecharacter.minion.classtype.BeastTamer;
import sharkindream.gamecharacter.minion.classtype.Bishop;
import sharkindream.gamecharacter.minion.classtype.MadScientist;
import sharkindream.gamecharacter.minion.classtype.Sage;
import sharkindream.gamecharacter.minion.classtype.Shielder;
import sharkindream.gamecharacter.minion.classtype.Soldier;
import sharkindream.gamecharacter.minion.classtype.Witch;
import sharkindream.gui.minion.CreateMinionController;

public class SubmenuMinionclassController {


	@FXML
	private AnchorPane soldiericon;
	@FXML
	private AnchorPane shieldericon;
	@FXML
	private AnchorPane witchicon;
	@FXML
	private AnchorPane sageicon;
	@FXML
	private AnchorPane bishopicon;
	@FXML
	private AnchorPane madscientisticon;
	@FXML
	private AnchorPane beasttamericon;
	private CreateMinionController controller;

	@FXML
	void initialize(){

		soldiericon.getChildren().add((new Soldier()).geticon());
		shieldericon.getChildren().add((new Shielder()).geticon());
		witchicon.getChildren().add((new Witch()).geticon());
		sageicon.getChildren().add((new Sage()).geticon());
		bishopicon.getChildren().add((new Bishop()).geticon());
		madscientisticon.getChildren().add((new MadScientist()).geticon());
		beasttamericon.getChildren().add((new BeastTamer()).geticon());
	}

	public void setController(CreateMinionController controller_ ) {
		this.controller = controller_;
	}

	@FXML
	private void onClickSoldier() {
		controller.updateminionclass(MinionClass.Soldier);
		controller.closesubmenu();
	}

	@FXML
	private void onClickShielder() {
		controller.updateminionclass(MinionClass.Shielder);
		controller.closesubmenu();
	}

	@FXML
	private void onClickWitch() {
		controller.updateminionclass(MinionClass.Witch);
		controller.closesubmenu();
	}

	@FXML
	private void onClickSage() {
		controller.updateminionclass(MinionClass.Sage);
		controller.closesubmenu();
	}

	@FXML
	private void onClickBichop() {
		controller.updateminionclass(MinionClass.Bishop);
		controller.closesubmenu();
	}

	@FXML
	private void onClickMadscientist() {
		controller.updateminionclass(MinionClass.MadScientist);
		controller.closesubmenu();
	}

	@FXML
	private void onClickBeastTamer() {
		controller.updateminionclass(MinionClass.BeastTamer);
		controller.closesubmenu();
	}
}
