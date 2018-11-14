package sharkindream.gui.gamescreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import sharkindream.gui.gamescreen.playerstatus.PlayerStatusController;
import sharkindream.network.event.OnUpdateGuestHandler;
import sharkindream.network.stream.playerstream.CharacterStatusStream;
import sharkindream.network.stream.playerstream.Guest;
import sharkindream.network.stream.playerstream.GuestStream;
import sharkindream.network.stream.playerstream.PlayCharacter;

public class New2GameScreenController{

	//ここ-------------------------

	//TODO: => guestlist.size()
	private int tempnum = 15;



	@FXML
	private StackPane playerbox;

	@FXML
	public AnchorPane playerboxaxis;

	private Map<Integer, Guest> guestlist = new HashMap<Integer, Guest>();
	private Map<Integer, PlayCharacter[]> memberlist = new HashMap<>();

	private double multiscroll;
	private boolean isactive = false;
	private double rotationamount = 0;
	private OnUpdateGuestHandler listener;

	private final float upline = 5.0f;
	private final int maxbox = 15;
	private final int centernum = 2;
	private int addcount = 0;

	public void addPlayerEvent(String pname) {

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

			AnchorPane temppane = getpane(guestset.getValue());

			if(((Guest)guestset.getValue()).isready)  ((SVGPath)temppane.getChildren().get(0)).setFill(Paint.valueOf("#0E7AC4"));

			((AnchorPane)((AnchorPane)(playerboxaxis.getChildren().get(0))).getChildren().get(guestset.getKey())).getChildren().clear();
			((AnchorPane)((AnchorPane)(playerboxaxis.getChildren().get(0))).getChildren().get(guestset.getKey())).getChildren().addAll(temppane.getChildren());

			});
		}
	}

	private AnchorPane getpane(Guest guest) {
		AnchorPane pane = new AnchorPane();
		try {
			FXMLLoader panefxml = new FXMLLoader(getClass().getResource("playerstatus/PlayerStatusCircle.fxml"));
			pane = panefxml.load();
			PlayerStatusController panecontroller = ((PlayerStatusController)panefxml.getController());
			panecontroller.initstatuscircle(guest);


		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return pane;
	}


	/*
	private void updatePlayerBox() {
		Platform.runLater( () -> ((AnchorPane)(playerboxaxis.getChildren().get(0))).getChildren().clear() );


		for(Guest guest : guestlist.values()) {

			Platform.runLater( () -> {
				AnchorPane temppane = getpane(guest);
				temppane.setRotate(-23.8 * guest.playerID);

				((AnchorPane)(playerboxaxis.getChildren().get(0))).getChildren().add(getpane(guest));

			});
		}

		if(guestlist.size() < 7) {
			Platform.runLater( () -> {
				for(int i=0; i < guestlist.size()+7; ++i) {
					Guest guest = new Guest();
					guest.name = "Nothing";
					AnchorPane temppane = getpane(guest);
					temppane.setRotate(-23.7 * (guestlist.size()+i));
					((AnchorPane)(playerboxaxis.getChildren().get(0))).getChildren().add(temppane);
				}
			});
		}
	}
	*/



/*
	private void updatescrollbox(double deltaY) {
		int num = (int)rotationamount % tempnum;
		if(num < 0) num += tempnum;
		num = (int) (num % multiscroll);
		System.out.println(num);
 		if(num > upline ) {

 			if(deltaY > 0) {
 	 			++addcount;
 				AnchorPane temppane = getpane(guestlist.get(((maxbox - centernum) + addcount) % maxbox));
 				temppane.setRotate(-23.8 * addcount);

 				Platform.runLater( () -> {
 					((AnchorPane)(playerboxaxis.getChildren().get(0))).getChildren().add(temppane);
 					((AnchorPane)playerboxaxis.getChildren().get(0)).getChildren().remove(0);
 					});

 			}else {

 			}
		}
	}
*/


	@FXML
	public void onScroll(ScrollEvent e) {
		multiscroll = e.getMultiplierY();
		rotationamount += e.getDeltaY() / 6.0;
		System.out.println(e.getMultiplierX());

		playerboxaxis.setRotate(rotationamount);

		//updatescrollbox(e.getDeltaY());
	}







	//リスナ登録
	public void addAllReadyListener(OnUpdateGuestHandler handler) {
		this.listener = handler;
	}

	public void removeAllReadyListener() {
		this.listener = null;
	}


}
