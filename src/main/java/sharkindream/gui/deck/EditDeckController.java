package sharkindream.gui.deck;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;
import sharkindream.actioncard.ActionCard;
import sharkindream.actioncard.ActionCard.Accuracy;
import sharkindream.actioncard.ActionCard.Debuff;
import sharkindream.actioncard.ActionCard.Luck;
import sharkindream.actioncard.ActionCard.Power;
import sharkindream.actioncard.ActionCard.Resource;
import sharkindream.actioncard.ActionCard.Target;
import sharkindream.config.Setting;
import sharkindream.deck.Deck;
import sharkindream.gamecharacter.Type;
import sharkindream.gui.deck.accuracyandluck.SubmenuAccuracyAndLuckController;
import sharkindream.gui.deck.buff.SubmenuBuffController;
import sharkindream.gui.deck.power.SubmenuPowerController;
import sharkindream.gui.deck.resource.SubmenuResourceController;
import sharkindream.gui.deck.target.SubmenuTargetController;
import sharkindream.gui.deck.type.SubmenuTypeController;
import sharkindream.gui.event.OnMoveScreenHandler;
import sharkindream.network.server.CalcCardStatus;

public class EditDeckController {

	private Deck deck;
	private int deckid = 0;
	private int activeCard = -1;

	@FXML
	private AnchorPane submanu;

	@FXML
	private Text decknum;
	@FXML
	private AnchorPane cardlistpane;
	@FXML
	private AnchorPane card0;
	@FXML
	private AnchorPane card1;
	@FXML
	private AnchorPane card2;
	@FXML
	private AnchorPane card3;

	@FXML
	private Text typetext;
	@FXML
	private Text resourcetext;
	@FXML
	private Text powertext;
	@FXML
	private Text bufftext;
	@FXML
	private Text targettext;
	@FXML
	private Text accuracylucktext;
	@FXML
	private Text costtext;

	private enum SettingList{
		Type,
		Resource,
		Power,
		Buff,
		Target,
		AccuracyLuck;
	}



	private boolean isclosesubmanu = true;
	private final int SUBMENUWIDTH = 150;
	private final int SUBHEIGHT = 200;

	private CalcCardStatus calccost = new CalcCardStatus();
	Deck[] decklist = new Deck[Setting.MaxDecknum];
	private OnMoveScreenHandler listener;

	@FXML
	void initialize() {
		showCardInfomation();
	}

	private void showCardInfomation() {
		deck = (new Deck()).readDeckfromjson(deckid);
		decknum.setText(String.valueOf(deckid));

		updateUIInfo(deck);
	}

	@FXML
	private void onClickNextDeck() {
		deck.saveDecktojason();
		deckid +=1;
		if(deckid >= Setting.MaxDecknum) deckid = 0;
		showCardInfomation();
	}

	@FXML
	private void onClickForwardDeck() {
		deck.saveDecktojason();
		deckid -= 1;
		if(deckid < 0) deckid = Setting.MaxDecknum - 1;
		showCardInfomation();
	}

	@FXML
	private void onClickReturnMainmenu() {
		onClickedReturnMenu();
	}

	 private void updateUIInfo(Deck deck) {
		 Map<ActionCard, AnchorPane> parecardset = new HashMap<>();
			AnchorPane[] cardlist = {card0, card1, card2, card3};
			for(int i=0; i<4; ++i) {
				parecardset.put(deck.cardlist[i], cardlist[i]);
			}

			for(Map.Entry<ActionCard, AnchorPane> cardentry : parecardset.entrySet()) {

				setCardShowName(cardentry.getValue(), cardentry.getKey().power.getshowname());
				setCardColor(cardentry.getValue(), cardentry.getKey().type.getColor());
				setCardIcon(cardentry.getValue(), cardentry.getKey().resource, cardentry.getKey().type.getColor());
				setCenterIcon(cardentry.getValue(), String.valueOf(cardentry.getKey().power.getpower()));
				setCardText(cardentry.getValue(), cardentry.getKey().debufflist, cardentry.getKey().accuracy.getaccuracy(), cardentry.getKey().luck.getluck());

			}
	 }

