package sharkindream.gui.mainmanu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.util.Duration;
import sharkindream.gui.event.OnMoveScreenHandler;
import sharkindream.network.client.Client;
import sharkindream.network.event.OnConnectedServerHandler;
import sharkindream.network.event.OnRunServerHandler;
import sharkindream.network.server.Server;

public class MainManuController {


	@FXML
	private TilePane showpane;
	@FXML
	private AnchorPane rotatepane;
	@FXML
	private AnchorPane playcircle;
	@FXML
	private AnchorPane deckcircle;
	@FXML
	private AnchorPane exitcircle;
	@FXML
	private AnchorPane minionscircle;
	@FXML
	private AnchorPane mainmanutagpane;
	@FXML
	private AnchorPane connectpane;
	@FXML
	private AnchorPane backline;
	@FXML
	private AnchorPane menulistpane;


	@FXML
	public TextField serverport;
	@FXML
	public TextField serveraddress;

	private ExecutorService service = Executors.newSingleThreadExecutor();



	private final int Radius = 90;
	private final int Anglebody = 65;


	@FXML
	void initialize() {

		//メインメニューロゴ


		Rectangle trect = new Rectangle();
		trect.widthProperty().bind(mainmanutagpane.widthProperty());
		trect.heightProperty().bind(mainmanutagpane.heightProperty());
		mainmanutagpane.setClip(trect);


		playcircle.rotateProperty().bind(rotatepane.rotateProperty());
		deckcircle.rotateProperty().bind(rotatepane.rotateProperty());
		exitcircle.rotateProperty().bind(rotatepane.rotateProperty());
		minionscircle.rotateProperty().bind(rotatepane.rotateProperty());

		((AnchorPane)playcircle.getChildren().get(0)).rotateProperty().bind(showpane.rotateProperty());
		((AnchorPane)deckcircle.getChildren().get(0)).rotateProperty().bind(showpane.rotateProperty());
		((AnchorPane)exitcircle.getChildren().get(0)).rotateProperty().bind(showpane.rotateProperty());
		((AnchorPane)minionscircle.getChildren().get(0)).rotateProperty().bind(showpane.rotateProperty());


		//線を引くための準備
		final double lengthcircle = 2 * Math.PI * Radius;
		((Circle)((AnchorPane)playcircle.getChildren().get(0)).getChildren().get(0)).getStrokeDashArray().add(lengthcircle);
		((Circle)((AnchorPane)playcircle.getChildren().get(0)).getChildren().get(0)).strokeDashOffsetProperty().set(lengthcircle);
		((Circle)((AnchorPane)deckcircle.getChildren().get(0)).getChildren().get(0)).getStrokeDashArray().add(lengthcircle );
		((Circle)((AnchorPane)deckcircle.getChildren().get(0)).getChildren().get(0)).strokeDashOffsetProperty().set(lengthcircle);
		((Circle)((AnchorPane)exitcircle.getChildren().get(0)).getChildren().get(0)).getStrokeDashArray().add(lengthcircle);
		((Circle)((AnchorPane)exitcircle.getChildren().get(0)).getChildren().get(0)).strokeDashOffsetProperty().set(lengthcircle);
		((Circle)((AnchorPane)minionscircle.getChildren().get(0)).getChildren().get(0)).getStrokeDashArray().add(lengthcircle);
		((Circle)((AnchorPane)minionscircle.getChildren().get(0)).getChildren().get(0)).strokeDashOffsetProperty().set(lengthcircle);

		//背景
		final double backlength = Math.sqrt( Math.pow(400, 2) + Math.pow(250, 2));
		((Line)backline.getChildren().get(0)).getStrokeDashArray().add( backlength );
		((Line)backline.getChildren().get(0)).strokeDashOffsetProperty().set(backlength);
		((Line)backline.getChildren().get(1)).getStrokeDashArray().add( backlength );
		((Line)backline.getChildren().get(1)).strokeDashOffsetProperty().set(backlength);
		((Line)backline.getChildren().get(2)).getStrokeDashArray().add( backlength );
		((Line)backline.getChildren().get(2)).strokeDashOffsetProperty().set(backlength);
		((Line)backline.getChildren().get(3)).getStrokeDashArray().add( backlength );
		((Line)backline.getChildren().get(3)).strokeDashOffsetProperty().set(backlength);

		//メニュー
		final double menulength = 450 * 2 + 120 * 2;
		((SVGPath)((AnchorPane)connectpane.getChildren().get(0)).getChildren().get(0)).getStrokeDashArray().add(menulength);
		((SVGPath)((AnchorPane)connectpane.getChildren().get(0)).getChildren().get(0)).strokeDashOffsetProperty().set(menulength);
		((SVGPath)((AnchorPane)connectpane.getChildren().get(1)).getChildren().get(0)).getStrokeDashArray().add(menulength);
		((SVGPath)((AnchorPane)connectpane.getChildren().get(1)).getChildren().get(0)).strokeDashOffsetProperty().set(menulength);



		playcircle.setTranslateY(-180);
		deckcircle.setTranslateX(-180);
		exitcircle.setTranslateY(180);
		minionscircle.setTranslateX(180);

		playcircle.setVisible(false);
		deckcircle.setVisible(false);
		exitcircle.setVisible(false);
		minionscircle.setVisible(false);


		playcircle.scaleXProperty().add( 1 +  (Math.cos(Math.toRadians(rotatepane.rotateProperty().get() + 0)) * 0.3  ) );
		playcircle.scaleYProperty().add( 1 +  (Math.cos(Math.toRadians(rotatepane.rotateProperty().get() + 0)) * 0.3  ) );
		deckcircle.scaleXProperty().add( 1 +  (Math.cos(Math.toRadians(rotatepane.rotateProperty().get() + 90)) * 0.3  ) );
		deckcircle.scaleYProperty().add( 1 +  (Math.cos(Math.toRadians(rotatepane.rotateProperty().get() + 90)) * 0.3  ) );
		exitcircle.scaleXProperty().add( 1 +  (Math.cos(Math.toRadians(rotatepane.rotateProperty().get() + 180)) * 0.3  ) );
		exitcircle.scaleYProperty().add( 1 +  (Math.cos(Math.toRadians(rotatepane.rotateProperty().get() + 180)) * 0.3  ) );
		minionscircle.scaleXProperty().add( 1 +  (Math.cos(Math.toRadians(rotatepane.rotateProperty().get() + 270)) * 0.3 ) );
		minionscircle.scaleXProperty().add( 1 +  (Math.cos(Math.toRadians(rotatepane.rotateProperty().get() + 270)) * 0.3  ) );


		playstartanimation();

	}


