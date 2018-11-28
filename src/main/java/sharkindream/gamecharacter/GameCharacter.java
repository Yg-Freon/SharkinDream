package sharkindream.gamecharacter;

import java.io.Serializable;

import sharkindream.gamecharacter.classtype.Prototype;

public abstract class GameCharacter implements Serializable {


	public enum CharacterClass {
		None,
		Player,
		Soldier,
		Shielder,
		Witch,
		Sage,
		Bishop,
		MadScientist,
		BeastTamer;

	public Prototype getMinionclass() {
		switch(this) {
		case Player:
			return new sharkindream.gamecharacter.classtype.Player();
		case Soldier:
			return new sharkindream.gamecharacter.classtype.Soldier();
		case Shielder:
			return new sharkindream.gamecharacter.classtype.Shielder();
		case Witch:
			return new sharkindream.gamecharacter.classtype.Witch();
		case Sage:
			return new sharkindream.gamecharacter.classtype.Sage();
		case Bishop:
			return new sharkindream.gamecharacter.classtype.Bishop();
		case MadScientist:
			return new sharkindream.gamecharacter.classtype.MadScientist();
		case BeastTamer:
			return new sharkindream.gamecharacter.classtype.BeastTamer();
		default :
			return new sharkindream.gamecharacter.classtype.None();

		}
	}
}

	public enum Status{
		Strength,
		Vitality,
		Intelligence,
		Mind,
		HP,
		MP;
	}

	protected int strength  = 5;
	protected int vitality = 5;
	protected int intelligence = 5;
	protected int mind = 5;
	protected int HP = 5;
	protected int MP = 5;

	protected Type type;
	protected StatusAilmen statusalimen = StatusAilmen.None;

	public GameCharacter() {

	}

	public GameCharacter(int str, int vit, int inte, int mind, int hp, int mp) {
		this.strength = str;
		this.vitality = vit;
		this.intelligence = inte;
		this.mind = mind;
		this.HP = hp;
		this.MP = mp;
		}


	public GameCharacter(Type type, int str, int vit, int inte, int mind, int hp, int mp) {
		this.strength = str;
		this.vitality = vit;
		this.intelligence = inte;
		this.mind = mind;
		this.HP = hp;
		this.MP = mp;
		this.type = type;
	}



	//攻撃処理
	/*
	public int attack(ActionCard card, int targetid) {


		return ;

	}
	*/

	public int[] getstatuslist() {
		int[] status = new int[6];

		status[0] = this.strength;
		status[1] = this.vitality;
		status[2] = this.intelligence;
		status[3] = this.mind;
		status[4] = this.HP;
		status[5] = this.MP;

		return status;
	}

	public void setstatuslist(int[] statuslist) {
		this.strength = statuslist[0];
		this.vitality = statuslist[1];
		this.intelligence = statuslist[2];
		this.mind = statuslist[3];
		this.HP = statuslist[4];
		this.MP = statuslist[5];
	}

	protected Type getType() {
		return this.type;
	}

	protected void setType(Type type_) {
		this.type = type_;
	}

	protected void  setGameCharacterStatus(Type type, int str, int vit, int inte, int mind, int hp, int mp) {
		this.strength = str;
		this.vitality = vit;
		this.intelligence = inte;
		this.mind = mind;
		this.HP = hp;
		this.MP = mp;
		this.type = type;
	}


}