	 private void setCardShowName(AnchorPane pane, String showname) {
		 ((Label)((AnchorPane)pane.getChildren().get(0)).getChildren().get(0)).setText(showname);
		 ((Label)((AnchorPane)pane.getChildren().get(1)).getChildren().get(0)).setText(showname);
	 }

	 private void setCardColor(AnchorPane pane, String color) {

		 //文字
		 ((Label)((AnchorPane)pane.getChildren().get(0)).getChildren().get(0)).setTextFill(Paint.valueOf(color));
		 ((Label)((AnchorPane)pane.getChildren().get(1)).getChildren().get(0)).setTextFill(Paint.valueOf(color));
		 ((Label)((TilePane)pane.getChildren().get(2)).getChildren().get(0)).setTextFill(Paint.valueOf(color));
		 ((Text)((AnchorPane)pane.getChildren().get(3)).getChildren().get(0)).setFill(Paint.valueOf(color));
		 ((Text)((AnchorPane)pane.getChildren().get(3)).getChildren().get(1)).setFill(Paint.valueOf(color));
	 }

	 private void setCardIcon(AnchorPane pane, Resource resource, String color) {

		 ((AnchorPane)((AnchorPane)pane.getChildren().get(0)).getChildren().get(1)).getChildren().clear();
		 ((AnchorPane)((AnchorPane)pane.getChildren().get(0)).getChildren().get(1)).getChildren().add(resource.getIcon(Paint.valueOf(color)));
		 ((Canvas)((AnchorPane)((AnchorPane)pane.getChildren().get(0)).getChildren().get(1)).getChildren().get(0)).setDisable(true);
		 ((AnchorPane)((AnchorPane)pane.getChildren().get(1)).getChildren().get(1)).getChildren().clear();
		 ((AnchorPane)((AnchorPane)pane.getChildren().get(1)).getChildren().get(1)).getChildren().add(resource.getIcon(Paint.valueOf(color)));
		 ((Canvas)((AnchorPane)((AnchorPane)pane.getChildren().get(1)).getChildren().get(1)).getChildren().get(0)).setDisable(true);
	 }

	 private void setCenterIcon(AnchorPane pane, String showcentertext) {
		 ((Label)((TilePane)pane.getChildren().get(2)).getChildren().get(0)).setText(showcentertext);
	 }

	 private void setCardText(AnchorPane pane, Debuff[] debufflist, int acnum, int lcnum) {

		 ((Text)((AnchorPane)pane.getChildren().get(3)).getChildren().get(0)).setText(debufflist[0].getshowname() + "/" + debufflist[1].getshowname() + "/" +  debufflist[2].getshowname() + "/" +  debufflist[3].getshowname());
		 ((Text)((AnchorPane)pane.getChildren().get(3)).getChildren().get(1)).setText(String.valueOf(acnum) + "/" + String.valueOf(lcnum));
	 }

	@FXML
	public void onMouseClockCard(MouseEvent e) {

		switch(((AnchorPane)e.getSource()).getId()){
		case "card0":
			activeCard = (activeCard == 0) ? -1: 0;
			break;

		case "card1":
			activeCard = (activeCard == 1) ? -1: 1;
			break;

		case "card2":
			activeCard = (activeCard == 2) ? -1: 2;
			break;

		case "card3":
			activeCard = (activeCard == 3) ? -1: 3;
			break;

		}
		updateSettingContents();
		updateCardSize();
	}

	private void updateCardSize() {
		ScaleTransition[] stlist = new ScaleTransition[4];
		for(int i=0; i<4; ++i) {

			float fscale = (((AnchorPane)cardlistpane.getChildren().get(i)).getScaleX() < 0.6) ? 0.5f : 0.6f;
			float tscale = (activeCard == i) ? 0.6f : 0.5f;

			ScaleTransition scaletransition = new ScaleTransition();
			scaletransition.setNode(((AnchorPane)cardlistpane.getChildren().get(i)));
			scaletransition.setFromX(fscale); scaletransition.setFromY(fscale);
			scaletransition.setToX(tscale);   scaletransition.setToY(tscale);
			scaletransition.setDuration(Duration.seconds(0.2));
			scaletransition.setInterpolator(Interpolator.EASE_BOTH);

			stlist[i] = scaletransition;
		}
		ParallelTransition pt = new ParallelTransition(stlist[0], stlist[1], stlist[2], stlist[3]);
		pt.play();
	}

