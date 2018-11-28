package sharkindream.network.stream.playerstream;

import java.io.Serializable;

import sharkindream.deck.Deck;
import sharkindream.gamecharacter.Minion;
import sharkindream.gamecharacter.Type;
import sharkindream.party.Party;

public class Guest implements Serializable{



	/*ID*/
	public int playerID;

	/*プレイヤー名*/
	private String name = "Noname";

	/*プレイヤーの属性*/
	public Type type;

	/*プレイヤーのデッキ*/
	public Deck deck = new Deck();

	/*プレイヤーのパーティ*/
	public Party party = new Party();

	/*準備完了*/
	public boolean isready;

	public boolean isequal(Object obj) {
		if(obj == null) {
			return false;
		}

		if(!(obj instanceof Guest)) {
			return false;
		}

		//要素を確認---------------------------------------
		if(this.playerID != ((Guest)obj).playerID){
			return false;
		}
		if(this.name != ((Guest)obj).name){
			return false;
		}

		if(this.isready != ((Guest)obj).isready) {
			return false;
		}
		if(this.type != ((Guest)obj).type){
			return false;
		}
		if(this.deck.getDeckID() != ((Guest)obj).deck.getDeckID()){
			return false;
		}

		return this.party.isequal(((Guest)obj).party);

	}

	public String getName() {
		return name;
	}

	public void setName(String name_) {
		this.name = name_;
	}

	public void setMinion(Minion minion, int index) {
		party.setMinion(minion, index);
	}

	public void readDeck(int deckid) {

	}
}
