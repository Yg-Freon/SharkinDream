package sharkindream.gui.gamescreen.infomation.lobby.selectminion;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class delCreateMinionScreenController {

	@FXML
	public HBox choiceclass;
	@FXML
	public Text comment;
	/*
	@FXML
	public ImageView classimage;
	*/

	int[] statusposintarray = {5,5,5,5,5,5};

	@FXML
	public Text hptext;
	@FXML
	public Text mptext;
	@FXML
	public Text strtext;
	@FXML
	public Text vittext;
	@FXML
	public Text inttext;
	@FXML
	public Text mindtext;
	@FXML
	public Text sumtext;

	@FXML
	public AnchorPane plussicon;
	@FXML
	public AnchorPane minussicon;
	@FXML
	public SVGPath center;


	private boolean isplusmode = true;


	public  void initializeChoiceBox(int n){

		/*
		 classTypecBox.getItems().clear();
             // コンボボックスに項目を追加
		 classTypecBox.getItems().add("クラスを選択してください");
		 classTypecBox.getItems().add(MinionClass.Soldier.getName());
		 classTypecBox.getItems().add(MinionClass.Shielder.getName());
		 classTypecBox.getItems().add(MinionClass.Witch.getName());
		 classTypecBox.getItems().add(MinionClass.Sage.getName());
		 classTypecBox.getItems().add(MinionClass.Bishop.getName());

		 classTypecBox.getItems().add(MinionClass.MadScientist.getName());
		 classTypecBox.getItems().add(MinionClass.BeastTamer.getName());


		 typecBox.getItems().add("属性を選択してください");
		 typecBox.getItems().add(Type.Fire.getName());
		 typecBox.getItems().add(Type.Water.getName());
		 typecBox.getItems().add(Type.Leaf.getName());
		 typecBox.getItems().add(Type.Light.getName());
		 typecBox.getItems().add(Type.Dark.getName());

		 num = n;
		 subtitle.setText("ミニオンを作成してください : " + num + "体目");
		 setiniminion(num-1);


		 setEvent();
     */
     }

	/*
	private void setiniminion(int n) {

		Minion minion = LobbyScreenController.mystatus.party.getMinion(n);

		MinionClass mclass = minion.getMinionClass();
		classTypecBox.getSelectionModel().select(mclass.getid());

		Type type = minion.getMinionType();
		typecBox.getSelectionModel().select(type.getid());


		miniontypecolor.setStyle("-fx-border-color:" + type.getColor() + ";" + iconStyle);


		File file = new File("src/main/resources/danzaigame/texture/classicon/" + mclass.getName()  + ".png");
		Image icon;
		try {
			icon = new Image(file.toURI().toURL().toString());
			 iconview.setImage(icon);
		}catch (MalformedURLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		hptext.setText(String.valueOf(minion.HP));
		hpslider.setValue(minion.HP);
		mptext.setText(String.valueOf(minion.MP));
		mpslider.setValue(minion.MP);
		strtext.setText(String.valueOf(minion.strength));
		strslider.setValue(minion.strength);
		vittext.setText(String.valueOf(minion.vitality));
		vitslider.setValue(minion.vitality);
		inttext.setText(String.valueOf(minion.intelligence));
		intslider.setValue(minion.intelligence);
		mindtext.setText(String.valueOf(minion.mind));
		mindslider.setValue(minion.mind);

	}




	@FXML
	public void returnpage() {
		switch(num) {
		case 1:
			returnLobbyScreen();
			break;
		case 2:
			returnCreateMinionPage();
			break;
		default:
			returnLobbyScreen();
			break;
		}
	}

	@FXML
	public void gonextpage() {

		//クラス等選択されてるか
		if(classTypecBox.getSelectionModel().getSelectedIndex() != 0
				&& typecBox.getSelectionModel().getSelectedIndex() != 0) {

			//ミニオン保存
			saveminioninfo();
			switch(num) {
			case 1:
				goCreateMinionPage();
				break;
			case 2:
				goChoiceDeckPage();
				break;
			default:
				goCreateMinionPage();
				break;
			}
		}else {
			System.out.println("Error");
		}
	}

	private void saveminioninfo() {
		Minion myminion = new Minion( MinionClass.getClassbyID(classTypecBox.getSelectionModel().getSelectedIndex()),
										Type.getClassbyID(typecBox.getSelectionModel().getSelectedIndex()),
										Integer.parseInt(strtext.getText()),
										Integer.parseInt(vittext.getText()),
										Integer.parseInt(inttext.getText()),
										Integer.parseInt(mindtext.getText()),
										Integer.parseInt(hptext.getText()),
										Integer.parseInt(mptext.getText()));

		LobbyScreenController.mystatus.setMinion(myminion, num-1);
	}

	private void goCreateMinionPage() {
		FXMLLoader minioncreatefxml = new FXMLLoader(getClass().getResource("/danzaigame/gui/gamescreen/infomation/lobby/createminion/CreateMinionScreen.fxml"));

		AnchorPane minioncreatescreen;
		try {
			minioncreatescreen = (AnchorPane)minioncreatefxml.load();
			Client.switchGameInfoLobbymanu(minioncreatescreen);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		((CreateMinionScreenController)minioncreatefxml.getController()).initializeChoiceBox(2);
	}


	private void goChoiceDeckPage(){
		FXMLLoader selectdeckfxml = new FXMLLoader(getClass().getResource("/danzaigame/gui/gamescreen/infomation/lobby/selectdeck/SelectDeck.fxml"));

		AnchorPane deckselectscreen;
		try {
			deckselectscreen = (AnchorPane)selectdeckfxml.load();
			Client.switchGameInfoLobbymanu(deckselectscreen);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		((SelectDeckController)selectdeckfxml.getController()).initializeDeck(LobbyScreenController.mystatus.deck.deckID);
	}


	private void returnCreateMinionPage() {

		FXMLLoader minioncreatefxml = new FXMLLoader(getClass().getResource("/danzaigame/gui/gamescreen/infomation/lobby/createminion/CreateMinionScreen.fxml"));

		AnchorPane minioncreatescreen;
		try {
			minioncreatescreen = (AnchorPane)minioncreatefxml.load();
			Client.switchGameInfoLobbymanu(minioncreatescreen);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		((CreateMinionScreenController)minioncreatefxml.getController()).initializeChoiceBox(1);
	}



	private void returnLobbyScreen() {
		FXMLLoader lobbyscreenfxml = new FXMLLoader(getClass().getResource("/danzaigame/gui/gamescreen/infomation/lobby/selectplayertype/LobbyScreen.fxml"));

		AnchorPane lobbyscreen;
		try {
			lobbyscreen = (AnchorPane)lobbyscreenfxml.load();
			Client.switchGameInfoLobbymanu(lobbyscreen);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}


	 public void setEvent() {
		 EventHandler<ActionEvent> classTypeChanged = ( event ) -> this.classTypeChanged( event );
		 classTypecBox.addEventHandler( ActionEvent.ACTION , classTypeChanged );

		 EventHandler<ActionEvent> typeChanged = ( event ) -> this.typeChanged( event );
		 typecBox.addEventHandler( ActionEvent.ACTION , typeChanged );
	 }

	 private void classTypeChanged( ActionEvent e ){

		 ChoiceBox c = (ChoiceBox)e.getSource();
		 MinionClass minion = MinionClass.getClassbyID( c.getSelectionModel().getSelectedIndex());

		 if(minion != null) {
			 comment.setText(minion.getName() + ":" + minion.getComment());
		 }

		 else {
			 comment.setText("comment:");
		 }
	}

	 private void typeChanged( ActionEvent e ){
		 ChoiceBox c = (ChoiceBox)e.getSource();
		 Type type = Type.getClassbyID( c.getSelectionModel().getSelectedIndex());

		 if(type != null) {
			 miniontypecolor.setStyle("-fx-border-color:" + type.getColor() + ";" + iconStyle);
		 }

		 else {
			 miniontypecolor.setStyle("-fx-border-color:black;" + iconStyle);
		 }

	 }

	 @FXML
	 public void onSliderDraged(MouseEvent e) {
		 Slider s = (Slider)e.getSource();
		 int stat = (int)s.getValue();

		 TilePane a = new TilePane();
		((Text)((TilePane)((HBox)((VBox)((TilePane)s.getParent()).getParent()).getChildren().get(0)).getChildren().get(1)).getChildren().get(0)).setText(String.valueOf(stat));
	 }

	 */


	 @FXML
	 public void onMouseClicked(MouseEvent e) {
		 String statusstr = ((Text)((AnchorPane)((SVGPath)e.getSource()).getParent()).getChildren().get(1)).getText();

		 if(isplusmode) {
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
		 int sum = 0;
		 for(int stpoint : statusposintarray) {
			 sum += stpoint;
		 }
		 sumtext.setText(String.valueOf(sum));
		 if(sum > 50) {
			 center.setFill(Paint.valueOf("#EE6557"));
		 }else if(sum > 40) {
			 center.setFill(Paint.valueOf("#ffdd59"));
			 sumtext.setFill(Paint.valueOf("#ffffff"));
		 }else {
			 center.setFill(Paint.valueOf("#ffffff"));
			 sumtext.setFill(Paint.valueOf("#2b2b2b"));
		 }
	 }

	 @FXML
	 public void onMouseEnterCenter(MouseEvent e) {
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
	 public void onMouseExitedCenter(MouseEvent e) {

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
	 public void onMouseClickedCenter(MouseEvent e) {

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
}
