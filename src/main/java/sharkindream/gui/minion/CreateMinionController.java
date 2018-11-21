package sharkindream.gui.minion;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.util.Duration;
import sharkindream.config.Setting;
import sharkindream.gamecharacter.Type;
import sharkindream.gamecharacter.minion.Minion;
import sharkindream.gamecharacter.minion.MinionClass;
import sharkindream.gui.event.OnMoveScreenHandler;
import sharkindream.gui.minion.minionclass.SubmenuMinionclassController;
import sharkindream.gui.minion.type.SubmenuTypeMinionController;

public class CreateMinionController {

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
	private Text sumtext;

	@FXML
	private AnchorPane plussicon;
	@FXML
	private AnchorPane minussicon;
	@FXML
	private SVGPath center;
	@FXML
	private Text minionnum;
	@FXML
	private AnchorPane classicon;
	@FXML
	private SVGPath submenuicon;
	@FXML
	private Line topline;
	@FXML
	private Line buttomline;
	@FXML
	private AnchorPane submenuiconpane;
	@FXML
	private AnchorPane submenupane;


	private int[] statusposintarray = {5,5,5,5,5,5};
	private boolean isplusmode = true;
	private OnMoveScreenHandler listener;

	private Minion minion;
	private int minionid = 0;
	private boolean activesubmenu = false;

	@FXML
	void initialize() {
		showMinionInfomation();

		//サブメニューのセット
		submenuicon.rotateProperty().set(0);
		topline.endXProperty().set(0);
		buttomline.endXProperty().set(0);
		submenupane.prefHeightProperty().set(0);

		Rectangle trect = new Rectangle();
		trect.widthProperty().bind(submenupane.widthProperty());
		trect.heightProperty().bind(submenupane.heightProperty());
		submenupane.setClip(trect);

	}

	@FXML
	private void returnMainmenu() {
		onClickedReturnMenu();
	}

	@FXML
	private void onClickNextMinion() {
		minion.saveMiniontoJson();
		minionid +=1;
		if(minionid >= Setting.MaxMinionnum) minionid = 0;
		showMinionInfomation();
	}

	@FXML
	private void onClickForwardMinion() {
		minion.saveMiniontoJson();
		minionid -= 1;
		if(minionid < 0) minionid = Setting.MaxMinionnum - 1;
		showMinionInfomation();
	}

	private void showMinionInfomation() {
		minion = (new Minion()).readMinionfromjson(minionid);
		minionnum.setText(String.valueOf(minionid));
		statusposintarray = minion.getstatuslist();

		updateUIInfo();
	}

	@FXML
	private void onClickSubmenu() {
		if(this.activesubmenu) {
			closesubmenu();
		}else {
			opensubmenu();
		}
	}

