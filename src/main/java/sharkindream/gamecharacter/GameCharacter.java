package sharkindream.gamecharacter;

public abstract class GameCharacter {


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

	protected Type type = Type.None;
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


	protected void setType(Type type_) {
		this.type = type_;
	}


}
