package sharkindream.gui.gamescreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.util.Duration;
import sharkindream.actioncard.ActionCard;
import sharkindream.config.Setting;
import sharkindream.gui.cardmodel.CardModelController;
import sharkindream.gui.event.OnMoveScreenHandler;
import sharkindream.gui.gamescreen.infomation.gameplay.GamePlayScreenController;
import sharkindream.gui.gamescreen.infomation.lobby.selectplayertype.SelectPlayerTypeController;
import sharkindream.network.client.Client;
import sharkindream.network.client.ClientGamePlayFlow;
import sharkindream.network.client.ClientGamePlayFlow.AttackAction;
import sharkindream.network.event.OnUpdateGuestHandler;
import sharkindream.network.stream.playerstream.Guest;
import sharkindream.network.stream.playerstream.GuestListData;
import sharkindream.network.stream.playerstream.PlayCharacter;
import sharkindream.network.stream.playerstream.PlayerStatus;

public class GameScreenController {
		@FXML
		private AnchorPane playerboxaxis;
		@FXML
		private AnchorPane animationpane;
		@FXML
		private AnchorPane infoscreen;
		@FXML
		private AnchorPane turninfoscreen;
		@FXML
		private AnchorPane attackpane;
		@FXML
		private AnchorPane drawpane;
		@FXML
		private AnchorPane restpane;
		@FXML
		private Text attackturntext;
		@FXML
		private AnchorPane cardfieldpane;

		@FXML
		private AnchorPane playerHPpane;
		@FXML
		private AnchorPane minion0HPpane;
		@FXML
		private AnchorPane minion1HPpane;
		@FXML
		private HBox buttonlist;
		@FXML
		private VBox partymenberlist;


		@FXML
		private AnchorPane player0;
		@FXML
		private AnchorPane player1;
		@FXML
		private AnchorPane player2;
		@FXML
		private AnchorPane player3;
		@FXML
		private AnchorPane player4;
		@FXML
		private AnchorPane player5;
		@FXML
		private AnchorPane player6;
		@FXML
		private AnchorPane player7;
		@FXML
		private AnchorPane player8;
		@FXML
		private AnchorPane player9;
		@FXML
		private AnchorPane player10;

		@FXML
		private AnchorPane card0;
		@FXML
		private AnchorPane card1;
		@FXML
		private AnchorPane card2;
		@FXML
		private AnchorPane card3;

		@FXML
		private AnchorPane playerpane;
		@FXML
		private AnchorPane minion0pane;
		@FXML
		private AnchorPane minion1pane;




		private static AnchorPane info;

		private Rectangle[] prectlist = new Rectangle[11];
		private List<ActionCard> hand = new ArrayList<>();
		private int activecard = -1;
		private int activepartymember = -1;
		private int activeplayer = -1;
		private boolean hasadvancecard = false;
		private boolean animationfinished = false;

		private int attackingminion = 0;
		private Guest attackedplayer;


		private Map<Integer, Guest> guestlist = new HashMap<Integer, Guest>();
		private Map<Integer, PlayerStatus> playerStatuslist = new HashMap<>();

		private double multiscroll;
		private ClientGamePlayFlow.AttackAction attackaction = AttackAction.None;
		private double rotationamount = 0;
		private OnUpdateGuestHandler listener;
		private GamePlayScreenController gameplayscreencontroller;

		private final int Radius = 450;

