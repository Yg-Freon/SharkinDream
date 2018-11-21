package sharkindream.network.stream.playerstream;

import sharkindream.gamecharacter.StatusAilmen;
import sharkindream.gamecharacter.Type;
import sharkindream.gamecharacter.minion.MinionClass;

public class PlayCharacter {

	/*操作主*/
	private int id;
	/*操作id 0:プレイヤ 1,2:ミニオン*/
	private int index;

	/*属性*/
	private Type type;

	/*キャラクタークラス*/
	private MinionClass characterclass;

	/*ステータス実数値*/
	private int[] status = new int[6];

	/*能力補正*/
	private int[] statuscorrection = {0, 0, 0, 0};

	/*状態異常*/
	private StatusAilmen stailmen = StatusAilmen.None;

	/*死亡*/
	private boolean isdead = false;



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

	public MinionClass getCharacterclass() {
		return characterclass;
	}

	public void setCharacterclass(MinionClass characterclass) {
		this.characterclass = characterclass;
	}


	public void addDamege(int damage){
		this.status[5] -= damage;
		if(this.status[5] < 1) {
			this.isdead = true;
		}
	}


}
