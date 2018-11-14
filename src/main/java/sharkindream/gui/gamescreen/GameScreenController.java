package sharkindream.gui.gamescreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.util.Duration;
import sharkindream.network.event.OnUpdateGuestHandler;
import sharkindream.network.stream.playerstream.CharacterStatusStream;
import sharkindream.network.stream.playerstream.Guest;
import sharkindream.network.stream.playerstream.GuestStream;
import sharkindream.network.stream.playerstream.PlayCharacter;

public class GameScreenController {
		@FXML
		private AnchorPane playerboxaxis;
		@FXML
		private AnchorPane animationpane;
		@FXML
		private AnchorPane infoscreen;


		private Map<Integer, Guest> guestlist = new HashMap<Integer, Guest>();
		private Map<Integer, PlayCharacter[]> memberlist = new HashMap<>();

		private double multiscroll;
		private boolean isactive = false;
		private double rotationamount = 0;
		private OnUpdateGuestHandler listener;

		private final int Radius = 450;



		@FXML
		void initialize(){
			//アニメーションの用意
			final double Lengthcircle = 2 * Math.PI * Radius;
			((Circle)animationpane.getChildren().get(0)).getStrokeDashArray().add(Lengthcircle);
			((Circle)animationpane.getChildren().get(0)).setStrokeDashOffset(Lengthcircle);
			((Circle)animationpane.getChildren().get(0)).setRadius(150);
			((AnchorPane)animationpane.getChildren().get(1)).setOpacity(0);
			((AnchorPane)animationpane.getChildren().get(2)).setOpacity(0);
			((SVGPath)((AnchorPane)animationpane.getChildren().get(2)).getChildren().get(1)).setOpacity(0);
			((Text)((AnchorPane)animationpane.getChildren().get(2)).getChildren().get(2)).setOpacity(0);

			playstartanimation();
		}

