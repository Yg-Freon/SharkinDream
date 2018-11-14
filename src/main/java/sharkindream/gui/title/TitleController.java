package sharkindream.gui.title;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import sharkindream.gui.event.OnMoveScreenHandler;

public class TitleController {

	@FXML
	private AnchorPane titlelogo;
	@FXML
	private Line topline;
	@FXML
	private Line bottomline;
	@FXML
	private Circle logodot;
	@FXML
	private TilePane creatertext;
	@FXML
	private AnchorPane iconlogo;
	@FXML
	private AnchorPane toptheeth;
	@FXML
	private AnchorPane bottomteeth;
	@FXML
	private TilePane sharkicon;
	@FXML
	private AnchorPane startgame;
	@FXML
	private Circle circle0;
	@FXML
	private Circle circle1;


	private OnMoveScreenHandler listener;


	@FXML
	void initialize() {

		Rectangle trect = new Rectangle();
		trect.widthProperty().set(450);
		trect.heightProperty().bind(titlelogo.heightProperty());
		creatertext.setClip(trect);

		Rectangle irect = new Rectangle();
		irect.widthProperty().set(600);
		irect.heightProperty().set(150);
		iconlogo.setClip(irect);

	}

	@FXML
	private void onClickStart() {
		startgame.setDisable(true);
		playgamestartanimation();
	}

