	package sharkindream.gamecharacter.minion;

import sharkindream.gamecharacter.minion.classtype.Prototype;

public enum MinionClass {
		None,
		Soldier,
		Shielder,
		Witch,
		Sage,
		Bishop,
		MadScientist,
		BeastTamer;

	public Prototype getMinionclass() {
		switch(this) {
		case Soldier:
			return new sharkindream.gamecharacter.minion.classtype.Soldier();
		case Shielder:
			return new sharkindream.gamecharacter.minion.classtype.Shielder();
		case Witch:
			return new sharkindream.gamecharacter.minion.classtype.Witch();
		case Sage:
			return new sharkindream.gamecharacter.minion.classtype.Sage();
		case Bishop:
			return new sharkindream.gamecharacter.minion.classtype.Bishop();
		case MadScientist:
			return new sharkindream.gamecharacter.minion.classtype.MadScientist();
		case BeastTamer:
			return new sharkindream.gamecharacter.minion.classtype.BeastTamer();
		default :
			return new sharkindream.gamecharacter.minion.classtype.None();

		}
	}
}
