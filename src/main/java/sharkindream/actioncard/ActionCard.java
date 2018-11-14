package sharkindream.actioncard;

import java.io.Serializable;

import sharkindream.gamecharacter.StatusAilmen;
import sharkindream.gamecharacter.Type;

public class ActionCard implements Serializable{

	public enum Resource{
		Physical,
		Magical,
		Debuff,
		Heal
	}
	public enum Power{
		O(0, 10, "0"),
		T(30, 15, "3"),
		F(50, 30, "5"),
		E(80, 50, "8"),
		N(90, 70, "9"),
		A(100, 100, "A"),
		K(130, 140, "K"),
		Joker(150, 180, "Joker");

		private int power;
		int cost;
		String showname;

		private Power(int power_, int cost_, String showname_) {
			this.power = power_;
			this.cost = cost_;
			this.showname = showname_;
		}
		public int getpower() {
			return this.power;
		}
		public int getcost() {
			return this.cost;
		}
		public String getshowname() {
			return this.showname;
		}
	}
	public enum Debuff{
		none(30, 30),
		p1(10, 100),
		p2(-10, 140),
		m1(100, 10),
		m2(140, -10),
		snone(120, 80),
		sp3(-40, 180),
		sm3(180, -40);

		private boolean reverse;
		private int cost;
		private int selfcost;
		private Debuff(int cost_, int selfcost_) {
			this.cost = cost_;
			this.selfcost = selfcost_;
		}
		public Debuff setreverse() {
			this.reverse = !this.reverse;
			return this;
			}
		public int getcost(boolean totarget) {
			boolean flag = (reverse) ?!totarget : totarget;
			return (flag) ? this.cost : this.selfcost;
		}
	}


	public enum Accuracy{
		a30(30, 0.1f),
		a50(50, 0.4f),
		a60(60, 0.6f),
		a80(80, 0.9f),
		a90(90, 1.0f),
		a100(100, 1.1f);

		private int accuracy;
		private float cost;
		private Accuracy(int accuracy_, float cost_) {
			this.accuracy = accuracy_;
			this.cost = cost_;
		}
		public int getaccuracy() {
			return this.accuracy;
		}
		public float getcost() {
			return this.cost;
		}
	}

	public enum Luck{
		l0(0, 1.0f),
		l5(5, 1.1f),
		l10(10, 1.2f),
		l20(20, 1.35f),
		l30(30, 1.5f),
		l50(50, 1.8f);

		private int luck;
		private float cost;
		private Luck(int luck_, float cost_) {
			this.luck = luck_;
			this.cost = cost_;
		}
		public int getluck() {
			return this.luck;
		}
		public float getcost() {
			return this.cost;
		}
	}

	public Power power;
	public Accuracy accuracy;
	public int buff[] = new int[4];
	public enum Target{
		Enemy(1.0f),
		Self(1.0f),
		Random(0.6f),
		Enemyis(2.25f);

		float cost;
		private Target(float cost_) {
			this.cost = cost_;
		}
		public float getcost() {
			return this.cost;
		}
	}

	public Type type = Type.None;
	public int cost;
	public Resource resource;
	public Target target;
	public StatusAilmen statusailmen;
	public Luck luck;	//状態異常の命中確立
	public Debuff[] debufflist = {Debuff.none, Debuff.none.setreverse(), Debuff.none, Debuff.none.setreverse()};


	public ActionCard() {
		this.type = Type.Fire;
		this.resource = Resource.Physical;
		this.power = Power.E;
		this.accuracy = Accuracy.a100;
		this.buff = new int[]{0,0,0,0};
		this.target = Target.Enemy;
		this.statusailmen = StatusAilmen.None;
		this.luck = Luck.l0;
	}



}
