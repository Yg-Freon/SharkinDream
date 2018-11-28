package sharkindream.gui.gamescreen.infomation.gameplay;

import java.io.IOException;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;
import sharkindream.gui.gamescreen.infomation.gameplay.nametile.NameTileController;
import sharkindream.network.stream.playerstream.PlayerStatus;

public class GamePlayScreenController {


	@FXML
	private AnchorPane statuspane;
	@FXML
	private SVGPath fpath;
	@FXML
	private SVGPath tpath;
	@FXML
	private SVGPath ellpath;


	NameTileController controller0;

	@FXML
	void initialize() {

		ellpath.getStrokeDashArray().add(1000d);
		ellpath.strokeDashOffsetProperty().set(1000);

		statuspane.getChildren().add(new AnchorPane());
	}

	public void openPlayerStatus(String name, PlayerStatus player) {

		Platform.runLater( () -> {
			KeyFrame kfs = new KeyFrame(Duration.ZERO, new  javafx.event.EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					switchPlayerStatus(name, player);
				}
			}, null);


			KeyValue kved0 = new KeyValue(ellpath.strokeDashOffsetProperty(), 1000);
			KeyFrame kfed0 = new KeyFrame(Duration.seconds(0.5), kved0);
			KeyValue kved1 = new KeyValue(ellpath.strokeDashOffsetProperty(), 0);
			KeyFrame kfed1 = new KeyFrame(Duration.seconds(1.5), kved1);


			Timeline timeline = new Timeline();
			timeline.getKeyFrames().add(kfed0);
			timeline.getKeyFrames().add(kfed1);
			timeline.getKeyFrames().add(kfs);

			timeline.play();
		});
	}


	public void switchPlayerStatus(String name, PlayerStatus player) {
		Interpolator interpolator = Interpolator.SPLINE(0.7, 0.1, 0.3, 0.9);
		if(controller0 != null) {
			controller0.closeNameTile();
		}

		FXMLLoader fxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/gamescreen/infomation/gameplay/nametile/NameTile.fxml"));
		AnchorPane namescreen;
		try {
			namescreen = fxml.load();
			controller0 = (NameTileController)fxml.getController();
			controller0.setPlayerInfo(name, player);
			statuspane.getChildren().add(namescreen);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		PathTransition pathTransition0 = new PathTransition();
		pathTransition0.setNode(statuspane.getChildren().get(0));
		pathTransition0.setDuration(Duration.seconds(1.4));
		pathTransition0.setPath(tpath);
		pathTransition0.setInterpolator(interpolator);
		pathTransition0.setDelay(Duration.seconds(1.0));

		PathTransition pathTransition1 = new PathTransition();
		pathTransition1.setNode(statuspane.getChildren().get(1));
		pathTransition1.setDuration(Duration.seconds(1.8));
		pathTransition1.setPath(fpath);
		pathTransition1.setInterpolator(interpolator);
		pathTransition1.setDelay(Duration.seconds(1.0));

		Timeline timeline = new Timeline();




		KeyFrame kfremove = new KeyFrame(Duration.seconds(2.5), new  javafx.event.EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				statuspane.getChildren().remove(0);
			}
        }, null);

		KeyFrame kfopen = new KeyFrame(Duration.seconds(3.5), new  javafx.event.EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(controller0 != null) {
					controller0.openNameTile();
				}
			}
		}, null);


		timeline.getKeyFrames().add(kfremove);
		timeline.getKeyFrames().add(kfopen);

		ParallelTransition paralleltransition = new ParallelTransition(pathTransition0, pathTransition1, timeline);
		paralleltransition.play();
	}

	public void closePlayerStatus() {
		Interpolator interpolator = Interpolator.SPLINE(0.7, 0.1, 0.3, 0.9);
		if(controller0 != null) {
			controller0.closeNameTile();
		}
		statuspane.getChildren().add(new AnchorPane());

		PathTransition pathTransition0 = new PathTransition();
		pathTransition0.setNode(statuspane.getChildren().get(0));
		pathTransition0.setDuration(Duration.seconds(1.4));
		pathTransition0.setPath(tpath);
		pathTransition0.setInterpolator(interpolator);
		pathTransition0.setDelay(Duration.seconds(1.0));

		PathTransition pathTransition1 = new PathTransition();
		pathTransition1.setNode(statuspane.getChildren().get(1));
		pathTransition1.setDuration(Duration.seconds(1.8));
		pathTransition1.setPath(fpath);
		pathTransition1.setInterpolator(interpolator);
		pathTransition1.setDelay(Duration.seconds(1.0));
		
		

		Timeline timeline = new Timeline();




		KeyFrame kfremove = new KeyFrame(Duration.seconds(2.5), new  javafx.event.EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				statuspane.getChildren().remove(0);
			}
        }, null);
		
		timeline.getKeyFrames().add(kfremove);
		
		ParallelTransition paralleltransition = new ParallelTransition(pathTransition0, pathTransition1, timeline);
		paralleltransition.play();
	}
}
