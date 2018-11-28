package sharkindream.party;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import sharkindream.gamecharacter.Minion;


public class Party implements Serializable{

	final int maxmember = 2;
	public Minion[] member = new Minion[maxmember];

	public Party() {
		for(int i=0; i<maxmember; ++i) {
			member[i] = new Minion();
		}
	}

	public Minion getMinion(int index) {
		switch(index) {
		case 0:
			return member[0];
		case 1:
			return member[1];
		default:
			return member[0];
		}
	}

	public boolean isequal(Party pty) {
		HashMap<Minion, Minion> guestmp = new HashMap<Minion, Minion>();

		guestmp.put(member[0], pty.getMinion(0));
		guestmp.put(member[1], pty.getMinion(1));


		boolean isequal = true;
		for(Map.Entry<Minion, Minion> guestentry: guestmp.entrySet()) {
			isequal = guestentry.getKey().isequal(guestentry.getValue()) && isequal;
		}

		return isequal;
	}

	public void setMinion(Minion minion, int index) {
		member[index].setMinionStatus(minion);
	}
}