		@FXML
		void initialize(){

			Arc[] parclist = new Arc[11];
			for(int i=0; i < Setting.MaxPlayernum; ++ i) {

				//TODO:あたり判定を変えたい
				parclist[i] = new Arc();
				parclist[i].radiusXProperty().bind(((Arc)((AnchorPane)playerboxaxis.getChildren().get(i)).getChildren().get(0)).radiusXProperty());
				parclist[i].radiusYProperty().bind(((Arc)((AnchorPane)playerboxaxis.getChildren().get(i)).getChildren().get(0)).radiusYProperty());
				parclist[i].startAngleProperty().bind(((Arc)((AnchorPane)playerboxaxis.getChildren().get(i)).getChildren().get(0)).startAngleProperty());
				parclist[i].lengthProperty().bind(((Arc)((AnchorPane)playerboxaxis.getChildren().get(i)).getChildren().get(0)).lengthProperty());
				parclist[i].setType(ArcType.ROUND);
				((Arc)((AnchorPane)playerboxaxis.getChildren().get(i)).getChildren().get(0)).setClip(parclist[i]);


				prectlist[i] = new Rectangle();
				prectlist[i].setWidth(330);
				prectlist[i].setHeight(250);
				prectlist[i].setTranslateX(-450);
				prectlist[i].setTranslateY(-125);
				//prectlist[i].layoutXProperty().set(-60);
				((Arc)((AnchorPane)playerboxaxis.getChildren().get(i)).getChildren().get(1)).setClip(prectlist[i]);
				((Arc)((AnchorPane)playerboxaxis.getChildren().get(i)).getChildren().get(1)).setFill(Paint.valueOf("#cdcdcd"));
			}


			Rectangle playertrect = new Rectangle();
			playertrect.widthProperty().bind(playerHPpane.widthProperty());
			playertrect.heightProperty().bind(playerHPpane.heightProperty());
			playertrect.layoutYProperty().set(100);
			playerHPpane.setClip(playertrect);


			Rectangle minion0trect = new Rectangle();
			minion0trect.widthProperty().bind(minion0HPpane.widthProperty());
			minion0trect.heightProperty().bind(minion0HPpane.heightProperty());
			minion0trect.layoutYProperty().set(100);
			minion0HPpane.setClip(minion0trect);

			Rectangle minion1trect = new Rectangle();
			minion1trect.widthProperty().bind(minion1HPpane.widthProperty());
			minion1trect.heightProperty().bind(minion1HPpane.heightProperty());
			minion1trect.layoutYProperty().set(100);
			minion1HPpane.setClip(minion1trect);

			((TilePane)((AnchorPane)playerpane.getChildren().get(0)).getChildren().get(1)).setOpacity(0);
			((TilePane)((AnchorPane)playerpane.getChildren().get(1)).getChildren().get(1)).setOpacity(0);
			((TilePane)((AnchorPane)minion0pane.getChildren().get(0)).getChildren().get(1)).setOpacity(0);
			((TilePane)((AnchorPane)minion0pane.getChildren().get(1)).getChildren().get(1)).setOpacity(0);
			((TilePane)((AnchorPane)minion1pane.getChildren().get(0)).getChildren().get(1)).setOpacity(0);
			((TilePane)((AnchorPane)minion1pane.getChildren().get(1)).getChildren().get(1)).setOpacity(0);

			((Text)((TilePane)((AnchorPane)playerpane.getChildren().get(0)).getChildren().get(1)).getChildren().get(0)).setText(String.valueOf(0));
			((Text)((TilePane)((AnchorPane)playerpane.getChildren().get(1)).getChildren().get(1)).getChildren().get(0)).setText(String.valueOf(0));
			((Text)((TilePane)((AnchorPane)minion0pane.getChildren().get(0)).getChildren().get(1)).getChildren().get(0)).setText(String.valueOf(0));
			((Text)((TilePane)((AnchorPane)minion0pane.getChildren().get(1)).getChildren().get(1)).getChildren().get(0)).setText(String.valueOf(0));
			((Text)((TilePane)((AnchorPane)minion1pane.getChildren().get(0)).getChildren().get(1)).getChildren().get(0)).setText(String.valueOf(0));
			((Text)((TilePane)((AnchorPane)minion1pane.getChildren().get(1)).getChildren().get(1)).getChildren().get(0)).setText(String.valueOf(0));

			hand.add(new ActionCard());
			hand.add(new ActionCard());
			hand.add(new ActionCard());
			hand.add(new ActionCard());
			info = infoscreen;

			//アニメーションの用意
			final double Lengthcircle = 2 * Math.PI * Radius;
			((Circle)animationpane.getChildren().get(0)).getStrokeDashArray().add(Lengthcircle);
			((Circle)animationpane.getChildren().get(0)).setStrokeDashOffset(Lengthcircle);
			//((Circle)animationpane.getChildren().get(0)).setRadius(150);
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

			/*
			KeyValue kvcr0 = new KeyValue(((Circle)animationpane.getChildren().get(0)).radiusProperty(), 150);
			KeyFrame kfcr0 = new KeyFrame(Duration.seconds(1.4), kvcr0);
			KeyValue kvcr1 = new KeyValue(((Circle)animationpane.getChildren().get(0)).radiusProperty(), 450, interpolator);
			KeyFrame kfcr1 = new KeyFrame(Duration.seconds(2.3), kvcr1);
			*/

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

					try {
						FXMLLoader fxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/gamescreen/infomation/lobby/selectplayertype/SelectPlayerType.fxml"));
						AnchorPane infoscreen = fxml.load();
						((SelectPlayerTypeController)fxml.getController()).addMoveScreenListener(new OnMoveScreenHandler());
						switchInfoScreen(infoscreen);
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


		public void updateGuestInfo(GuestListData guestst) {

			for(Guest guest: guestst.getlist() ){

				if(guestlist.containsKey(Integer.valueOf(guest.playerID))){
					if(!guestlist.get(Integer.valueOf(guest.playerID)).equals(guest)){
						guestlist.replace(Integer.valueOf(guest.playerID), guest);
					}
				}else {
					guestlist.put(Integer.valueOf(guest.playerID), guest);
				}
			}

			if(guestst.getlist().size() < guestlist.size()) {
				List<Integer> cloneguest = new ArrayList<Integer>();
				for(Guest guest: guestlist.values()) {
					cloneguest.add(guest.playerID);
				}
				for(Guest guest: guestst.getlist()) {
					cloneguest.remove(guest.playerID);
				}
				for(Integer id: cloneguest) {
					guestlist.remove(id);
				}
			}
			updatePlayerBox();
		}




		public void initPlayerInfo(Map<Integer, PlayerStatus> playerinfo) {

			this.playerStatuslist = playerinfo;

		}

		public void startGameAnimation() {
			//ゲーム開始のアニメーション

			//ルーレットの色を変更
			Timeline tlr = getStartGameRouletteTimeline();
			Timeline tlpsi = getStartPlayerStatusInfoTImeline();






			//infoscreenを変更
			//左側も色を変更
			//下側はカード配布のアニメーション

			//----------------------------------------------------------------------

			Timeline tlfin = new Timeline();
			KeyFrame kffin = new KeyFrame(Duration.seconds(2.5), new  javafx.event.EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					setanimationfinished(true);
				}
	        }, null);

			tlfin.getKeyFrames().add(kffin);

			//-----------------------------------------------------------------------

			ParallelTransition paralleltransition = new ParallelTransition(tlr, tlfin, tlpsi);
			paralleltransition.play();


			Platform.runLater( () -> {
				try {
					updateHand(card0, playerStatuslist.get(Client.getMyStatus().playerID).getHand(0));
					updateHand(card1, playerStatuslist.get(Client.getMyStatus().playerID).getHand(1));
					updateHand(card2, playerStatuslist.get(Client.getMyStatus().playerID).getHand(2));
					
					
					
					FXMLLoader fxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/gamescreen/infomation/gameplay/GamePlayScreen.fxml"));
					AnchorPane infoscreen = fxml.load();
					gameplayscreencontroller =(GamePlayScreenController)fxml.getController();
					gameplayscreencontroller.openPlayerStatus(Client.getMyStatus().getName(), playerStatuslist.get(Client.getMyStatus().playerID));
					switchInfoScreen(infoscreen);
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			});
		}

		private Timeline getStartGameRouletteTimeline() {

			Timeline timeline = new Timeline();


			for(Map.Entry<Integer, PlayerStatus> playerlist : this.playerStatuslist.entrySet()) {
				for(int i=0; i<this.playerStatuslist.size(); ++i) {

					if(playerlist.getKey().equals(this.guestlist.get(i).playerID)) {
						((Arc)((AnchorPane)playerboxaxis.getChildren().get(i)).getChildren().get(1)).setFill(Paint.valueOf(playerlist.getValue().getPlayer().getType().getColor()));

						KeyValue kvp0 = new KeyValue(prectlist[i].layoutXProperty(), -330 * 1.0);
						KeyFrame kfp0 = new KeyFrame(Duration.ZERO, kvp0);
						KeyValue kvp1 = new KeyValue(prectlist[i].layoutXProperty(), -330 * 0.0);
						KeyFrame kfp1 = new KeyFrame(Duration.seconds(2.5), kvp1);



						timeline.getKeyFrames().add(kfp0);
						timeline.getKeyFrames().add(kfp1);

						break;
					}
				}
			}


			return timeline;
		}

		private Timeline getStartPlayerStatusInfoTImeline() {

			((SVGPath)playerHPpane.getChildren().get(0)).setFill(Paint.valueOf(playerStatuslist.get(Client.getMyStatus().playerID).getPlayer().getType().getColor()));
			((SVGPath)minion0HPpane.getChildren().get(0)).setFill(Paint.valueOf(playerStatuslist.get(Client.getMyStatus().playerID).getminion(0).getType().getColor()));
			((SVGPath)minion1HPpane.getChildren().get(0)).setFill(Paint.valueOf(playerStatuslist.get(Client.getMyStatus().playerID).getminion(1).getType().getColor()));

			Timeline timeline = new Timeline();

			KeyValue kvpt0o0 = new KeyValue(((TilePane)((AnchorPane)playerpane.getChildren().get(0)).getChildren().get(1)).opacityProperty(), 0);
			KeyFrame kfpt0o0 = new KeyFrame(Duration.ZERO, kvpt0o0);
			KeyValue kvpt0o1 = new KeyValue(((TilePane)((AnchorPane)playerpane.getChildren().get(0)).getChildren().get(1)).opacityProperty(), 1);
			KeyFrame kfpt0o1 = new KeyFrame(Duration.seconds(1.0), kvpt0o1);

			KeyValue kvpt1o0 = new KeyValue(((TilePane)((AnchorPane)playerpane.getChildren().get(1)).getChildren().get(1)).opacityProperty(), 0);
			KeyFrame kfpt1o0 = new KeyFrame(Duration.ZERO, kvpt1o0);
			KeyValue kvpt1o1 = new KeyValue(((TilePane)((AnchorPane)playerpane.getChildren().get(1)).getChildren().get(1)).opacityProperty(), 1);
			KeyFrame kfpt1o1 = new KeyFrame(Duration.seconds(1.0), kvpt1o1);


			KeyValue kvm0t0o0 = new KeyValue(((TilePane)((AnchorPane)minion0pane.getChildren().get(0)).getChildren().get(1)).opacityProperty(), 0);
			KeyFrame kfm0t0o0 = new KeyFrame(Duration.ZERO, kvm0t0o0);
			KeyValue kvm0t0o1 = new KeyValue(((TilePane)((AnchorPane)minion0pane.getChildren().get(0)).getChildren().get(1)).opacityProperty(), 1);
			KeyFrame kfm0t0o1 = new KeyFrame(Duration.seconds(1.0), kvm0t0o1);

			KeyValue kvm0t1o0 = new KeyValue(((TilePane)((AnchorPane)minion0pane.getChildren().get(1)).getChildren().get(1)).opacityProperty(), 0);
			KeyFrame kfm0t1o0 = new KeyFrame(Duration.ZERO, kvm0t1o0);
			KeyValue kvm0t1o1 = new KeyValue(((TilePane)((AnchorPane)minion0pane.getChildren().get(1)).getChildren().get(1)).opacityProperty(), 1);
			KeyFrame kfm0t1o1 = new KeyFrame(Duration.seconds(1.0), kvm0t1o1);


			KeyValue kvm1t0o0 = new KeyValue(((TilePane)((AnchorPane)minion1pane.getChildren().get(0)).getChildren().get(1)).opacityProperty(), 0);
			KeyFrame kfm1t0o0 = new KeyFrame(Duration.ZERO, kvm1t0o0);
			KeyValue kvm1t0o1 = new KeyValue(((TilePane)((AnchorPane)minion1pane.getChildren().get(0)).getChildren().get(1)).opacityProperty(), 1);
			KeyFrame kfm1t0o1 = new KeyFrame(Duration.seconds(1.0), kvm1t0o1);

			KeyValue kvm1t1o0 = new KeyValue(((TilePane)((AnchorPane)minion1pane.getChildren().get(1)).getChildren().get(1)).opacityProperty(), 0);
			KeyFrame kfm1t1o0 = new KeyFrame(Duration.ZERO, kvm1t1o0);
			KeyValue kvm1t1o1 = new KeyValue(((TilePane)((AnchorPane)minion1pane.getChildren().get(1)).getChildren().get(1)).opacityProperty(), 1);
			KeyFrame kfm1t1o1 = new KeyFrame(Duration.seconds(1.0), kvm1t1o1);

			KeyFrame kfpsu = new KeyFrame(Duration.ZERO, new  javafx.event.EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					updateHPPaneTimeline(playerHPpane, playerStatuslist.get(Client.getMyStatus().playerID).getPlayer());
					HPTextAnimation hpTextAnimation = new HPTextAnimation(playerpane,  400);
					hpTextAnimation.start();
				}
	        }, null);

