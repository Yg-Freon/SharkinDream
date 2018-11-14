package sharkindream.gamecharacter.minion.classtype;

public class MadScientist extends Prototype{

	//物理攻撃↓ 物理防御↓ 魔法攻撃↓ 魔法防御↑ HP↓ MP↑↑
	//状態異常の確率up
	public MadScientist() {
		super(0.9F, 0.9F, 0.9F, 1.1F, 0.9F, 1.2F);
	}
}
