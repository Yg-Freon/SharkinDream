package sharkindream.gamecharacter.minion;

import java.io.Serializable;

import sharkindream.gamecharacter.GameCharacter;
import sharkindream.gamecharacter.Type;

public class Minion extends GameCharacter implements Serializable{

	public MinionClass minionclass = MinionClass.None;

	public Minion() {
		super();
	}

	public Minion(int str, int vit, int inte, int mind, int hp, int mp) {
		super(str, vit, inte, mind, hp, mp);

	}

	public Minion(MinionClass classtype, Type type, int str, int vit, int inte, int mind, int hp, int mp) {
		super(type, str, vit, inte, mind, hp, mp);
		this.minionclass = classtype;

	}

	public boolean isequal(Minion targetminion) {
		boolean isequal = true;
		isequal = (this.minionclass == targetminion.minionclass) && isequal;
		isequal = (this.type == targetminion.type) && isequal;
		isequal = (this.HP == targetminion.HP) && isequal;
		isequal = (this.MP == targetminion.MP) && isequal;
		isequal = (this.strength == targetminion.strength) && isequal;
		isequal = (this.vitality == targetminion.vitality) && isequal;
		isequal = (this.intelligence == targetminion.intelligence) && isequal;
		isequal = (this.mind == targetminion.mind) && isequal;

		return isequal;
	}


	public MinionClass getMinionClass() {
		return minionclass;
	}

	public Type getMinionType() {
		return this.type;
	}
}