	private void opensubmenu() {
		playOpenAnimation(true);
		this.activesubmenu = true;

		try {
				FXMLLoader typefxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/minion/type/SubmenuTypeMinion.fxml"));

				AnchorPane pane = typefxml.load();

				((SubmenuTypeMinionController)typefxml.getController()).setController(this);
				((AnchorPane)submenupane.getChildren().get(0)).getChildren().add(pane);
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
	}

	public void closesubmenu() {
		playOpenAnimation(false);;
		this.activesubmenu = false;

		((AnchorPane)submenupane.getChildren().get(0)).getChildren().clear();
	}



	private void playOpenAnimation(boolean openflag) {

		Timeline timeline = new Timeline();
		Interpolator interpolator = Interpolator.SPLINE(0.7, 0.1, 0.3, 0.9);

		int subangle = (openflag) ? -90 : 0;
		int linelength = (openflag) ? 250 : 0;
		int paneheight = (openflag) ? 350 : 0;

		KeyValue kvsr = new KeyValue(submenuicon.rotateProperty(), subangle, interpolator);
		KeyFrame kfsr = new KeyFrame(Duration.seconds(0.3), kvsr);
		KeyValue kvtlw = new KeyValue(topline.endXProperty(), linelength, interpolator);
		KeyFrame kftlw = new KeyFrame(Duration.seconds(0.75), kvtlw);
		KeyValue kvblw = new KeyValue(buttomline.endXProperty(), linelength, interpolator);
		KeyFrame kfblw = new KeyFrame(Duration.seconds(0.75), kvblw);
		KeyValue kvsph = new KeyValue(submenupane.prefHeightProperty(), paneheight, interpolator);
		KeyFrame kfsph = new KeyFrame(Duration.seconds(1), kvsph);

		timeline.getKeyFrames().add(kfsr);
		timeline.getKeyFrames().add(kftlw);
		timeline.getKeyFrames().add(kfblw);
		timeline.getKeyFrames().add(kfsph);

		timeline.play();

	}


	private void updateUIInfo() {
		strtext.setText(String.valueOf(minion.getstatuslist()[0]));
		vittext.setText(String.valueOf(minion.getstatuslist()[1]));
		inttext.setText(String.valueOf(minion.getstatuslist()[2]));
		mindtext.setText(String.valueOf(minion.getstatuslist()[3]));
		hptext.setText(String.valueOf(minion.getstatuslist()[4]));
		mptext.setText(String.valueOf(minion.getstatuslist()[5]));

		classicon.getChildren().clear();
		classicon.getChildren().add(minion.getMinionClass().getMinionclass().geticon(Paint.valueOf(minion.getMinionType().getColor())));

		 int sum = 0;
		 for(int stpoint : minion.getstatuslist()) {
			 sum += stpoint;
		 }
		 sumtext.setText(String.valueOf(sum));

		 if(sum > 49) {
			 center.setFill(Paint.valueOf("#EE6557"));
			 sumtext.setFill(Paint.valueOf("#ffffff"));
		 }else if(sum > 39) {
			 center.setFill(Paint.valueOf("#f09613"));
			 sumtext.setFill(Paint.valueOf("#ffffff"));
		 }else {
			 center.setFill(Paint.valueOf("#ffffff"));
			 sumtext.setFill(Paint.valueOf("#2b2b2b"));
		 }
	}



	 @FXML
	 private void onMouseClicked(MouseEvent e) {
		 String statusstr = ((Text)((AnchorPane)((SVGPath)e.getSource()).getParent()).getChildren().get(1)).getText();

		 if((isplusmode && e.getButton() == MouseButton.PRIMARY) || (!isplusmode && e.getButton() == MouseButton.SECONDARY)) {
			 switch(statusstr) {
			 case "Str":
				 if(statusposintarray[0] < 10 )  statusposintarray[0] +=1;
				 strtext.setText(String.valueOf(statusposintarray[0]));
				 break;
			 case "Vit":
				 if(statusposintarray[1] < 10 )  statusposintarray[1] +=1;
				 vittext.setText(String.valueOf(statusposintarray[1]));
				 break;
			 case "Int":
				 if(statusposintarray[2] < 10 )  statusposintarray[2] +=1;
				 inttext.setText(String.valueOf(statusposintarray[2]));
				 break;
			 case "Min":
				 if(statusposintarray[3] < 10 )  statusposintarray[3] +=1;
				 mindtext.setText(String.valueOf(statusposintarray[3]));
				 break;
			 case "HP":
				 if(statusposintarray[4] < 10 )  statusposintarray[4] +=1;
				 hptext.setText(String.valueOf(statusposintarray[4]));
				 break;
			 case "MP":
				 if(statusposintarray[5] < 10 )  statusposintarray[5] +=1;
				 mptext.setText(String.valueOf(statusposintarray[5]));
				 break;
			 }
		 }else {
			 switch(statusstr) {
			 case "Str":
				 if(statusposintarray[0] > 0 )  statusposintarray[0] -=1;
				 strtext.setText(String.valueOf(statusposintarray[0]));
				 break;
			 case "Vit":
				 if(statusposintarray[1] > 0 )  statusposintarray[1] -=1;
				 vittext.setText(String.valueOf(statusposintarray[1]));
				 break;
			 case "Int":
				 if(statusposintarray[2] > 0 )  statusposintarray[2] -=1;
				 inttext.setText(String.valueOf(statusposintarray[2]));
				 break;
			 case "Min":
				 if(statusposintarray[3] > 0 )  statusposintarray[3] -=1;
				 mindtext.setText(String.valueOf(statusposintarray[3]));
				 break;
			 case "HP":
				 if(statusposintarray[4] > 0)  statusposintarray[4] -=1;
				 hptext.setText(String.valueOf(statusposintarray[4]));
				 break;
			 case "MP":
				 if(statusposintarray[5] > 0 )  statusposintarray[5] -=1;
				 mptext.setText(String.valueOf(statusposintarray[5]));
				 break;
			 }
		 }
		 minion.setstatuslist(statusposintarray);
		 updateUIInfo();
	 }

	 @FXML
	 private void onMouseEnterCenter(MouseEvent e) {
		 Node shownode = (isplusmode) ? minussicon : plussicon;


		 FadeTransition fadetransition1 = new FadeTransition();
		 fadetransition1.setNode(sumtext);
		 fadetransition1.setDuration(Duration.seconds(0.1));
		 fadetransition1.setInterpolator(Interpolator.LINEAR);
		 fadetransition1.setFromValue(1.0);
		 fadetransition1.setToValue(0.0);

		 FadeTransition fadetransition2 = new FadeTransition();
		 fadetransition2.setNode(shownode);
		 fadetransition2.setDuration(Duration.seconds(0.1));
		 fadetransition2.setInterpolator(Interpolator.LINEAR);
		 fadetransition2.setFromValue(0.0);
		 fadetransition2.setToValue(1.0);


		 ParallelTransition animation = new ParallelTransition(fadetransition1, fadetransition2);

		 animation.play();

	 }

	 @FXML
	 private void onMouseExitedCenter(MouseEvent e) {

		 Node shownode = (isplusmode) ? minussicon : plussicon;

		 FadeTransition fadetransition1 = new FadeTransition();
		 fadetransition1.setNode(sumtext);
		 fadetransition1.setDuration(Duration.seconds(0.1));
		 fadetransition1.setInterpolator(Interpolator.LINEAR);
		 fadetransition1.setFromValue(0.1);
		 fadetransition1.setToValue(1.0);

		 FadeTransition fadetransition2 = new FadeTransition();
		 fadetransition2.setNode(shownode);
		 fadetransition2.setDuration(Duration.seconds(0.1));
		 fadetransition2.setInterpolator(Interpolator.LINEAR);
		 fadetransition2.setFromValue(1.0);
		 fadetransition2.setToValue(0.0);


		 ParallelTransition animation = new ParallelTransition(fadetransition1, fadetransition2);

		 animation.play();
	 }

	 @FXML
	 private void onMouseClickedCenter(MouseEvent e) {

		 isplusmode = !isplusmode;
		 Node shownode = (isplusmode) ? minussicon : plussicon;
		 Node hidenode = (isplusmode) ? plussicon : minussicon;

		 FadeTransition fadetransition1 = new FadeTransition();
		 fadetransition1.setNode(shownode);
		 fadetransition1.setDuration(Duration.seconds(0.1));
		 fadetransition1.setInterpolator(Interpolator.LINEAR);
		 fadetransition1.setFromValue(0.1);
		 fadetransition1.setToValue(1.0);

		 FadeTransition fadetransition2 = new FadeTransition();
		 fadetransition2.setNode(hidenode);
		 fadetransition2.setDuration(Duration.seconds(0.1));
		 fadetransition2.setInterpolator(Interpolator.LINEAR);
		 fadetransition2.setFromValue(1.0);
		 fadetransition2.setToValue(0.0);

		 ParallelTransition animation = new ParallelTransition(fadetransition1, fadetransition2);
		 animation.play();
	 }

	 public void updatetype(Type type) {
		 minion.setMinionType(type);
	 }

	 public void updateminionclass(MinionClass minionclass) {
		 minion.setMinionClass(minionclass);
		 updateUIInfo();
	 }

	 public void switchclasspane() {
			try {
				FXMLLoader typefxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/minion/minionclass/SubmenuMinionclass.fxml"));
				AnchorPane pane = typefxml.load();
				((SubmenuMinionclassController)typefxml.getController()).setController(this);
				((AnchorPane)submenupane.getChildren().get(0)).getChildren().clear();
				((AnchorPane)submenupane.getChildren().get(0)).getChildren().add(pane);
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

	public void onClickedReturnMenu() {
		if(listener != null) {
			listener.onMoveMainManuScreen();
		}
	}
}
