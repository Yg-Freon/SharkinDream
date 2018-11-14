package sharkindream.gamecharacter;

public abstract class GameCharacter {


	public int strength  = 5;
	public int vitality = 5;
	public int intelligence = 5;
	public int mind = 5;
	public int HP = 5;
	public int MP = 5;

	public Type type = Type.None;
	private StatusAilmen statusalimen = StatusAilmen.None;
	private boolean isAlive = true;

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

	public void hitattack() {
		//ダメージ処理
	}

	public int addDamage(int damage) {
		this.HP -= damage;
		if(this.HP < 1) {
			this.HP = 0;
			kill();
		}
		return this.HP;
	}

	public void kill() {
		this.isAlive = false;
	}


}
