package sharkindream.gui.gamescreen.infomation.lobby.selectminion;

import java.io.IOException;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.util.Duration;
import sharkindream.gamecharacter.Minion;
import sharkindream.gui.event.OnMoveScreenHandler;
import sharkindream.gui.gamescreen.infomation.lobby.selectdeck.SelectDeckController;
import sharkindream.gui.gamescreen.infomation.lobby.selectminion.getterminion.GetterMinionController;
import sharkindream.gui.gamescreen.infomation.lobby.selectplayertype.SelectPlayerTypeController;
import sharkindream.network.client.Client;

public class SelectMinionScreenController {


	@FXML
	private AnchorPane minion0statuspane;
	@FXML
	private AnchorPane minion1statuspane;
	@FXML
	private AnchorPane minion0selectpane;
	@FXML
	private AnchorPane minion1selectpane;


	@FXML
	private Text hptext0;
	@FXML
	private Text mptext0;
	@FXML
	private Text strtext0;
	@FXML
	private Text vittext0;
	@FXML
	private Text inttext0;
	@FXML
	private Text mindtext0;
	@FXML
	private AnchorPane classiconpane0;

	@FXML
	private Text hptext1;
	@FXML
	private Text mptext1;
	@FXML
	private Text strtext1;
	@FXML
	private Text vittext1;
	@FXML
	private Text inttext1;
	@FXML
	private Text mindtext1;
	@FXML
	private AnchorPane classiconpane1;


	private boolean isclosesubmenu0 = true;
	private boolean isclosesubmenu1 = true;


	private OnMoveScreenHandler listener;
	private Minion minion0;
	private Minion minion1;

	@FXML
	void initialize() {
		minion0 = (new Minion()).readMinionfromjson(0);
		minion1 = (new Minion()).readMinionfromjson(1);
		updateminionstatusinfo(0);
		updateminionstatusinfo(1);
	}

	private void updateminionstatusinfo(int id) {
		switch(id) {
		case 0:
			strtext0.setText(String.valueOf(minion0.getstatuslist()[0]));
			vittext0.setText(String.valueOf(minion0.getstatuslist()[1]));
			inttext0.setText(String.valueOf(minion0.getstatuslist()[2]));
			mindtext0.setText(String.valueOf(minion0.getstatuslist()[3]));
			hptext0.setText(String.valueOf(minion0.getstatuslist()[4]));
			mptext0.setText(String.valueOf(minion0.getstatuslist()[5]));
			classiconpane0.getChildren().clear();
			classiconpane0.getChildren().add(minion0.getMinionClass().getMinionclass().geticon(Paint.valueOf("#f4f4f4"), Paint.valueOf(minion0.getMinionType().getColor())));
			((SVGPath)((AnchorPane)classiconpane0.getParent()).getChildren().get(0)).setFill(Paint.valueOf(minion0.getMinionType().getColor()));
			break;
		case 1:
			strtext1.setText(String.valueOf(minion1.getstatuslist()[0]));
			vittext1.setText(String.valueOf(minion1.getstatuslist()[1]));
			inttext1.setText(String.valueOf(minion1.getstatuslist()[2]));
			mindtext1.setText(String.valueOf(minion1.getstatuslist()[3]));
			hptext1.setText(String.valueOf(minion1.getstatuslist()[4]));
			mptext1.setText(String.valueOf(minion1.getstatuslist()[5]));
			classiconpane1.getChildren().clear();
			classiconpane1.getChildren().add(minion1.getMinionClass().getMinionclass().geticon(Paint.valueOf("#f4f4f4"), Paint.valueOf(minion1.getMinionType().getColor())));
			((SVGPath)((AnchorPane)classiconpane1.getParent()).getChildren().get(0)).setFill(Paint.valueOf(minion1.getMinionType().getColor()));
		}
	}

	@FXML
	public void onMouseClickedSelect(MouseEvent e) {
		switch(  ((AnchorPane)((AnchorPane)((SVGPath)e.getSource()).getParent()).getParent()).getId() ) {
		case "minion0statuspane":
			opensubmenu(minion0selectpane, 0);
			break;

		case "minion1statuspane":
			opensubmenu(minion1selectpane, 1);
			break;
		}
	}