			KeyFrame kfm0su = new KeyFrame(Duration.ZERO, new  javafx.event.EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					updateHPPaneTimeline(minion0HPpane, playerStatuslist.get(Client.getMyStatus().playerID).getminion(0));
					HPTextAnimation hpTextAnimation = new HPTextAnimation(minion0pane,  playerStatuslist.get(Client.getMyStatus().playerID).getminion(0).getMaxHP());
					hpTextAnimation.start();
				}
	        }, null);

			KeyFrame kfm1su = new KeyFrame(Duration.ZERO, new  javafx.event.EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					updateHPPaneTimeline(minion1HPpane, playerStatuslist.get(Client.getMyStatus().playerID).getminion(1));
					HPTextAnimation hpTextAnimation = new HPTextAnimation(minion1pane,  playerStatuslist.get(Client.getMyStatus().playerID).getminion(1).getMaxHP());
					hpTextAnimation.start();
				}
	        }, null);


			timeline.getKeyFrames().add(kfpt0o0);
			timeline.getKeyFrames().add(kfpt0o1);
			timeline.getKeyFrames().add(kfpt1o0);
			timeline.getKeyFrames().add(kfpt1o1);
			timeline.getKeyFrames().add(kfm0t0o0);
			timeline.getKeyFrames().add(kfm0t0o1);
			timeline.getKeyFrames().add(kfm0t1o0);
			timeline.getKeyFrames().add(kfm0t1o1);
			timeline.getKeyFrames().add(kfm1t0o0);
			timeline.getKeyFrames().add(kfm1t0o1);
			timeline.getKeyFrames().add(kfm1t1o0);
			timeline.getKeyFrames().add(kfm1t1o1);
			timeline.getKeyFrames().add(kfpsu);
			timeline.getKeyFrames().add(kfm0su);
			timeline.getKeyFrames().add(kfm1su);

			return timeline;
		}

		private void updateHPPaneTimeline(AnchorPane pane, PlayCharacter character) {
			Timeline timeline = new Timeline();

			double hp = ((double)(character.getMaxHP() -  character.getHP()) / (double)character.getMaxHP()) * 100;


			KeyValue kv = new KeyValue(pane.getClip().layoutYProperty(), 0);
			KeyFrame kf = new KeyFrame(Duration.seconds(1.5), kv);

			timeline.getKeyFrames().add(kf);

			timeline.play();
		}

		private void updateHand(AnchorPane targetpane, ActionCard card) {
			((AnchorPane)targetpane.getChildren().get(0)).getChildren().clear();
			FXMLLoader cardfxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/cardmodel/CardModel.fxml"));
			AnchorPane cardmodel;
			try {
				cardmodel = cardfxml.load();
				CardModelController controller = (CardModelController)cardfxml.getController();
				controller.setCardInfo(card);
				((AnchorPane)targetpane.getChildren().get(0)).getChildren().clear();
				((AnchorPane)targetpane.getChildren().get(0)).getChildren().add(cardmodel);
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}

		}

		private class HPTextAnimation extends AnimationTimer{
	        // アニメーション対象のノード
	        private AnchorPane    parentnode    = null;
	        private Text textnode0 = null;
	        private Text textnode1 = null;

	        // テキスト・アニメーションのコマ送り間隔（ミリ秒）
	        private long    duration    = 1;

	        // テキスト・アニメーションの開始時間
	        private long    startTime   = 0;
	        private int startHP;
	        private int endHP;

	        public HPTextAnimation( AnchorPane node , int endHP)
	        {
	            // 内部変数の初期化

	             this.parentnode      = node;
	             this.textnode0 = (Text)((TilePane)((AnchorPane)this.parentnode.getChildren().get(0)).getChildren().get(1)).getChildren().get(0);
	             this.textnode1 = (Text)((TilePane)((AnchorPane)this.parentnode.getChildren().get(1)).getChildren().get(1)).getChildren().get(0);
	             this.startHP = Integer.parseInt(((Text)((TilePane)((AnchorPane)this.parentnode.getChildren().get(0)).getChildren().get(1)).getChildren().get(0)).getText());
	             this.endHP = endHP;
	             if(Math.abs(startHP - endHP) != 0) {
	            	 this.duration = (int)(1.5 / Math.abs(startHP - endHP) * 1000000000);
	             }
	        }

	        @Override
	        public void handle(long t)
	        {
	            // アニメーション開始時間を取得
	            if( startTime == 0 ){ startTime = t; }

	            // アニメーション・タイムスタンプを初期化
	            int hp = (int)((t - startTime) / duration);
	            int nowHP = (int)Integer.parseInt(textnode0.getText());
	            if( Math.abs(startHP - endHP) > nowHP) {
	            	if(nowHP < endHP) {
	            		textnode0.setText(String.valueOf(startHP + hp));
	            		textnode1.setText(String.valueOf(startHP + hp));

	            	}else if(nowHP > endHP) {
	            		textnode0.setText(String.valueOf(startHP - hp));
	            		textnode1.setText(String.valueOf(startHP - hp));
	            	}
	            }else {

	            	textnode0.setText(String.valueOf(endHP));
            		textnode1.setText(String.valueOf(endHP));
	            	startTime = 0;
	            	stop();
	            }
	        }
	    }


		private void updatePlayerBox() {

			for(Map.Entry<Integer, Guest> guestset : guestlist.entrySet()) {

				Platform.runLater( () -> {

					((Text)((AnchorPane)((AnchorPane)playerboxaxis.getChildren().get(guestset.getKey())).getChildren().get(2)).getChildren().get(1)).setText(guestset.getValue().getName().substring(0, 1));
					((Text)((AnchorPane)((AnchorPane)playerboxaxis.getChildren().get(guestset.getKey())).getChildren().get(2)).getChildren().get(2)).setText(guestset.getValue().getName().substring(1));

					//準備完了の表示
					/*
					if(((Guest)guestset.getValue()).isready) {
						((Arc)((AnchorPane)playerboxaxis.getChildren().get(guestset.getKey())).getChildren().get(1)).setFill((Paint.valueOf("#0E7AC4")));
					}else {
						((Arc)((AnchorPane)playerboxaxis.getChildren().get(guestset.getKey())).getChildren().get(1)).setFill((Paint.valueOf("#EE6557")));
					}
					*/
				});
			}
		}

		@FXML
		private void onClickPlayerStatus(MouseEvent e) {
			switch(((AnchorPane)((Arc)e.getSource()).getParent()).getId()) {

			case "player0":
				if(guestlist.size() > 0) {
					activeplayer = (activeplayer == 0) ? -1: 0;
				}
				break;

			case "player1":
				if(guestlist.size() > 1) {
					activeplayer = (activeplayer == 1) ? -1: 1;
				}
				break;

			case "player2":
				if(guestlist.size() > 2) {
					activeplayer = (activeplayer == 2) ? -1: 2;
				}
				break;

			case "player3":
				if(guestlist.size() > 3) {
					activeplayer = (activeplayer == 3) ? -1: 3;
				}
				break;

			case "player4":
				if(guestlist.size() > 4) {
					activeplayer = (activeplayer == 4) ? -1: 4;
				}
				break;

			case "player5":
				if(guestlist.size() > 5) {
					activeplayer = (activeplayer == 5) ? -1: 5;
				}
				break;

			case "player6":
				if(guestlist.size() > 6) {
					activeplayer = (activeplayer == 6) ? -1: 6;
				}
				break;

			case "player7":
				if(guestlist.size() > 7) {
					activeplayer = (activeplayer == 7) ? -1: 7;
				}
				break;

			case "player8":
				if(guestlist.size() > 8) {
					activeplayer = (activeplayer == 8) ? -1: 8;
				}
				break;

			case "player9":
				if(guestlist.size() > 9) {
					activeplayer = (activeplayer == 9) ? -1: 9;
				}
				break;

			case "player10":
				if(guestlist.size() > 10) {
					activeplayer = (activeplayer == 10) ? -1: 10;
				}
				break;
			}

			updatePlayerBoxSize();
		}

		private void updatePlayerBoxSize() {
			ScaleTransition[] stlist = new ScaleTransition[11];
			for(int i=0; i<11; ++i) {


				float fscale = (((AnchorPane)playerboxaxis.getChildren().get(i)).getScaleX() < 1.1f) ? 1.0f : 1.1f;
				float tscale = (activeplayer == i) ? 1.1f : 1.0f;

				ScaleTransition scaletransition = new ScaleTransition();
				scaletransition.setNode((AnchorPane)playerboxaxis.getChildren().get(i));
				scaletransition.setFromX(fscale); scaletransition.setFromY(fscale);
				scaletransition.setToX(tscale);   scaletransition.setToY(tscale);
				scaletransition.setDuration(Duration.seconds(0.2));
				scaletransition.setInterpolator(Interpolator.EASE_BOTH);

				stlist[i] = scaletransition;


				if(gameplayscreencontroller != null) {
					if(activeplayer == i) {
						gameplayscreencontroller.switchPlayerStatus(guestlist.get(i).getName(), playerStatuslist.get(i));
					}else if(activeplayer == -1) {
						gameplayscreencontroller.closePlayerStatus();
					}

				}
			}
			ParallelTransition pt = new ParallelTransition(stlist[0], stlist[1], stlist[2], stlist[3], stlist[4], stlist[5], stlist[6], stlist[7], stlist[8], stlist[9], stlist[10] );
			pt.play();
		}

		@FXML
		private void onClickPartyMenber(MouseEvent e) {

			switch( ((AnchorPane)((AnchorPane)((SVGPath)e.getSource()).getParent()).getParent()).getId() ) {
			case "playerpane":
				activepartymember = (activepartymember == 0) ? -1 : 0;
				break;

			case "minion0pane":
				activepartymember = (activepartymember == 1) ? -1 : 1;
				break;

			case "minion1pane":
				activepartymember = (activepartymember == 2) ? -1 : 2;
				break;

			}
			updatePartyMenberSize();
		}

		private void updatePartyMenberSize() {
			ScaleTransition[] stlist = new ScaleTransition[4];
			for(int i=0; i<3; ++i) {

				float fscale = (((AnchorPane)partymenberlist.getChildren().get(i)).getScaleX() < 1.2) ? 1.0f : 1.2f;
				float tscale = (activepartymember == i) ? 1.2f : 1.0f;

				ScaleTransition scaletransition = new ScaleTransition();
				scaletransition.setNode(((AnchorPane)partymenberlist.getChildren().get(i)));
				scaletransition.setFromX(fscale); scaletransition.setFromY(fscale);
				scaletransition.setToX(tscale);   scaletransition.setToY(tscale);
				scaletransition.setDuration(Duration.seconds(0.2));
				scaletransition.setInterpolator(Interpolator.EASE_BOTH);

				stlist[i] = scaletransition;
			}
			ParallelTransition pt = new ParallelTransition(stlist[0], stlist[1], stlist[2]);
			pt.play();
		}

		@FXML
		private void onClickCard(MouseEvent e) {

			switch( ((AnchorPane)e.getSource()).getId()) {

			case "card0":
				activecard = (activecard == 0) ? -1 : 0;
				break;

			case "card1":
				activecard = (activecard == 1) ? -1 : 1;
				break;

			case "card2":
				activecard = (activecard == 2) ? -1 : 2;
				break;

			case "card3":
				if(hasadvancecard) {
					activecard = (activecard == 3) ? -1 : 3;
				}
				break;
			}
			updateCardSize();
		}

		private void updateCardSize() {
			ScaleTransition[] stlist = new ScaleTransition[4];
			for(int i=0; i<4; ++i) {

				float fscale = (((AnchorPane)cardfieldpane.getChildren().get(i)).getScaleX() < 1.2) ? 1.0f : 1.2f;
				float tscale = (activecard == i) ? 1.2f : 1.0f;

				ScaleTransition scaletransition = new ScaleTransition();
				scaletransition.setNode(((AnchorPane)cardfieldpane.getChildren().get(i)));
				scaletransition.setFromX(fscale); scaletransition.setFromY(fscale);
				scaletransition.setToX(tscale);   scaletransition.setToY(tscale);
				scaletransition.setDuration(Duration.seconds(0.2));
				scaletransition.setInterpolator(Interpolator.EASE_BOTH);

				stlist[i] = scaletransition;
			}
			ParallelTransition pt = new ParallelTransition(stlist[0], stlist[1], stlist[2], stlist[3]);
			pt.play();
		}

		@FXML
		public void onScroll(ScrollEvent e) {
			multiscroll = e.getMultiplierY();
			rotationamount += e.getDeltaY() / 6.0;

			playerboxaxis.setRotate(rotationamount);
		}

		public static void switchInfoScreen(AnchorPane infoscreen_) {
			info.getChildren().clear();
			info.getChildren().add(infoscreen_);

			AnchorPane.setTopAnchor(infoscreen_, 0d);
			AnchorPane.setBottomAnchor(infoscreen_, 0d);
			AnchorPane.setLeftAnchor(infoscreen_, 0d);
			AnchorPane.setRightAnchor(infoscreen_, 0d);
		}


		public void onAttackTurn() {
			this.attackaction = AttackAction.None;
			infoAttackTurn(true);



		}

		private void infoAttackTurn(boolean isstartturn) {

			Timeline timeline = new Timeline();
			Interpolator interpolator = Interpolator.SPLINE(0.7, 0.1, 0.3, 0.9);

			int th0 = (isstartturn) ? 0 : 100;
			int th1 = (isstartturn) ? 100 : 0;

			int o0 = (isstartturn) ? 0 : 1;
			int o1 = (isstartturn) ? 1 : 0;



			KeyValue kvp0 = new KeyValue(turninfoscreen.prefHeightProperty(), th0);
			KeyFrame kfp0 = new KeyFrame(Duration.ZERO, kvp0);
			KeyValue kvp1 = new KeyValue(turninfoscreen.prefHeightProperty(), th1);
			KeyFrame kfp1 = new KeyFrame(Duration.seconds(1.0), kvp1);

			KeyValue kvto0 = new KeyValue(attackturntext.opacityProperty(), o0);
			KeyFrame kfto0 = new KeyFrame(Duration.ZERO, kvto0);
			KeyValue kvto1 = new KeyValue(attackturntext.opacityProperty(), o1);
			KeyFrame kfto1 = new KeyFrame(Duration.seconds(1.0), kvto1);

			KeyValue kva0 = new KeyValue(attackpane.opacityProperty(), o0);
			KeyFrame kfa0 = new KeyFrame(Duration.ZERO, kva0);
			KeyValue kva1 = new KeyValue(attackpane.opacityProperty(), o1);
			KeyFrame kfa1 = new KeyFrame(Duration.seconds(1.0), kva1);

			KeyValue kvd0 = new KeyValue(drawpane.opacityProperty(), o0);
			KeyFrame kfd0 = new KeyFrame(Duration.ZERO, kvd0);
			KeyValue kvd1 = new KeyValue(drawpane.opacityProperty(), o1);
			KeyFrame kfd1 = new KeyFrame(Duration.seconds(1.0), kvd1);

			KeyValue kvr0 = new KeyValue(restpane.opacityProperty(), o0);
			KeyFrame kfr0 = new KeyFrame(Duration.ZERO, kvr0);
			KeyValue kvr1 = new KeyValue(restpane.opacityProperty(), o1);
			KeyFrame kfr1 = new KeyFrame(Duration.seconds(1.0), kvr1);


			KeyFrame kfbd = new KeyFrame(Duration.seconds(1.0), new  javafx.event.EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					buttonlist.setDisable(!isstartturn);
				}
	        }, null);

			timeline.getKeyFrames().add(kfp0);
			timeline.getKeyFrames().add(kfp1);
			timeline.getKeyFrames().add(kfto0);
			timeline.getKeyFrames().add(kfto1);
			timeline.getKeyFrames().add(kfa0);
			timeline.getKeyFrames().add(kfa1);
			timeline.getKeyFrames().add(kfd0);
			timeline.getKeyFrames().add(kfd1);
			timeline.getKeyFrames().add(kfr0);
			timeline.getKeyFrames().add(kfr1);
			timeline.getKeyFrames().add(kfbd);


			timeline.play();
			//攻撃ターンであることを通知
			//ボタンをアクティブ化
		}





		@FXML
		private void onClickAttack() {
			//カード,攻撃者, 攻撃対象をしていしているかチェック
			if(activecard != -1 && activepartymember != -1 && activeplayer != -1) {
				if(activepartymember != 0) {
					this.attackaction = AttackAction.Attack;
					buttonlist.setDisable(true);
					infoAttackTurn(false);
				}else {
					System.out.println("Eroor: プレイヤーは攻撃できません");
				}
			}
		}

		@FXML
		private void onClickDraw() {
			if(activecard != -1) {
				this.attackaction = AttackAction.Draw;
				buttonlist.setDisable(true);
				infoAttackTurn(false);
			}
		}

		@FXML
		private void onClickRest() {
			this.attackaction = AttackAction.Rest;
			buttonlist.setDisable(true);
			infoAttackTurn(false);
		}

		public AttackAction getAttackActoin() {
			return attackaction;

		}



		public int getActionCard() {

			return this.activecard;
		}

		public int getattakingminion() {

			return this.attackingminion;
		}

		public Guest getAttackedPlayer() {

			return this.attackedplayer;
		}

		private void setanimationfinished(boolean flag) {
			this.animationfinished = flag;
		}

		public boolean isanimationfinished() {
			return this.animationfinished;
		}


		//リスナ登録
		public void addUpdateGuestListener(OnUpdateGuestHandler handler) {
			this.listener = handler;
		}

		public void removeAllReadyListener() {
			this.listener = null;
		}

}