	private void playstartanimation() {

		Timeline tlb = setTimelineDuration(getWriteBackLine(), Duration.seconds(0.2));

		Timeline tl0 = setTimelineDuration(getShowMainManuLogo(), Duration.seconds(1.0));
		Timeline tl10 = setTimelineDuration(getWriteCircleTimeline(minionscircle), Duration.seconds(0.5));
		Timeline tl11 = setTimelineDuration(getWriteCircleTimeline(deckcircle), Duration.seconds(0.5));
		Timeline tl12 = setTimelineDuration(getWriteCircleTimeline(playcircle), Duration.seconds(2.5));
		Timeline tl13 = setTimelineDuration(getWriteCircleTimeline(exitcircle), Duration.seconds(2.5));
		Timeline tl2 = setTimelineDuration(getSlideMainManu(), Duration.seconds(4.0));
		//↑
		Timeline tl30 = setTimelineDuration(getRotateManuBodyTimeline(), Duration.seconds(4.2));
		Timeline tl31 = setTimelineDuration(getScaleTimeline(Duration.seconds(1.1), 0), Duration.seconds(4.2));


		Timeline tl4 = setTimelineDuration(getPlayMenuTimeline(), Duration.seconds(5.0));



		ParallelTransition paralleltransition = new ParallelTransition(tlb, tl0, tl10, tl11, tl12, tl13, tl2, tl30, tl31, tl4);

		paralleltransition.play();
	}

