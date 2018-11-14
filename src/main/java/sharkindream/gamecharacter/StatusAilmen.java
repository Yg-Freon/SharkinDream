package sharkindream.gamecharacter;

public enum StatusAilmen {

	None,			//通常
	Burn,			//やけど スリップ
	Drown,			//溺れ 魔法ダウン
	Bind,			//拘束 物理ダウン
	Confusion,		//混乱 対象ランダム
	Berserker,		//狂化 ステータス異常 => 攻撃↑防御↓
	Invulnerable,	//無敵 ダメージ0

}
