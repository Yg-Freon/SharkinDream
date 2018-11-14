	package sharkindream.gamecharacter.minion;

public enum MinionClass {
		None(0,"None",""),
		Soldier(1, "Soldier", "物理攻撃が得意、魔法耐性が低い"),
		Shielder(2, "Shielder", "打たれ強いが、攻撃性能は低い"),
		Witch(3, "Witch", "魔法攻撃が得意、撃たれ弱い"),
		Sage(4, "Sage", "魔法が得意、物理は苦手"),
		Bishop(5, "Bishop", "回復が得意"),
		MadScientist(6, "MadScientist", "状態異常にさせやすい"),	//丸さん
		BeastTamer(7, "BeastTamer", "攻撃を食らった後、その攻撃カードをコピーする"); //がみさん

	private int id;
	private String name;
	private String comment;

	private MinionClass(int id, String name, String comment) {
		this.id = id;
		this.name = name;
		this.comment = comment;
	}

	public int getid() {
		if(Integer.valueOf(id) == null) return -1;
		return id;
	}

	public String getName() {
		return name;
	}

	public String getComment() {
		return comment;
	}

	public static MinionClass getClassbyID(int id) {
		for(MinionClass minion: values()) {
			if(minion.getid() == id) return minion;
		}
		return null;
	}
}