		private void playstartanimation() {

			Timeline tl0 = setTimelineDuration(getDrawRoulette(), Duration.seconds(0.2));


			ParallelTransition paralleltransition = new ParallelTransition(tl0);

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

		private Timeline getDrawRoulette() {
			Timeline timeline = new Timeline();
			Interpolator interpolator = Interpolator.SPLINE(0.7, 0.1, 0.3, 0.9);

			KeyValue kvcl = new KeyValue(((Circle)animationpane.getChildren().get(0)).strokeDashOffsetProperty(), 0, interpolator);
			KeyFrame kfcl = new KeyFrame(Duration.seconds(1.6), kvcl);
			KeyValue kvcr0 = new KeyValue(((Circle)animationpane.getChildren().get(0)).radiusProperty(), 150);
			KeyFrame kfcr0 = new KeyFrame(Duration.seconds(1.4), kvcr0);
			KeyValue kvcr1 = new KeyValue(((Circle)animationpane.getChildren().get(0)).radiusProperty(), 450, interpolator);
			KeyFrame kfcr1 = new KeyFrame(Duration.seconds(2.3), kvcr1);

			KeyValue kvci0 = new KeyValue(((AnchorPane)animationpane.getChildren().get(1)).opacityProperty(), 0);
			KeyFrame kfci0 = new KeyFrame(Duration.seconds(2.4), kvci0);
			KeyValue kvci1 = new KeyValue(((AnchorPane)animationpane.getChildren().get(1)).opacityProperty(), 1);
			KeyFrame kfci1 = new KeyFrame(Duration.seconds(2.5), kvci1);
			KeyValue kvcs0 = new KeyValue(((AnchorPane)animationpane.getChildren().get(2)).opacityProperty(), 0);
			KeyFrame kfcs0 = new KeyFrame(Duration.seconds(2.3), kvcs0);
			KeyValue kvcs1 = new KeyValue(((AnchorPane)animationpane.getChildren().get(2)).opacityProperty(), 1);
			KeyFrame kfcs1 = new KeyFrame(Duration.seconds(2.4), kvcs1);


			KeyValue kvcsr0 = new KeyValue(((Circle)((AnchorPane)animationpane.getChildren().get(2)).getChildren().get(0)).radiusProperty(), 450);
			KeyFrame kfcsr0 = new KeyFrame(Duration.seconds(2.5), kvcsr0);
			KeyValue kvcsr1 = new KeyValue(((Circle)((AnchorPane)animationpane.getChildren().get(2)).getChildren().get(0)).radiusProperty(), 130, interpolator);
			KeyFrame kfcsr1 = new KeyFrame(Duration.seconds(3.3), kvcsr1);

			KeyValue kvcss0 = new KeyValue(((SVGPath)((AnchorPane)animationpane.getChildren().get(2)).getChildren().get(1)).opacityProperty(), 0);
			KeyFrame kfcss0 = new KeyFrame(Duration.seconds(3.5), kvcss0);
			KeyValue kvcss1 = new KeyValue(((SVGPath)((AnchorPane)animationpane.getChildren().get(2)).getChildren().get(1)).opacityProperty(), 1, interpolator);
			KeyFrame kfcss1 = new KeyFrame(Duration.seconds(3.8), kvcss1);
			KeyValue kvcst0 = new KeyValue(((Text)((AnchorPane)animationpane.getChildren().get(2)).getChildren().get(2)).opacityProperty(), 0);
			KeyFrame kfcst0 = new KeyFrame(Duration.seconds(3.5), kvcst0);
			KeyValue kvcst1 = new KeyValue(((Text)((AnchorPane)animationpane.getChildren().get(2)).getChildren().get(2)).opacityProperty(), 1, interpolator);
			KeyFrame kfcst1 = new KeyFrame(Duration.seconds(3.8), kvcst1);

			KeyFrame kfinfo = new KeyFrame(Duration.seconds(3.8), new  javafx.event.EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					AnchorPane infoscreen_;
					try {
						infoscreen_ = (AnchorPane)FXMLLoader.load(getClass().getResource("/sharkindream/gui/gamescreen/infomation/lobby/selectplayertype/SelectPlayerType.fxml"));
						infoscreen.getChildren().add(infoscreen_);
					} catch (IOException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
				}
	        }, null);



			/*
			KeyValue kvcpx0 = new KeyValue(((Circle)animationpane.getChildren().get(0)).centerXProperty(), -500);
			KeyFrame kfcpx0 = new KeyFrame(Duration.seconds(2.5), kvcpx0);
			KeyValue kvcpx1 = new KeyValue(((Circle)animationpane.getChildren().get(0)).centerXProperty(), 0, interpolator);
			KeyFrame kfcpx1 = new KeyFrame(Duration.seconds(3.1), kvcpx1);
			KeyValue kvcpy0 = new KeyValue(((Circle)animationpane.getChildren().get(0)).centerYProperty(), 230);
			KeyFrame kfcpy0 = new KeyFrame(Duration.seconds(2.5), kvcpy0);
			KeyValue kvcpy1 = new KeyValue(((Circle)animationpane.getChildren().get(0)).centerYProperty(), 0, interpolator);
			KeyFrame kfcpy1 = new KeyFrame(Duration.seconds(3.1), kvcpy1);
			*/




			timeline.getKeyFrames().add(kfcl);
			timeline.getKeyFrames().add(kfcr0);
			timeline.getKeyFrames().add(kfcr1);
			timeline.getKeyFrames().add(kfci0);
			timeline.getKeyFrames().add(kfci1);
			timeline.getKeyFrames().add(kfcs0);
			timeline.getKeyFrames().add(kfcs1);
			timeline.getKeyFrames().add(kfcsr0);
			timeline.getKeyFrames().add(kfcsr1);
			timeline.getKeyFrames().add(kfcss0);
			timeline.getKeyFrames().add(kfcss1);
			timeline.getKeyFrames().add(kfcst0);
			timeline.getKeyFrames().add(kfcst1);
			timeline.getKeyFrames().add(kfinfo);

			return timeline;
		}


