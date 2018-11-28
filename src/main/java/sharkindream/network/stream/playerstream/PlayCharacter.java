package sharkindream.network.stream.playerstream;

import java.io.Serializable;

import sharkindream.gamecharacter.GameCharacter.CharacterClass;
import sharkindream.gamecharacter.StatusAilmen;
import sharkindream.gamecharacter.Type;

public class PlayCharacter implements Serializable{

	/*操作主*/
	private int id;
	/*操作id 0:プレイヤ 1,2:ミニオン*/
	private int index;

	/*属性*/
	private Type type;

	/*キャラクタークラス*/
	private CharacterClass characterclass;

	/*ステータス実数値*/
	private int[] status = new int[6];

	/*能力補正*/
	private int[] statuscorrection = {0, 0, 0, 0};

	/*状態異常*/
	private StatusAilmen stailmen = StatusAilmen.None;

	/*死亡*/
	private boolean isdead = false;

	private int HP;



	public int getid() {
		return this.id;
	}

	public void setid(int id_) {
		this.id = id_;
	}


	public int[] getStatus() {
		return this.status;
	}

	public void setStatus(int[] status_) {
		this.status = status_;
	}

	public void  initHP() {
		this.HP = this.status[5];
	}

	public int getHP() {
		return this.HP;
	}

	public int getMaxHP() {
		return this.status[5];
	}

	public void addDamege(int damage){
		this.HP -= damage;
		if(this.HP > this.status[5]) {
			this.HP = this.status[5];
		}

		if(this.status[5] < 1) {
			this.isdead = true;
		}
	}

	public void setType(Type type_) {
		this.type = type_;
	}

	public Type getType() {
		return this.type;
	}

	public void setCharacterClass(CharacterClass characlass) {
		this.characterclass = characlass;
	}

	public CharacterClass getCharacterClass() {
		return this.characterclass;
	}


}