	private Timeline setTimelineDuration(Timeline timeline_, Duration duration) {
		Timeline timeline = new Timeline();

		KeyFrame kf = new KeyFrame(duration, new  javafx.event.EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				timeline_.play();
			}
        }, null);

		timeline.getKeyFrames().add(kf);

		return timeline;
	}

	private Timeline getPlayMenuTimeline() {
		Timeline timeline = new Timeline();
		Interpolator interpolator = Interpolator.SPLINE(0.7, 0.1, 0.3, 0.9);

		KeyValue kvml0 = new KeyValue(((SVGPath)((AnchorPane)connectpane.getChildren().get(0)).getChildren().get(0)).strokeDashOffsetProperty(), 0, interpolator);
		KeyFrame kfml0 = new KeyFrame(Duration.seconds(1.0), kvml0);
		KeyValue kvml1 = new KeyValue(((SVGPath)((AnchorPane)connectpane.getChildren().get(1)).getChildren().get(0)).strokeDashOffsetProperty(), 0, interpolator);
		KeyFrame kfml1 = new KeyFrame(Duration.seconds(1.0), kvml1);

		KeyValue kvmo00 = new KeyValue( ((AnchorPane)((AnchorPane)connectpane.getChildren().get(0)).getChildren().get(1)).opacityProperty(), 0);
		KeyFrame kfmo00 = new KeyFrame(Duration.seconds(0.4), kvmo00);
		KeyValue kvmo01 = new KeyValue( ((AnchorPane)((AnchorPane)connectpane.getChildren().get(0)).getChildren().get(1)).opacityProperty(), 1, interpolator);
		KeyFrame kfmo01 = new KeyFrame(Duration.seconds(1.4), kvmo01);
		KeyValue kvmo10 = new KeyValue( ((AnchorPane)((AnchorPane)connectpane.getChildren().get(1)).getChildren().get(1)).opacityProperty(), 0);
		KeyFrame kfmo10 = new KeyFrame(Duration.seconds(0.4), kvmo10);
		KeyValue kvmo11 = new KeyValue( ((AnchorPane)((AnchorPane)connectpane.getChildren().get(1)).getChildren().get(1)).opacityProperty(), 1 , interpolator);
		KeyFrame kfmo11 = new KeyFrame(Duration.seconds(1.4), kvmo11);



		timeline.getKeyFrames().add(kfml0);
		timeline.getKeyFrames().add(kfml1);
		timeline.getKeyFrames().add(kfmo00);
		timeline.getKeyFrames().add(kfmo01);
		timeline.getKeyFrames().add(kfmo10);
		timeline.getKeyFrames().add(kfmo11);


		return timeline;
	}

	private Timeline getSlideMainManu() {

		Timeline timeline = new Timeline();
		Interpolator interpolator = Interpolator.SPLINE(0.7, 0.1, 0.3, 0.9);

		KeyValue kvm0 = new KeyValue(showpane.translateXProperty(), 280);
		KeyFrame kfm0 = new KeyFrame(Duration.ZERO, kvm0);
		KeyValue kvm1 = new KeyValue(showpane.translateXProperty(), 25, interpolator);
		KeyFrame kfm1 = new KeyFrame(Duration.seconds(0.8), kvm1);

		timeline.getKeyFrames().add(kfm0);
		timeline.getKeyFrames().add(kfm1);

		return timeline;
	}

	private Timeline getWriteBackLine() {

		Timeline timeline = new Timeline();
		Interpolator interpolator = Interpolator.SPLINE(0.7, 0.1, 0.3, 0.9);

		KeyValue kvl0 = new KeyValue(((Line)backline.getChildren().get(0)).strokeDashOffsetProperty(), 0);
		KeyFrame kfl0 = new KeyFrame(Duration.seconds(0.4), kvl0);
		KeyValue kvl1 = new KeyValue(((Line)backline.getChildren().get(1)).strokeDashOffsetProperty(), 0);
		KeyFrame kfl1 = new KeyFrame(Duration.seconds(0.4), kvl1);
		KeyValue kvl2 = new KeyValue(((Line)backline.getChildren().get(2)).strokeDashOffsetProperty(), 0);
		KeyFrame kfl2 = new KeyFrame(Duration.seconds(0.4), kvl2);
		KeyValue kvl3 = new KeyValue(((Line)backline.getChildren().get(3)).strokeDashOffsetProperty(), 0);
		KeyFrame kfl3 = new KeyFrame(Duration.seconds(0.4), kvl3);

		KeyValue kvb0 = new KeyValue(backline.opacityProperty(), 1);
		KeyFrame kfb0 = new KeyFrame(Duration.seconds(0.5), kvb0);
		KeyValue kvb1 = new KeyValue(backline.opacityProperty(), 0, interpolator);
		KeyFrame kfb1 = new KeyFrame(Duration.seconds(3.0), kvb1);


		timeline.getKeyFrames().add(kfl0);
		timeline.getKeyFrames().add(kfl1);
		timeline.getKeyFrames().add(kfl2);
		timeline.getKeyFrames().add(kfl3);
		timeline.getKeyFrames().add(kfb0);
		timeline.getKeyFrames().add(kfb1);

		return timeline;


	}

	private Timeline getShowMainManuLogo(){

		//メインメニューロゴ
		Timeline timeline = new Timeline();
		Interpolator interpolator = Interpolator.SPLINE(0.7, 0.1, 0.3, 0.9);


		KeyValue kvml0 = new KeyValue(((Line)mainmanutagpane.getChildren().get(0)).endXProperty(), 0);
		KeyFrame kfml0 = new KeyFrame(Duration.ZERO, kvml0);
		KeyValue kvml1 = new KeyValue(((Line)mainmanutagpane.getChildren().get(0)).endXProperty(), 350, interpolator);
		KeyFrame kfml1 = new KeyFrame(Duration.seconds(0.8), kvml1);

		KeyValue kvmdx0 = new KeyValue(((Circle)mainmanutagpane.getChildren().get(1)).translateXProperty(), 0);
		KeyFrame kfmdx0 = new KeyFrame(Duration.ZERO, kvmdx0);
		KeyValue kvmdx1 = new KeyValue(((Circle)mainmanutagpane.getChildren().get(1)).translateXProperty(), 350, interpolator);
		KeyFrame kfmdx1 = new KeyFrame(Duration.seconds(0.8), kvmdx1);

		KeyValue kvmdo0 = new KeyValue(((Circle)mainmanutagpane.getChildren().get(1)).opacityProperty(), 0);
		KeyFrame kfmdo0 = new KeyFrame(Duration.ZERO, kvmdo0);
		KeyValue kvmdo1 = new KeyValue(((Circle)mainmanutagpane.getChildren().get(1)).opacityProperty(), 1, interpolator);
		KeyFrame kfmdo1 = new KeyFrame(Duration.seconds(0.2), kvmdo1);

		KeyValue kvmdo2 = new KeyValue(((Circle)mainmanutagpane.getChildren().get(1)).opacityProperty(), 1);
		KeyFrame kfmdo2 = new KeyFrame(Duration.seconds(0.6), kvmdo2);
		KeyValue kvmdo3 = new KeyValue(((Circle)mainmanutagpane.getChildren().get(1)).opacityProperty(), 0, interpolator);
		KeyFrame kfmdo3 = new KeyFrame(Duration.seconds(0.8), kvmdo3);


		KeyValue kvmt0 = new KeyValue(((Text)mainmanutagpane.getChildren().get(2)).translateYProperty(), 60);
		KeyFrame kfmt0 = new KeyFrame(Duration.seconds(0.8), kvmt0);
		KeyValue kvmt1 = new KeyValue(((Text)mainmanutagpane.getChildren().get(2)).translateYProperty(), 0, interpolator);
		KeyFrame kfmt1 = new KeyFrame(Duration.seconds(1.2), kvmt1);


		timeline.getKeyFrames().add(kfml0);
		timeline.getKeyFrames().add(kfml1);
		timeline.getKeyFrames().add(kfmdx0);
		timeline.getKeyFrames().add(kfmdx1);
		timeline.getKeyFrames().add(kfmdo0);
		timeline.getKeyFrames().add(kfmdo1);
		timeline.getKeyFrames().add(kfmdo2);
		timeline.getKeyFrames().add(kfmdo3);
		timeline.getKeyFrames().add(kfmt0);
		timeline.getKeyFrames().add(kfmt1);



		return timeline;

	}

	//--------------------------------------------
	private Timeline getRotateManuBodyTimeline() {

		Timeline timeline = new Timeline();
		Interpolator interpolator = Interpolator.SPLINE(0.7, 0.1, 0.3, 0.9);

		KeyValue kvs0 = new KeyValue(showpane.rotateProperty(), 0);
		KeyFrame kfs0 = new KeyFrame(Duration.ZERO, kvs0);
		KeyValue kvs1 = new KeyValue(showpane.rotateProperty(), Anglebody, interpolator);
		KeyFrame kfs1 = new KeyFrame(Duration.seconds(1.0), kvs1);

		timeline.getKeyFrames().add(kfs0);
		timeline.getKeyFrames().add(kfs1);

		return timeline;
	}


	private Timeline getWriteCircleTimeline(AnchorPane targetcircle) {


		Timeline timeline = new Timeline();
		Interpolator interpolator = Interpolator.SPLINE(0.7, 0.1, 0.3, 0.9);

		KeyFrame kfv = new KeyFrame(Duration.ZERO, new  javafx.event.EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				targetcircle.setVisible(true);
			}
        }, null);



		KeyValue kvc0 = new KeyValue(((Circle)((AnchorPane)targetcircle.getChildren().get(0)).getChildren().get(0)).strokeDashOffsetProperty(), 2 * Math.PI * Radius);
		KeyFrame kfc0 = new KeyFrame(Duration.ZERO, kvc0);
		KeyValue kvc1 = new KeyValue(((Circle)((AnchorPane)targetcircle.getChildren().get(0)).getChildren().get(0)).strokeDashOffsetProperty(), 0, interpolator);
		KeyFrame kfc1 = new KeyFrame(Duration.seconds(0.8), kvc1);

		KeyValue kvt0 = new KeyValue( ((Text)((TilePane)((AnchorPane)targetcircle.getChildren().get(0)).getChildren().get(1)).getChildren().get(0)).opacityProperty(), 0 );
		KeyFrame kft0 = new KeyFrame(Duration.seconds(1.0), kvt0);
		KeyValue kvt1 = new KeyValue( ((Text)((TilePane)((AnchorPane)targetcircle.getChildren().get(0)).getChildren().get(1)).getChildren().get(0)).opacityProperty(), 1, interpolator);
		KeyFrame kft1 = new KeyFrame(Duration.seconds(1.2), kvt1);


		switch(targetcircle.getId()) {
		case "playcircle":
			KeyValue kvct0p = new KeyValue(targetcircle.translateYProperty(), -180, interpolator);
			KeyFrame kfct0p = new KeyFrame(Duration.seconds(0.9), kvct0p);
			timeline.getKeyFrames().add(kfct0p);
			break;
		case "deckcircle":
			KeyValue kvct0d = new KeyValue(targetcircle.translateXProperty(), -180, interpolator);
			KeyFrame kfct0d = new KeyFrame(Duration.seconds(0.9), kvct0d);
			timeline.getKeyFrames().add(kfct0d);
			break;
		case "exitcircle":
			KeyValue kvct0e = new KeyValue(targetcircle.translateYProperty(), 180, interpolator);
			KeyFrame kfct0e = new KeyFrame(Duration.seconds(0.9), kvct0e);
			timeline.getKeyFrames().add(kfct0e);
			break;
		case "minionscircle":
			KeyValue kvct0m = new KeyValue(targetcircle.translateXProperty(), 180, interpolator);
			KeyFrame kfct0m = new KeyFrame(Duration.seconds(0.9), kvct0m);
			timeline.getKeyFrames().add(kfct0m);
			break;
		}


		KeyValue kvct1x = new KeyValue(targetcircle.translateXProperty(), 0, interpolator);
		KeyFrame kfct1x = new KeyFrame(Duration.seconds(1.5), kvct1x);
		KeyValue kvct1y = new KeyValue(targetcircle.translateYProperty(), 0, interpolator);
		KeyFrame kfct1y = new KeyFrame(Duration.seconds(1.5), kvct1y);


		KeyValue kvcc0 = new KeyValue( ((Circle)((AnchorPane)targetcircle.getChildren().get(0)).getChildren().get(0)).fillProperty(),
				Paint.valueOf(Color.web("#f4f4f4", 1).toString()));
		KeyFrame kfcc0 = new KeyFrame(Duration.ZERO, kvcc0);
		KeyValue kvcc1 = new KeyValue( ((Circle)((AnchorPane)targetcircle.getChildren().get(0)).getChildren().get(0)).fillProperty(),
				Paint.valueOf(Color.web("#383838", 1).toString()));
		KeyFrame kfcc1 = new KeyFrame(Duration.seconds(1.5), kvcc1);


		timeline.getKeyFrames().add(kfv);
		timeline.getKeyFrames().add(kfc0);
		timeline.getKeyFrames().add(kfc1);
		timeline.getKeyFrames().add(kft0);
		timeline.getKeyFrames().add(kft1);
		timeline.getKeyFrames().add(kfct1x);
		timeline.getKeyFrames().add(kfct1y);
		timeline.getKeyFrames().add(kfcc0);
		timeline.getKeyFrames().add(kfcc1);

		return timeline;

	}


	private Timeline getScaleTimeline(Duration duration, double angle) {


		setViewOrder((int) angle / 90);


		Timeline scaletimeline = new Timeline();

		KeyValue kvpx = new KeyValue(((AnchorPane)playcircle.getChildren().get(0)).scaleXProperty(), 1 +  (Math.cos(Math.toRadians(angle + 0)) * 0.4 ));
		KeyFrame kfpx = new KeyFrame(duration, kvpx);
		KeyValue kvpy = new KeyValue(((AnchorPane)playcircle.getChildren().get(0)).scaleYProperty(), 1 +  (Math.cos(Math.toRadians(angle + 0)) * 0.4 ));
		KeyFrame kfpy = new KeyFrame(duration, kvpy);

		KeyValue kvdx = new KeyValue(((AnchorPane)deckcircle.getChildren().get(0)).scaleXProperty(), 1 +  (Math.cos(Math.toRadians(angle - 90)) * 0.4 ));
		KeyFrame kfdx = new KeyFrame(duration, kvdx);
		KeyValue kvdy = new KeyValue(((AnchorPane)deckcircle.getChildren().get(0)).scaleYProperty(), 1 +  (Math.cos(Math.toRadians(angle - 90)) * 0.4 ));
		KeyFrame kfdy = new KeyFrame(duration, kvdy);

		KeyValue kvex = new KeyValue(((AnchorPane)exitcircle.getChildren().get(0)).scaleXProperty(), 1 +  (Math.cos(Math.toRadians(angle + 180)) * 0.4 ));
		KeyFrame kfex = new KeyFrame(duration, kvex);
		KeyValue kvey = new KeyValue(((AnchorPane)exitcircle.getChildren().get(0)).scaleYProperty(), 1 +  (Math.cos(Math.toRadians(angle + 180)) * 0.4 ));
		KeyFrame kfey = new KeyFrame(duration, kvey);

		KeyValue kvmx = new KeyValue(((AnchorPane)minionscircle.getChildren().get(0)).scaleXProperty(), 1 +  (Math.cos(Math.toRadians(angle + 90)) * 0.4 ));
		KeyFrame kfmx = new KeyFrame(duration, kvmx);
		KeyValue kvmy = new KeyValue(((AnchorPane)minionscircle.getChildren().get(0)).scaleYProperty(), 1 +  (Math.cos(Math.toRadians(angle + 90)) * 0.4 ));
		KeyFrame kfmy = new KeyFrame(duration, kvmy);

		scaletimeline.getKeyFrames().add(kfpx);
		scaletimeline.getKeyFrames().add(kfpy);
		scaletimeline.getKeyFrames().add(kfdx);
		scaletimeline.getKeyFrames().add(kfdy);
		scaletimeline.getKeyFrames().add(kfex);
		scaletimeline.getKeyFrames().add(kfey);
		scaletimeline.getKeyFrames().add(kfmx);
		scaletimeline.getKeyFrames().add(kfmy);

		return scaletimeline;
	}


	private void setViewOrder(int index) {

		//XXX : うごかん => ver.9以降動作確認



		playcircle.styleProperty().set("-fx-view-order:" + ((((index + 0) % 4) % 2 == 1) ? 2 : (index + 0) % 4) + ";");
		deckcircle.styleProperty().set("-fx-view-order:" + ((((index + 3) % 4) % 2 == 1) ? 2 : (index + 3) % 4) + ";");
		exitcircle.styleProperty().set("-fx-view-order:" + ((((index + 2) % 4) % 2 == 1) ? 2 : (index + 2) % 4) + ";");
		minionscircle.styleProperty().set("-fx-view-order:" + ((((index + 1) % 4) % 2 == 1) ? 2 : (index + 1) % 4) + ";");


	}



	@FXML
	private void onClicked(MouseEvent e) {


		Timeline rotatetimeline = new Timeline();

		double angle = 0;

		AnchorPane targetcircle = (AnchorPane)e.getSource();

		switch(targetcircle.getId()) {
		case "playcircle":
			angle = 0;
			break;
		case "deckcircle":
			angle = 90;
			break;
		case "exitcircle":
			angle = 180;
			break;
		case "minionscircle":
			angle = 270;
			break;
		default:
			angle = 0;
			break;
		}

		/*
		for(int i=0; i<4; ++i) {

			KeyValue kvcc = new KeyValue( ((Circle)((AnchorPane)((AnchorPane)rotatepane.getChildren().get(i+1)).getChildren().get(0)).getChildren().get(0)).fillProperty(),
					Paint.valueOf(Color.web("#383838", 1).toString()));
			if(rotatepane.getChildren().get(i+1).getId() == targetcircle.getId()) {
				kvcc = new KeyValue( ((Circle)((AnchorPane)((AnchorPane)rotatepane.getChildren().get(i+1)).getChildren().get(0)).getChildren().get(0)).fillProperty(),
						Paint.valueOf(Color.web("#f4f4f4", 1).toString()));
			}
			KeyFrame kfcc = new KeyFrame(Duration.seconds(0.5), kvcc);
			rotatetimeline.getKeyFrames().add(kfcc);
		}
		*/



		KeyValue kvm = new KeyValue(rotatepane.rotateProperty(),	 angle);
		KeyFrame kfm = new KeyFrame(Duration.seconds(0.5), kvm);

		KeyValue kvs = new KeyValue(menulistpane.rotateProperty(),	 angle);
		KeyFrame kfs = new KeyFrame(Duration.seconds(0.5), kvs);

		rotatetimeline.getKeyFrames().add(kfm);
		rotatetimeline.getKeyFrames().add(kfs);

		ParallelTransition paralleltransition = new ParallelTransition(rotatetimeline, getScaleTimeline(Duration.seconds(0.5), angle));
		paralleltransition.play();

	}

	/*
	private Timeline getonClickMenuAnimetionTimeline(String clickid) {
		Timeline timeline = new Timeline();


		if(clickid != activeid) {
			for(Node menu: menulistpane.getChildren()) {

				Timeline tlc = new Timeline();
				Timeline tlo = new Timeline();
				if(((AnchorPane)menu).getId() == activeid) //閉じるアニメーション
				if(((AnchorPane)menu).getId() == clickid) System.out.println(); //開くアニメーション
				//menuid == clickid => 開く => activeid = clickid
			}

			ParallelTransition paralleltransition = new ParallelTransition(tlb, tl0, tl10, tl11, tl12, tl13, tl2, tl30, tl31, tl4);

			return paralleltransition;
		}
	}
	*/


	//------------------------------------------------------------------------
	@FXML
	public void onBuild() {

		OnRunServerHandler handle = new OnRunServerHandler();

		Server server = new Server();
		if(server.setPort(serverport.getCharacters())) {

			server.addRunServerListner(handle);
			service.submit(server);
		}else {
			//TODO:視覚的にわかりやすいエラーへ
			System.out.println("正しい値を入力してください");
		}
	}

	@FXML
	public void onJoin() {
		OnConnectedServerHandler handle = new OnConnectedServerHandler() ;
		OnMoveScreenHandler moveScreenHandler = new OnMoveScreenHandler();

		Client client = new Client();
		if(client.setAddress(serveraddress.getCharacters())) {
			client.addConnecedListener(handle);
			client.addMoveScreenListener(moveScreenHandler);
			service.submit(client);

		}else{
			//TODO:視覚的にわかりやすいエラーへ
			System.out.println("正しい値を入力してください");
		}
	}


}