	private void updateSettingContents() {
		if(activeCard != -1) {
			typetext.setText(String.valueOf(deck.cardlist[activeCard].type.name()));
			resourcetext.setText(String.valueOf(deck.cardlist[activeCard].resource.name()));
			powertext.setText(String.valueOf(deck.cardlist[activeCard].power.getshowname() + "/" + deck.cardlist[activeCard].power.getpower()));
			bufftext.setText(String.valueOf(deck.cardlist[activeCard].debufflist[0].getshowname() + "/" + deck.cardlist[activeCard].debufflist[1].getshowname() + "/" + deck.cardlist[activeCard].debufflist[2].getshowname()) + "/" + deck.cardlist[activeCard].debufflist[3].getshowname());
			targettext.setText(String.valueOf(deck.cardlist[activeCard].target.name()));
			accuracylucktext.setText(String.valueOf(deck.cardlist[activeCard].accuracy.getaccuracy() + "/" + deck.cardlist[activeCard].luck.getluck()));
			costtext.setText(String.valueOf(calccost.calcCost(deck.cardlist[activeCard])));
		}
	}


	@FXML
	public void onMouseClickSetting(MouseEvent e) {
		switch( ((Text)((AnchorPane)((Text)e.getSource()).getParent()).getChildren().get(0)).getText() ) {
			case "Type:":
				System.out.println(((Text)((AnchorPane)((Text)e.getSource()).getParent()).getChildren().get(0)).getText());
				opensubmanu(SettingList.Type);
				break;
			case "Resource:":
				System.out.println(((Text)((AnchorPane)((Text)e.getSource()).getParent()).getChildren().get(0)).getText());
				opensubmanu(SettingList.Resource);
				break;
			case "Power:":
				System.out.println(((Text)((AnchorPane)((Text)e.getSource()).getParent()).getChildren().get(0)).getText());
				opensubmanu(SettingList.Power);
				break;
			case "Buff:":
				System.out.println(((Text)((AnchorPane)((Text)e.getSource()).getParent()).getChildren().get(0)).getText());
				opensubmanu(SettingList.Buff);
				break;
			case "Target:":
				System.out.println(((Text)((AnchorPane)((Text)e.getSource()).getParent()).getChildren().get(0)).getText());
				opensubmanu(SettingList.Target);
				break;
			case "Accuracy/Luck:":
				System.out.println(((Text)((AnchorPane)((Text)e.getSource()).getParent()).getChildren().get(0)).getText());
				opensubmanu(SettingList.AccuracyLuck);
				break;


		}
	}

