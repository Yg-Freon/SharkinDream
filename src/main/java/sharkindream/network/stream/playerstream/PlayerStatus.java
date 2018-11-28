package sharkindream.network.stream.playerstream;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import sharkindream.actioncard.ActionCard;
import sharkindream.config.Setting;
import sharkindream.network.event.OnUpdatePlayerHandler;

public class PlayerStatus implements Serializable{

	private boolean isloose = false;
	private List<ActionCard> hand = new ArrayList<>();

	private PlayCharacter player = new PlayCharacter();
	private PlayCharacter[] miniones = {new PlayCharacter(), new PlayCharacter()};
	private OnUpdatePlayerHandler handler;

	public PlayerStatus() {

	}

	public PlayerStatus(PlayCharacter player_, PlayCharacter[] miniones_) {
		this.player = player_;
		this.miniones[0] = miniones_[0];
		this.miniones[1] = miniones_[1];
	}


	public void drawHand(List<ActionCard> deck) {
		if(hand.size() < Setting.MaxHandnum) {
			ActionCard drawcard = deck.get((int)(Math.random() * deck.size()));
			hand.add(drawcard);
		}
	}

	public ActionCard getHand(int index) {
		return hand.get(index);
	}

	public PlayCharacter getPlayer() {
		return this.player;
	}

	public boolean canaction() {
		return !this.isloose;
	}

	public PlayCharacter getminion(int id) {
		switch(id) {
		case 0:
			return this.miniones[0];
		case 1:
			return this.miniones[1];
		default:
			return this.miniones[0];
		}
	}


}
