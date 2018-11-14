package sharkindream.gui.editscreen;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import sharkindream.config.Setting;
import sharkindream.deck.Deck;
import sharkindream.gui.editscreen.deck.EditDeckController;

public class EditScreenController {

	@FXML
	public AnchorPane mainscreen;

	Deck[] decklist = new Deck[Setting.MaxDecknum];



	//初期化、デッキデータの読み込み
	public void initDeck() {
		for(int id=0; id<Setting.MaxDecknum; ++id) {
			decklist[id] = new Deck().readDeckfromjson(id);
		}
	}

	public void openEditDeckScreen() {
		mainscreen.getChildren().clear();
		FXMLLoader editdeckfxml = new FXMLLoader(getClass().getResource("/danzaigame/gui/editscreen/deck/EditDeckController.fxml"));
		try {
			AnchorPane pane =editdeckfxml.load();
			((EditDeckController)editdeckfxml.getController()).initpage();
			mainscreen.getChildren().add(pane);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}


}