	 void opensubmanu(SettingList list) {


		((AnchorPane)submanu.getChildren().get(2)).getChildren().clear();
		Timeline timeline = new Timeline();



		if(isclosesubmanu) {
			//サブメニューを開く

			Line uline = (Line) submanu.getChildren().get(0);
			Line dline = (Line) submanu.getChildren().get(1);

			KeyValue kvu0 = new KeyValue(uline.endXProperty(), 0, Interpolator.LINEAR);
			KeyFrame kfu0 = new KeyFrame(Duration.ZERO, kvu0);

			KeyValue kvu1 = new KeyValue(uline.endXProperty(), this.SUBMENUWIDTH);
			KeyFrame kfu1 = new KeyFrame(Duration.seconds(0.3), kvu1);

			KeyValue kvd0 = new KeyValue(dline.endXProperty(), 0, Interpolator.LINEAR);
			KeyFrame kfd0 = new KeyFrame(Duration.ZERO, kvd0);

			KeyValue kvd1 = new KeyValue(dline.endXProperty(), this.SUBMENUWIDTH);
			KeyFrame kfd1 = new KeyFrame(Duration.seconds(0.3), kvd1);

			KeyValue kvp0 = new KeyValue(submanu.prefHeightProperty(), 0, Interpolator.LINEAR);
			KeyFrame kfp0 = new KeyFrame(Duration.seconds(0.1), kvp0);

			KeyValue kvp1 = new KeyValue(submanu.prefHeightProperty(), this.SUBHEIGHT);
			KeyFrame kfp1 = new KeyFrame(Duration.seconds(0.4), kvp1);


			timeline.getKeyFrames().add(kfu0);
			timeline.getKeyFrames().add(kfu1);

			timeline.getKeyFrames().add(kfd0);
			timeline.getKeyFrames().add(kfd1);

			timeline.getKeyFrames().add(kfp0);
			timeline.getKeyFrames().add(kfp1);

			timeline.play();


			switch(list) {
			case Type:
				try {
					FXMLLoader typefxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/deck/type/SubmenuType.fxml"));

					AnchorPane pane = typefxml.load();
					((SubmenuTypeController)typefxml.getController()).setController(this);
					((AnchorPane)submanu.getChildren().get(2)).getChildren().add(pane);
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				break;

			  case Resource:
				try {
					FXMLLoader resourcefxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/deck/resource/SubmenuResource.fxml"));

					AnchorPane pane = resourcefxml.load();
					((SubmenuResourceController)resourcefxml.getController()).getController(this);
					((AnchorPane)submanu.getChildren().get(2)).getChildren().add(pane);
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				break;

			  case Power:
				  try {
						FXMLLoader powerfxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/deck/power/SubmenuPower.fxml"));

						AnchorPane pane = powerfxml.load();
						((SubmenuPowerController)powerfxml.getController()).getController(this);
						((AnchorPane)submanu.getChildren().get(2)).getChildren().add(pane);
					} catch (IOException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
					break;

			  case Buff:
				  try {
						FXMLLoader bufffxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/deck/buff/SubmenuBuff.fxml"));

						AnchorPane pane = bufffxml.load();
						((SubmenuBuffController)bufffxml.getController()).getController(this);
						if(activeCard != -1) ((SubmenuBuffController)bufffxml.getController()).setSliderIni(deck.cardlist[activeCard]);
						((AnchorPane)submanu.getChildren().get(2)).getChildren().add(pane);
					} catch (IOException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
					break;

			  case Target:
				  try {
						FXMLLoader targetfxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/deck/target/SubmenuTarget.fxml"));

						AnchorPane pane = targetfxml.load();
						((SubmenuTargetController)targetfxml.getController()).getController(this);
						((AnchorPane)submanu.getChildren().get(2)).getChildren().add(pane);
					} catch (IOException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
					break;

			  case AccuracyLuck:
				  try {
						FXMLLoader aclcfxml = new FXMLLoader(getClass().getResource("/sharkindream/gui/deck/accuracyandluck/SubmenuAccuracyAndLuck.fxml"));

						AnchorPane pane = aclcfxml.load();
						((SubmenuAccuracyAndLuckController)aclcfxml.getController()).getController(this);
						((AnchorPane)submanu.getChildren().get(2)).getChildren().add(pane);
					} catch (IOException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
					break;
			}


			this.isclosesubmanu = false;

		}else {
			closesubmanu();
		}
	}

	 public void closesubmanu() {
		 //TODO: コピペなのでどちらか1つにまとめる

		Timeline timeline = new Timeline();

		Line uline = (Line) submanu.getChildren().get(0);
		Line dline = (Line) submanu.getChildren().get(1);



		KeyValue kvu0 = new KeyValue(uline.endXProperty(), 0, Interpolator.LINEAR);
		KeyFrame kfu0 = new KeyFrame(Duration.seconds(0.3), kvu0);

		KeyValue kvu1 = new KeyValue(uline.endXProperty(), this.SUBMENUWIDTH);
		KeyFrame kfu1 = new KeyFrame(Duration.ZERO, kvu1);

		KeyValue kvd0 = new KeyValue(dline.endXProperty(), 0, Interpolator.LINEAR);
		KeyFrame kfd0 = new KeyFrame(Duration.seconds(0.3), kvd0);

		KeyValue kvd1 = new KeyValue(dline.endXProperty(), this.SUBMENUWIDTH);
		KeyFrame kfd1 = new KeyFrame(Duration.ZERO, kvd1);

		KeyValue kvp0 = new KeyValue(submanu.prefHeightProperty(), 0, Interpolator.LINEAR);
		KeyFrame kfp0 = new KeyFrame(Duration.seconds(0.4), kvp0);

		KeyValue kvp1 = new KeyValue(submanu.prefHeightProperty(), this.SUBHEIGHT);
		KeyFrame kfp1 = new KeyFrame(Duration.seconds(0.1), kvp1);


		timeline.getKeyFrames().add(kfu0);
		timeline.getKeyFrames().add(kfu1);

		timeline.getKeyFrames().add(kfd0);
		timeline.getKeyFrames().add(kfd1);

		timeline.getKeyFrames().add(kfp0);
		timeline.getKeyFrames().add(kfp1);

		timeline.play();

		this.isclosesubmanu = true;


		((AnchorPane)submanu.getChildren().get(2)).getChildren().clear();


	 }

	 private List<ActionCard> getactivecardlist() {
		 AnchorPane[] cardlist = {card0, card1, card2, card3};
		 List<Integer> activelist = new ArrayList<>();
		 int index = 0;
		 for(AnchorPane pane : cardlist) {
			 if(pane.getScaleX() > 0.55) {
				 activelist.add(index);
			 }
			 ++index;
		 }

		 List<ActionCard> activecardlist = new ArrayList<>();
		 for(int i=0; i<deck.maxcardnum; ++i) {
			 if(activelist.contains(i)) {
				 activecardlist.add(deck.cardlist[i]);
			 }
		 }

		 return activecardlist;
	 }



	 public void updatetype(Type type) {
		 List<ActionCard> activecardlist = getactivecardlist();
		 if(!activecardlist.isEmpty()) {
			 for(ActionCard card : activecardlist) {
				 card.type = type;
			 }
		 }
		 //表示の更新
		 updateUIInfo(deck);
		 updateSettingContents();
	 }

	 public void updateResource(Resource resource) {
		 List<ActionCard> activecardlist = getactivecardlist();
		 if(!activecardlist.isEmpty()) {
			 for(ActionCard card : activecardlist) {
				 card.resource = resource;
			 }
		 }
		 //表示の更新
		 updateUIInfo(deck);
		 updateSettingContents();
	 }

	 public void updatePower(Power power) {
		 List<ActionCard> activecardlist = getactivecardlist();
		 if(!activecardlist.isEmpty()) {
			 for(ActionCard card : activecardlist) {
				 card.power = power;
			 }
		 }
		 //表示の更新
		 updateUIInfo(deck);
		 updateSettingContents();
	 }

	 public void updateBuff(Debuff[] buff) {
		 List<ActionCard> activecardlist = getactivecardlist();
		 if(!activecardlist.isEmpty()) {
			 for(ActionCard card : activecardlist) {
				 card.debufflist = buff;
			 }
		 }
		 updateUIInfo(deck);
		 updateSettingContents();
	 }

	 public void updateTarget(Target target) {
		 List<ActionCard> activecardlist = getactivecardlist();
		 if(!activecardlist.isEmpty()) {
			 for(ActionCard card : activecardlist) {
				 card.target = target;
			 }
		 }
		 updateUIInfo(deck);
		 updateSettingContents();
	 }

	 public void updateAccuracyAndLuck(Accuracy accuracy, Luck luck) {
		 List<ActionCard> activecardlist = getactivecardlist();
		 if(!activecardlist.isEmpty()) {
			 for(ActionCard card : activecardlist) {
				 card.accuracy = accuracy;
				 card.luck = luck;
			 }
		 }
		 updateUIInfo(deck);
		 updateSettingContents();
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