		public void updateGuestInfo(GuestStream guestst) {

			for(Guest guest: guestst.getList() ){

				if(guestlist.containsKey(Integer.valueOf(guest.playerID))){
					if(!guestlist.get(Integer.valueOf(guest.playerID)).equals(guest)){
						guestlist.replace(Integer.valueOf(guest.playerID), guest);
					}
				}else {
					guestlist.put(Integer.valueOf(guest.playerID), guest);
				}
			}

			if(guestst.getList().size() < guestlist.size()) {
				List<Integer> cloneguest = new ArrayList<Integer>();
				for(Guest guest: guestlist.values()) {
					cloneguest.add(guest.playerID);
				}
				for(Guest guest: guestst.getList()) {
					cloneguest.remove(guest.playerID);
				}
				for(Integer id: cloneguest) {
					guestlist.remove(id);
				}
			}
			updatePlayerBox();





			//-----------------------------------------------
			/*
			AnchorPane playerstatus;
			try {

				playerstatus = (AnchorPane)FXMLLoader.load(getClass().getResource("playerstatus/PlayerStatus.fxml"));
				Text playername = (Text) ((Pane)((Pane)playerstatus.getChildren().get(0)).getChildren().get(0)).getChildren().get(0);
				playername.setText(pname);

				playerbox.getChildren().add(playerstatus);
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}

		//---------------------------------
		public void updateGuestInfo(GuestStream guestst) {

			for(Guest guest: guestst.getList() ){

				if(guestlist.containsKey(Integer.valueOf(guest.playerID))){
					if(!guestlist.get(Integer.valueOf(guest.playerID)).equals(guest)){
						guestlist.replace(Integer.valueOf(guest.playerID), guest);
					}
				}else {
					guestlist.put(Integer.valueOf(guest.playerID), guest);
				}
			}

			if(guestst.getList().size() < guestlist.size()) {
				List<Integer> cloneguest = new ArrayList<Integer>();
				for(Guest guest: guestlist.values()) {
					cloneguest.add(guest.playerID);
				}
				for(Guest guest: guestst.getList()) {
					cloneguest.remove(guest.playerID);
				}
				for(Integer id: cloneguest) {
					guestlist.remove(id);
				}
			}
			updatePlayerBox();
			*/
		}




		public void updatePlayerInfo(CharacterStatusStream Charast) {

			for(Map.Entry<Integer, PlayCharacter[]> memberset: Charast.getmemberlist().entrySet() ){


				if(memberlist.containsKey(memberset.getKey())) {
					memberlist.replace(memberset.getKey(), memberset.getValue());
				}else {
					memberlist.put(memberset.getKey(), memberset.getValue());
				}
			}

			updatePlayerBox();
		}




		private void updatePlayerBox() {

			for(Map.Entry<Integer, Guest> guestset : guestlist.entrySet()) {

				Platform.runLater( () -> {

					((Text)((AnchorPane)((AnchorPane)playerboxaxis.getChildren().get(guestset.getKey())).getChildren().get(1)).getChildren().get(1)).setText(guestset.getValue().getName().substring(0, 1));
					((Text)((AnchorPane)((AnchorPane)playerboxaxis.getChildren().get(guestset.getKey())).getChildren().get(1)).getChildren().get(2)).setText(guestset.getValue().getName().substring(1));

					if(((Guest)guestset.getValue()).isready) {
						((SVGPath)((AnchorPane)playerboxaxis.getChildren().get(guestset.getKey())).getChildren().get(0)).setFill((Paint.valueOf("#0E7AC4")));
					}else {
						((SVGPath)((AnchorPane)playerboxaxis.getChildren().get(guestset.getKey())).getChildren().get(0)).setFill((Paint.valueOf("#EE6557")));
					}

				});
			}

		}

		@FXML
		public void onScroll(ScrollEvent e) {
			multiscroll = e.getMultiplierY();
			rotationamount += e.getDeltaY() / 6.0;
			System.out.println(e.getMultiplierX());

			playerboxaxis.setRotate(rotationamount);
		}



		//リスナ登録
		public void addAllReadyListener(OnUpdateGuestHandler handler) {
			this.listener = handler;
		}

		public void removeAllReadyListener() {
			this.listener = null;
		}

}
