package sharkindream.gamecharacter.minion.classtype;

public class Bishop extends Prototype{

	//物理攻撃↓ 物理防御- 魔法攻撃↓ 魔法防御↑ HP- MP-
	//回復に倍率がかかる1.5


	public Bishop() {
		super(0.9F, 0.9F, 0.9F, 1.1F, 0.9F, 1.0F);
	}

}