	private void playgamestartanimation() {

		Timeline startimeline = new Timeline();

		Interpolator interpolator = Interpolator.SPLINE(0.7, 0.1, 0.3, 0.9);

		KeyValue kvc00 = new KeyValue(circle0.radiusProperty(), 0);
		KeyFrame kfc00 = new KeyFrame( Duration.ZERO , kvc00 );
		KeyValue kvc01 = new KeyValue(circle0.radiusProperty(), 350, interpolator);
		KeyFrame kfc01 = new KeyFrame( Duration.seconds(0.8) , kvc01 );

		KeyValue kvc0o0 = new KeyValue(circle0.strokeProperty(), Paint.valueOf(Color.web("#383838", 1).toString()));
		KeyFrame kfc0o0 = new KeyFrame( Duration.seconds(0.4) , kvc0o0 );
		KeyValue kvc0o1 = new KeyValue(circle0.strokeProperty(), Paint.valueOf(Color.web("#383838", 0).toString()), interpolator);
		KeyFrame kfc0o1 = new KeyFrame( Duration.seconds(0.8) , kvc0o1 );


		KeyValue kvc10 = new KeyValue(circle1.radiusProperty(), 0);
		KeyFrame kfc10 = new KeyFrame( Duration.seconds(0.2) , kvc10 );
		KeyValue kvc11 = new KeyValue(circle1.radiusProperty(), 500, interpolator);
		KeyFrame kfc11 = new KeyFrame( Duration.seconds(1.0) , kvc11 );

		KeyValue kvc1o0 = new KeyValue(circle1.strokeProperty(), Paint.valueOf(Color.web("#383838", 1).toString()));
		KeyFrame kfc1o0 = new KeyFrame( Duration.seconds(0.6) , kvc1o0 );
		KeyValue kvc1o1 = new KeyValue(circle1.strokeProperty(), Paint.valueOf(Color.web("#383838", 0).toString()), interpolator);
		KeyFrame kfc1o1 = new KeyFrame( Duration.seconds(1.0) , kvc1o1 );


		KeyValue kvs0 = new KeyValue(startgame.opacityProperty(), 1);
		KeyFrame kfs0 = new KeyFrame( Duration.seconds(1.0) , kvs0 );
		KeyValue kvs1 = new KeyValue(startgame.opacityProperty(), 0, interpolator);
		KeyFrame kfs1 = new KeyFrame( Duration.seconds(1.4) , kvs1 );



        KeyFrame kfplaygame = new KeyFrame(Duration.seconds(1.5), new  javafx.event.EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				onClickedGameStart();
			}

        }, null);






		startimeline.getKeyFrames().add(kfc00);
		startimeline.getKeyFrames().add(kfc01);
		startimeline.getKeyFrames().add(kfc0o0);
		startimeline.getKeyFrames().add(kfc0o1);

		startimeline.getKeyFrames().add(kfc10);
		startimeline.getKeyFrames().add(kfc11);
		startimeline.getKeyFrames().add(kfc1o0);
		startimeline.getKeyFrames().add(kfc1o1);

		startimeline.getKeyFrames().add(kfs0);
		startimeline.getKeyFrames().add(kfs1);

		startimeline.getKeyFrames().add(kfplaygame);





		startimeline.play();
	}

	//読み込み完了後、少し間を開ける
	public void readyanimation() {
		Timeline logotimeline = new Timeline();
        KeyFrame kf = new KeyFrame(Duration.seconds(0.8), new  javafx.event.EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				playanimation();
			}
        }, null);

        logotimeline.getKeyFrames().add(kf);

        logotimeline.play();

	}

	private void playanimation() {
		Timeline logotimeline1 = new Timeline();

		Interpolator interpolator = Interpolator.SPLINE(0.7, 0.1, 0.3, 0.9);

		KeyValue kvt0 = new KeyValue( topline.endXProperty() , 0 );
        KeyFrame kft0 = new KeyFrame( Duration.seconds(0.2) , kvt0 );

        KeyValue kvt1 = new KeyValue( topline.endXProperty() , 450, interpolator);
        KeyFrame kft1 = new KeyFrame( Duration.seconds(1.0) , kvt1 );

        KeyValue kvb0 = new KeyValue( bottomline.endXProperty() , 0 );
        KeyFrame kfb0 = new KeyFrame( Duration.seconds(0.2) , kvb0 );

        KeyValue kvb1 = new KeyValue( bottomline.endXProperty() , 450, interpolator);
        KeyFrame kfb1 = new KeyFrame( Duration.seconds(1.0) , kvb1 );

        KeyValue kvd0 = new KeyValue( logodot.translateXProperty() , 0 );
        KeyFrame kfd0 = new KeyFrame( Duration.seconds(0.2) , kvd0 );

        KeyValue kvd1 = new KeyValue( logodot.translateXProperty() , 450, interpolator);
        KeyFrame kfd1 = new KeyFrame( Duration.seconds(1.0) , kvd1 );

        KeyValue kvdo0 = new KeyValue( logodot.opacityProperty() , 0 );
        KeyFrame kfdo0 = new KeyFrame( Duration.ZERO , kvdo0 );

        KeyValue kvdo1 = new KeyValue( logodot.opacityProperty() , 1, interpolator);
        KeyFrame kfdo1 = new KeyFrame( Duration.seconds(0.2) , kvdo1 );

        KeyValue kvdo2 = new KeyValue( logodot.opacityProperty() , 1 );
        KeyFrame kfdo2 = new KeyFrame( Duration.seconds(0.6) , kvdo2 );

        KeyValue kvdo3 = new KeyValue( logodot.opacityProperty() , 0, interpolator);
        KeyFrame kfdo3 = new KeyFrame( Duration.seconds(0.8) , kvdo3 );





		KeyValue kvlh0 = new KeyValue( titlelogo.prefHeightProperty() , 0 );
        KeyFrame kflh0 = new KeyFrame( Duration.seconds(1.0) , kvlh0 );

        KeyValue kvlh1 = new KeyValue( titlelogo.prefHeightProperty() , 100, interpolator);
        KeyFrame kflh1 = new KeyFrame( Duration.seconds(1.5) , kvlh1 );

		KeyValue kvco0 = new KeyValue( creatertext.opacityProperty() , 1 );
        KeyFrame kfco0 = new KeyFrame( Duration.seconds(2.7) , kvco0 );

        KeyValue kvco1 = new KeyValue( creatertext.opacityProperty() , 0, interpolator);
        KeyFrame kfco1 = new KeyFrame( Duration.seconds(3.1) , kvco1 );



		KeyValue kvtt0 = new KeyValue( toptheeth.translateYProperty() , -90 );
        KeyFrame kftt0 = new KeyFrame( Duration.seconds(3.4) , kvtt0 );

        KeyValue kvtt1 = new KeyValue( toptheeth.translateYProperty() , -70, interpolator);
        KeyFrame kftt1 = new KeyFrame( Duration.seconds(3.7) , kvtt1 );

        KeyValue kvbt0 = new KeyValue( bottomteeth.translateYProperty() , 95 );
        KeyFrame kfbt0 = new KeyFrame( Duration.seconds(3.4) , kvbt0 );

        KeyValue kvbt1 = new KeyValue( bottomteeth.translateYProperty() , 70, interpolator);
        KeyFrame kfbt1 = new KeyFrame( Duration.seconds(3.7) , kvbt1 );



		KeyValue kvtt2 = new KeyValue( toptheeth.translateYProperty() , -70 );
        KeyFrame kftt2 = new KeyFrame( Duration.seconds(3.9) , kvtt2 );

        KeyValue kvtt3 = new KeyValue( toptheeth.translateYProperty() , 0, interpolator);
        KeyFrame kftt3 = new KeyFrame( Duration.seconds(4.2) , kvtt3 );

        KeyValue kvbt2 = new KeyValue( bottomteeth.translateYProperty() , 70 );
        KeyFrame kfbt2 = new KeyFrame( Duration.seconds(3.9) , kvbt2 );

        KeyValue kvbt3 = new KeyValue( bottomteeth.translateYProperty() , 0, interpolator);
        KeyFrame kfbt3 = new KeyFrame( Duration.seconds(4.2) , kvbt3 );

		KeyValue kvlh2 = new KeyValue( titlelogo.prefHeightProperty() , 100 );
        KeyFrame kflh2 = new KeyFrame( Duration.seconds(3.9) , kvlh2 );

        KeyValue kvlh3 = new KeyValue( titlelogo.prefHeightProperty() , 0, interpolator);
        KeyFrame kflh3 = new KeyFrame( Duration.seconds(4.2) , kvlh3 );

		KeyValue kvlo0 = new KeyValue( titlelogo.opacityProperty() , 1 );
        KeyFrame kflo0 = new KeyFrame( Duration.seconds(3.9) , kvlo0 );

        KeyValue kvlo1 = new KeyValue( titlelogo.opacityProperty() , 0, interpolator);
        KeyFrame kflo1 = new KeyFrame( Duration.seconds(4.2) , kvlo1 );



        KeyValue kvix0 = new KeyValue( iconlogo.translateXProperty() , 0 );
        KeyFrame kfix0 = new KeyFrame( Duration.seconds(4.3) , kvix0 );

        KeyValue kvix1 = new KeyValue( iconlogo.translateXProperty() , -180, interpolator);
        KeyFrame kfix1 = new KeyFrame( Duration.seconds(4.5) , kvix1 );

        KeyValue kvso0 = new KeyValue( sharkicon.opacityProperty() , 0 );
        KeyFrame kfso0 = new KeyFrame( Duration.seconds(4.2) , kvso0 );

        KeyValue kvso1 = new KeyValue( sharkicon.opacityProperty() , 1, interpolator);
        KeyFrame kfso1 = new KeyFrame( Duration.seconds(4.3) , kvso1 );

        KeyValue kvsx0 = new KeyValue( sharkicon.translateXProperty() , 0 );
        KeyFrame kfsx0 = new KeyFrame( Duration.seconds(4.3) , kvsx0 );

        KeyValue kvsx1 = new KeyValue( sharkicon.translateXProperty() , 180, interpolator);
        KeyFrame kfsx1 = new KeyFrame( Duration.seconds(4.5) , kvsx1 );


        KeyValue kvbo0 = new KeyValue( startgame.opacityProperty() , 0 );
        KeyFrame kfbo0 = new KeyFrame( Duration.seconds(4.6) , kvbo0 );

        KeyValue kvbo1 = new KeyValue( startgame.opacityProperty() , 1, interpolator);
        KeyFrame kfbo1 = new KeyFrame( Duration.seconds(5.0) , kvbo1 );

        KeyFrame kfbd0 = new KeyFrame(Duration.seconds(4.9), new  javafx.event.EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				startgame.setDisable(true);
			}

        }, null);

        KeyFrame kfbd1 = new KeyFrame(Duration.seconds(5.0), new  javafx.event.EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				startgame.setDisable(false);
			}

        }, null);



        logotimeline1.getKeyFrames().add( kft0 );
        logotimeline1.getKeyFrames().add( kft1 );
        logotimeline1.getKeyFrames().add( kfb0 );
        logotimeline1.getKeyFrames().add( kfb1 );
        logotimeline1.getKeyFrames().add( kfd0 );
        logotimeline1.getKeyFrames().add( kfd1 );
        logotimeline1.getKeyFrames().add( kfdo0 );
        logotimeline1.getKeyFrames().add( kfdo1 );
        logotimeline1.getKeyFrames().add( kfdo2 );
        logotimeline1.getKeyFrames().add( kfdo3 );

        logotimeline1.getKeyFrames().add( kflh0 );
        logotimeline1.getKeyFrames().add( kflh1 );
        logotimeline1.getKeyFrames().add( kfco0 );
        logotimeline1.getKeyFrames().add( kfco1 );

        logotimeline1.getKeyFrames().add( kftt0 );
        logotimeline1.getKeyFrames().add( kftt1 );
        logotimeline1.getKeyFrames().add( kfbt0 );
        logotimeline1.getKeyFrames().add( kfbt1 );
        logotimeline1.getKeyFrames().add( kftt2 );
        logotimeline1.getKeyFrames().add( kftt3 );
        logotimeline1.getKeyFrames().add( kfbt2 );
        logotimeline1.getKeyFrames().add( kfbt3 );
        logotimeline1.getKeyFrames().add( kflh2 );
        logotimeline1.getKeyFrames().add( kflh3 );
        logotimeline1.getKeyFrames().add( kflo0 );
        logotimeline1.getKeyFrames().add( kflo1 );

        logotimeline1.getKeyFrames().add( kfix0 );
        logotimeline1.getKeyFrames().add( kfix1 );
        logotimeline1.getKeyFrames().add( kfso0 );
        logotimeline1.getKeyFrames().add( kfso1 );
        logotimeline1.getKeyFrames().add( kfsx0 );
        logotimeline1.getKeyFrames().add( kfsx1 );

        logotimeline1.getKeyFrames().add( kfbo0 );
        logotimeline1.getKeyFrames().add( kfbo1 );
        logotimeline1.getKeyFrames().add( kfbd0 );
        logotimeline1.getKeyFrames().add( kfbd1 );


        logotimeline1.play();
	}


	//リスナ登録
	public void addMoveScreenListener(OnMoveScreenHandler handler) {
		this.listener = handler;
	}

	public void removeMoveScreenListener() {
		this.listener = null;
	}

	public void onClickedGameStart() {
		if(listener != null) {
			listener.onMoveMainManuScreen();
		}
	}

}
