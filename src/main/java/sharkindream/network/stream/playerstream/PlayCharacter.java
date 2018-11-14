package sharkindream.network.stream.playerstream;

import sharkindream.gamecharacter.StatusAilmen;
import sharkindream.gamecharacter.Type;
import sharkindream.gamecharacter.minion.MinionClass;

public class PlayCharacter {

	/*操作主*/
	public int id;
	/*操作id 0:プレイヤ 1,2:ミニオン*/
	public int index;

	/*属性*/
	public Type type;

	/*キャラクタークラス*/
	public MinionClass characterclass;

	/*ステータス実数値*/
	public int[] status = new int[6];

	/*能力補正*/
	public int[] statuscorrection = {0, 0, 0, 0};

	/*状態異常*/
	public StatusAilmen stailmen = StatusAilmen.None;

	/*死亡*/
	public boolean isdead = false;

}
