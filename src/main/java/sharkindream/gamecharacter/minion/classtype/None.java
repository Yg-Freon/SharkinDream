package sharkindream.gamecharacter.minion.classtype;

public class None extends Prototype{

	//プレイヤーのステータス
	//物理攻撃↓ 物理防御↓ 魔法攻撃↓ 魔法防御↓ HP60 MP無限
	//状態異常の確率up
	public None() {
		super(0.7F, 0.7F, 0.7F, 0.7F, 1.0F, 1.0F);
	}

}
