package sharkindream.gui.gamescreen.infomation.lobby.selectminion.getterminion;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import sharkindream.config.Setting;
import sharkindream.gamecharacter.Minion;
import sharkindream.gui.gamescreen.infomation.lobby.selectminion.SelectMinionScreenController;

public class GetterMinionController {

	@FXML
	private Text hptext;
	@FXML
	private Text mptext;
	@FXML
	private Text strtext;
	@FXML
	private Text vittext;
	@FXML
	private Text inttext;
	@FXML
	private Text mindtext;

	@FXML
	private AnchorPane classiconpane;

	@FXML
	private Text minionnumtext;

	private int id;
	private int minionid = 0;
	private Minion minion;
	private int[] statusposintarray = {5,5,5,5,5,5};
	private SelectMinionScreenController controller;


	@FXML
	void initialize() {
		updateStatusUI();
	}

	private void updateStatusUI() {
		minion = (new Minion()).readMinionfromjson(minionid);
		minionnumtext.setText(String.valueOf(minionid));
		statusposintarray = minion.getstatuslist();


		strtext.setText(String.valueOf(minion.getstatuslist()[0]));
		vittext.setText(String.valueOf(minion.getstatuslist()[1]));
		inttext.setText(String.valueOf(minion.getstatuslist()[2]));
		mindtext.setText(String.valueOf(minion.getstatuslist()[3]));
		hptext.setText(String.valueOf(minion.getstatuslist()[4]));
		mptext.setText(String.valueOf(minion.getstatuslist()[5]));

		classiconpane.getChildren().clear();
		classiconpane.getChildren().add(minion.getMinionClass().getMinionclass().geticon(Paint.valueOf("#f4f4f4"), Paint.valueOf(minion.getMinionType().getColor())));
		((SVGPath)((AnchorPane)classiconpane.getParent()).getChildren().get(0)).setFill(Paint.valueOf(minion.getMinionType().getColor()));
	}

	@FXML
	private void onClickMinion() {
		if(controller != null) {
			controller.setMinion(minion, id);
			controller.closesubmenu(id);
		}
	}

	@FXML
	private void onClickNextMinion() {
		minionid += 1;
		if(minionid >= Setting.MaxMinionnum) {
			minionid = 0;
		}
		updateStatusUI();
	}

	@FXML
	private void onClickForwardMinion() {
		minionid -= 1;
		if(minionid < 0) {
			minionid = Setting.MaxMinionnum-1;
		}
		updateStatusUI();
	}


	public void setController(SelectMinionScreenController controller_, int id) {
		this.controller = controller_;
		this.id = id;
	}
}