	private void opensubmenu(AnchorPane submenupane, int id) {
		Timeline timeline = new Timeline();
		//サブメニューを開く
		final float SUBMENUWIDTH = 500;
		final float SUBHEIGHT = 570;

		Line uline = (Line) submenupane.getChildren().get(0);
		Line dline = (Line) submenupane.getChildren().get(1);

		KeyValue kvu0 = new KeyValue(uline.endXProperty(), 0, Interpolator.LINEAR);
		KeyFrame kfu0 = new KeyFrame(Duration.ZERO, kvu0);

		KeyValue kvu1 = new KeyValue(uline.endXProperty(), SUBMENUWIDTH);
		KeyFrame kfu1 = new KeyFrame(Duration.seconds(0.6), kvu1);

		KeyValue kvd0 = new KeyValue(dline.endXProperty(), 0, Interpolator.LINEAR);
		KeyFrame kfd0 = new KeyFrame(Duration.ZERO, kvd0);

		KeyValue kvd1 = new KeyValue(dline.endXProperty(), SUBMENUWIDTH);
		KeyFrame kfd1 = new KeyFrame(Duration.seconds(0.6), kvd1);

		KeyValue kvp0 = new KeyValue(submenupane.prefHeightProperty(), 0, Interpolator.LINEAR);
		KeyFrame kfp0 = new KeyFrame(Duration.seconds(0.2), kvp0);

		KeyValue kvp1 = new KeyValue(submenupane.prefHeightProperty(), SUBHEIGHT);
		KeyFrame kfp1 = new KeyFrame(Duration.seconds(0.8), kvp1);

		 KeyFrame kfsm = new KeyFrame(Duration.seconds(0.9), new  javafx.event.EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					openGetterMinionPane(submenupane, id);
				}

	        }, null);


		timeline.getKeyFrames().add(kfu0);
		timeline.getKeyFrames().add(kfu1);
		timeline.getKeyFrames().add(kfd0);
		timeline.getKeyFrames().add(kfd1);
		timeline.getKeyFrames().add(kfp0);
		timeline.getKeyFrames().add(kfp1);
		timeline.getKeyFrames().add(kfsm);

		timeline.play();
	}


	private void openGetterMinionPane(AnchorPane submenupane, int id) {
		try {
			FXMLLoader typefxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/gamescreen/infomation/lobby/selectminion/getterminion/GetterMinion.fxml"));

			AnchorPane pane = typefxml.load();
			((GetterMinionController)typefxml.getController()).setController(this, id);
			((AnchorPane)submenupane.getChildren().get(2)).getChildren().add(pane);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public void setMinion(Minion minion, int id) {
		switch(id) {
		case 0 :
			this.minion0 = minion;
			break;
		case 1:
			this.minion1 = minion;
			break;
		}

		updateminionstatusinfo(id);
	}

	public void closesubmenu(int id) {

		AnchorPane submenupane = (AnchorPane)minion0selectpane.getChildren().get(2);
		switch(id) {
		case 0:
			submenupane = minion0selectpane;
			break;
		case 1:
			submenupane = minion1selectpane;
			break;
		}
		Timeline timeline = new Timeline();
		//サブメニューを開く
		final float SUBMENUWIDTH = 500;
		final float SUBHEIGHT = 570;

		Line uline = (Line) submenupane.getChildren().get(0);
		Line dline = (Line) submenupane.getChildren().get(1);

		KeyValue kvu0 = new KeyValue(uline.endXProperty(), SUBMENUWIDTH, Interpolator.LINEAR);
		KeyFrame kfu0 = new KeyFrame(Duration.ZERO, kvu0);

		KeyValue kvu1 = new KeyValue(uline.endXProperty(), 0);
		KeyFrame kfu1 = new KeyFrame(Duration.seconds(0.6), kvu1);

		KeyValue kvd0 = new KeyValue(dline.endXProperty(), SUBMENUWIDTH, Interpolator.LINEAR);
		KeyFrame kfd0 = new KeyFrame(Duration.ZERO, kvd0);

		KeyValue kvd1 = new KeyValue(dline.endXProperty(), 0);
		KeyFrame kfd1 = new KeyFrame(Duration.seconds(0.6), kvd1);

		KeyValue kvp0 = new KeyValue(submenupane.prefHeightProperty(), SUBHEIGHT, Interpolator.LINEAR);
		KeyFrame kfp0 = new KeyFrame(Duration.seconds(0.2), kvp0);

		KeyValue kvp1 = new KeyValue(submenupane.prefHeightProperty(), 0);
		KeyFrame kfp1 = new KeyFrame(Duration.seconds(0.8), kvp1);




		timeline.getKeyFrames().add(kfu0);
		timeline.getKeyFrames().add(kfu1);
		timeline.getKeyFrames().add(kfd0);
		timeline.getKeyFrames().add(kfd1);
		timeline.getKeyFrames().add(kfp0);
		timeline.getKeyFrames().add(kfp1);

		timeline.play();

		((AnchorPane)submenupane.getChildren().get(2)).getChildren().clear();

	}

	@FXML
	public void gonextpage() {

		Client.getMyStatus().setMinion(minion0, 0);
		Client.getMyStatus().setMinion(minion1, 1);

		FXMLLoader selectdeckfxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/gamescreen/infomation/lobby/selectdeck/SelectDeck.fxml"));
		AnchorPane deckscreen;
		try {
			deckscreen = (AnchorPane)selectdeckfxml.load();
			((SelectDeckController)selectdeckfxml.getController()).addMoveScreenListener(new OnMoveScreenHandler());
			switchInfoScreen(deckscreen);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	@FXML
	public void returnpage() {

		FXMLLoader playertypescreenfxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/gamescreen/infomation/lobby/selectplayertype/SelectPlayerType.fxml"));
		AnchorPane infoscreen;
		try {
			infoscreen = playertypescreenfxml.load();
			((SelectPlayerTypeController)playertypescreenfxml.getController()).addMoveScreenListener(new OnMoveScreenHandler());
			switchInfoScreen(infoscreen);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}



	//リスナ登録
	public void addMoveScreenListener(OnMoveScreenHandler handler) {
		this.listener = handler;
	}

	public void removeMoveScreenListener() {
		this.listener = null;
	}

	private void switchInfoScreen(AnchorPane infoscreen) {
		if(listener != null) {
			listener.onSwitchInfomationScreen(infoscreen);
		}
	}
}
