package sharkindream.gamecharacter.minion.classtype;

public class Soldier extends Prototype{

	//物理攻撃↑ 物理防御↑ 魔法攻撃- 魔法防御↓ HP- MP↓

	public static String name = "Soldier";
	public static String comment = "物理攻撃に強く、魔法に弱い";

	public Soldier() {
		super(1.1F, 1.1F, 1.0F	, 0.9F, 1.0F, 0.9F);
	}

}
