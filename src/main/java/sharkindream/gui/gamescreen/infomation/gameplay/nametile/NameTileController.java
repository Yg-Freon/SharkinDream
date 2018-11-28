package sharkindream.gui.gamescreen.infomation.gameplay.nametile;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.util.Duration;
import sharkindream.network.stream.playerstream.PlayerStatus;

public class NameTileController {


	@FXML
	private AnchorPane statustile;
	@FXML
	private Line statusline;

	@FXML
	private Text firstnametext;
	@FXML
	private Text othernametext;
	@FXML
	private AnchorPane playerpane;
	@FXML
	private AnchorPane minion0pane;
	@FXML
	private AnchorPane minion1pane;

	@FXML
	void initialize() {
		Rectangle trect = new Rectangle();
		trect.widthProperty().bind(statustile.prefWidthProperty());
		trect.heightProperty().bind(statustile.prefHeightProperty());
		statustile.setClip(trect);

		statustile.setPrefWidth(0);
		statusline.setEndX(0);
		statusline.setOpacity(0);
	}

	public void openNameTile() {

		Timeline timeline = new Timeline();
		Interpolator interpolator = Interpolator.SPLINE(0.7, 0.1, 0.3, 0.9);

		statusline.setOpacity(1);

		KeyValue kvp0 = new KeyValue(statustile.prefWidthProperty(), 0);
		KeyFrame kfp0 = new KeyFrame(Duration.ZERO, kvp0);
		KeyValue kvp1 = new KeyValue(statustile.prefWidthProperty(), 300, interpolator);
		KeyFrame kfp1 = new KeyFrame(Duration.seconds(0.5), kvp1);

		KeyValue kvl0 = new KeyValue(statusline.endXProperty(), 0);
		KeyFrame kfl0 = new KeyFrame(Duration.ZERO, kvl0);
		KeyValue kvl1 = new KeyValue(statusline.endXProperty(), 350, interpolator);
		KeyFrame kfl1 = new KeyFrame(Duration.seconds(0.5), kvl1);

		timeline.getKeyFrames().add(kfp0);
		timeline.getKeyFrames().add(kfp1);
		timeline.getKeyFrames().add(kfl0);
		timeline.getKeyFrames().add(kfl1);

		timeline.play();

	}

	public void setPlayerInfo(String name, PlayerStatus player) {
		firstnametext.setText(name.substring(0, 1));
		othernametext.setText(name.substring(1));

		((SVGPath)playerpane.getChildren().get(0)).setFill(Paint.valueOf(player.getPlayer().getType().getColor()));
		((SVGPath)minion0pane.getChildren().get(0)).setFill(Paint.valueOf(player.getminion(0).getType().getColor()));
		((SVGPath)minion1pane.getChildren().get(0)).setFill(Paint.valueOf(player.getminion(1).getType().getColor()));

		((AnchorPane)minion0pane.getChildren().get(1)).getChildren().add(player.getminion(0).getCharacterClass().getMinionclass().geticon(Paint.valueOf("#f4f4f4"), Paint.valueOf(player.getminion(0).getType().getColor())));
		((AnchorPane)minion1pane.getChildren().get(1)).getChildren().add(player.getminion(1).getCharacterClass().getMinionclass().geticon(Paint.valueOf("#f4f4f4"), Paint.valueOf(player.getminion(1).getType().getColor())));

	}

	public void closeNameTile() {
		Timeline timeline = new Timeline();
		Interpolator interpolator = Interpolator.SPLINE(0.7, 0.1, 0.3, 0.9);

		statusline.setOpacity(1);

		KeyValue kvp0 = new KeyValue(statustile.prefWidthProperty(), 300);
		KeyFrame kfp0 = new KeyFrame(Duration.ZERO, kvp0);
		KeyValue kvp1 = new KeyValue(statustile.prefWidthProperty(), 0, interpolator);
		KeyFrame kfp1 = new KeyFrame(Duration.seconds(0.5), kvp1);

		KeyValue kvl0 = new KeyValue(statusline.endXProperty(), 350);
		KeyFrame kfl0 = new KeyFrame(Duration.ZERO, kvl0);
		KeyValue kvl1 = new KeyValue(statusline.endXProperty(), 0, interpolator);
		KeyFrame kfl1 = new KeyFrame(Duration.seconds(0.5), kvl1);

		KeyValue kvlo0 = new KeyValue(statusline.opacityProperty(), 1);
		KeyFrame kflo0 = new KeyFrame(Duration.seconds(0.4), kvlo0);

		KeyValue kvlo1 = new KeyValue(statusline.opacityProperty(), 0);
		KeyFrame kflo1 = new KeyFrame(Duration.seconds(0.5), kvlo1);

		timeline.getKeyFrames().add(kfp0);
		timeline.getKeyFrames().add(kfp1);
		timeline.getKeyFrames().add(kfl0);
		timeline.getKeyFrames().add(kfl1);
		timeline.getKeyFrames().add(kflo0);
		timeline.getKeyFrames().add(kflo1);

		timeline.play();
	}
}
