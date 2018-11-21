package sharkindream.gui.deck.buff;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import sharkindream.actioncard.ActionCard;
import sharkindream.actioncard.ActionCard.Debuff;
import sharkindream.gui.deck.EditDeckController;

public class SubmenuBuffController {
	@FXML
	private Text strtext;
	@FXML
	private Text vittext;
	@FXML
	private Text inttext;
	@FXML
	private Text mintext;
	@FXML
	private HBox textlist;
	@FXML
	private Slider strslider;
	@FXML
	private Slider vitslider;
	@FXML
	private Slider intslider;
	@FXML
	private Slider minslider;
	@FXML
	private Object listener;
	private EditDeckController controller;
	private Debuff[] bufflist = {Debuff.none, Debuff.none.setreverse(), Debuff.none, Debuff.none.setreverse()};

	public void getController(EditDeckController controller_) {
		controller = controller_;
	}

	public void setSliderIni(ActionCard card) {

		bufflist[0] = getDebuffenum(getdebuffnum(card.debufflist[0]));
		bufflist[1] = getDebuffenum(getdebuffnum(card.debufflist[1]));
		bufflist[2] = getDebuffenum(getdebuffnum(card.debufflist[2]));
		bufflist[3] = getDebuffenum(getdebuffnum(card.debufflist[3]));

		strslider.setValue(getdebuffnum(bufflist[0]));
		vitslider.setValue(getdebuffnum(bufflist[1]));
		intslider.setValue(getdebuffnum(bufflist[2]));
		minslider.setValue(getdebuffnum(bufflist[3]));

		strtext.setText(bufflist[0].getshowname());
		vittext.setText(bufflist[1].getshowname());
		inttext.setText(bufflist[2].getshowname());
		mintext.setText(bufflist[3].getshowname());
	}

	private int getdebuffnum(Debuff debuff) {
		switch(debuff) {
		case none:
			return 0;
		case m1:
			return -1;
		case m2:
			return -2;
		case setm3:
			return -3;
		case p1:
			return 1;
		case p2:
			return 2;
		case setp3:
			return 3;
		case setnone:
			return 4;
		default:
			return 0;
		}
	}

	@FXML
	private void onSlideStr(MouseEvent e) {
		int num = (int)(((Slider)e.getSource()).getValue() + 4.5) - 4;
		strtext.setText(getDebuffenum(num).getshowname());
		bufflist[0] = getDebuffenum(num);
	}

	@FXML
	private void onSlideVit(MouseEvent e) {
		int num = (int)(((Slider)e.getSource()).getValue() + 4.5) - 4;
		vittext.setText(getDebuffenum(num).getshowname());
		bufflist[1] = getDebuffenum(num);
	}

	@FXML
	private void onSlideInt(MouseEvent e) {
		int num = (int)(((Slider)e.getSource()).getValue() + 4.5) - 4;
		inttext.setText(getDebuffenum(num).getshowname());
		bufflist[2] = getDebuffenum(num);
	}

	@FXML
	private void onSlideMin(MouseEvent e) {
		int num = (int)(((Slider)e.getSource()).getValue() + 4.5) - 4;
		mintext.setText(getDebuffenum(num).getshowname());
		bufflist[3] = getDebuffenum(num);
	}

	private Debuff getDebuffenum(int num) {
		switch(num) {
		case 0:
			return ActionCard.Debuff.none;
		case -1:
			return ActionCard.Debuff.m1;
		case -2:
			return ActionCard.Debuff.m2;
		case -3:
			return ActionCard.Debuff.setm3;
		case 1:
			return ActionCard.Debuff.p1;
		case 2:
			return ActionCard.Debuff.p2;
		case 3:
			return ActionCard.Debuff.setp3;
		case 4:
			return ActionCard.Debuff.setnone;
		default:
			return ActionCard.Debuff.none;
		}
	}

	@FXML
	private void onClickApply() {


		controller.updateBuff(bufflist);
		controller.closesubmanu();
	}



}
