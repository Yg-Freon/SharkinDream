package sharkindream.gui.minion.minionclass;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import sharkindream.gamecharacter.GameCharacter.CharacterClass;
import sharkindream.gamecharacter.classtype.BeastTamer;
import sharkindream.gamecharacter.classtype.Bishop;
import sharkindream.gamecharacter.classtype.MadScientist;
import sharkindream.gamecharacter.classtype.Sage;
import sharkindream.gamecharacter.classtype.Shielder;
import sharkindream.gamecharacter.classtype.Soldier;
import sharkindream.gamecharacter.classtype.Witch;
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
		controller.updateminionclass(CharacterClass.Soldier);
		controller.closesubmenu();
	}

	@FXML
	private void onClickShielder() {
		controller.updateminionclass(CharacterClass.Shielder);
		controller.closesubmenu();
	}

	@FXML
	private void onClickWitch() {
		controller.updateminionclass(CharacterClass.Witch);
		controller.closesubmenu();
	}

	@FXML
	private void onClickSage() {
		controller.updateminionclass(CharacterClass.Sage);
		controller.closesubmenu();
	}

	@FXML
	private void onClickBichop() {
		controller.updateminionclass(CharacterClass.Bishop);
		controller.closesubmenu();
	}

	@FXML
	private void onClickMadscientist() {
		controller.updateminionclass(CharacterClass.MadScientist);
		controller.closesubmenu();
	}

	@FXML
	private void onClickBeastTamer() {
		controller.updateminionclass(CharacterClass.BeastTamer);
		controller.closesubmenu();
	}
}
