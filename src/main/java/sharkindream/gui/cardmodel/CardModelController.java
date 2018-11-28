package sharkindream.gui.cardmodel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import sharkindream.actioncard.ActionCard;

public class CardModelController {

	@FXML
	private Label cardpower;
	@FXML
	private AnchorPane cardiconpane;
	@FXML
	private Text debuff;
	@FXML
	private Text accuracyandluck;
	@FXML
	private Label centerText;

	public void setCardInfo(ActionCard card) {

		cardpower.setText(card.power.getshowname());
		cardpower.setTextFill(Paint.valueOf(card.type.getColor()));
		cardiconpane.getChildren().clear();
		cardiconpane.getChildren().add(card.resource.getIcon(Paint.valueOf(card.type.getColor())));
		debuff.setText(card.debufflist[0].getshowname() + "/" + card.debufflist[1].getshowname() + "/" + card.debufflist[2].getshowname() + "/" + card.debufflist[3].getshowname());
		debuff.setFill(Paint.valueOf(card.type.getColor()));
		accuracyandluck.setText(card.accuracy.getaccuracy() + "/" + card.luck.getluck());
		accuracyandluck.setFill(Paint.valueOf(card.type.getColor()));
		centerText.setText(card.power.getshocenter());
		centerText.setTextFill(Paint.valueOf(card.type.getColor()));

	}
}
