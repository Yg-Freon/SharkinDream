package sharkindream.actioncard;

import java.io.Serializable;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Paint;
import sharkindream.gamecharacter.StatusAilmen;
import sharkindream.gamecharacter.Type;

public class ActionCard implements Serializable{

	public enum Resource{

		Physical,
		Magical,
		Debuff,
		Heal;



		public Canvas getIcon(Paint color) {
			Canvas canvas;
			switch(this) {
			case Physical:
				canvas = (new sharkindream.actioncard.resource.Physical()).geticon(color);
				break;
			case Magical:
				canvas = (new sharkindream.actioncard.resource.Magical()).geticon(color);
				break;
			case Debuff:
				canvas = (new sharkindream.actioncard.resource.Debuff()).geticon(color);
				break;
			case Heal:
				canvas = (new sharkindream.actioncard.resource.Heal()).geticon(color);
				break;
			default:
				canvas = null;
				break;
			}
			return canvas;
		}
	}
	public enum Power{
		O(0, 5, "0", "O"),
		T(30, 15, "3", "T"),
		F(50, 25, "5", "F"),
		E(80, 50, "8", "E"),
		N(90, 60, "9", "N"),
		A(100, 75, "A", "A"),
		K(130, 90, "K", "K"),
		Joker(150, 100, "Joker", "J");

		private int power;
		private int cost;
		private String showname;
		private String showcenter;

		private Power(int power_, int cost_, String showname_, String showcenter_) {
			this.power = power_;
			this.cost = cost_;
			this.showname = showname_;
			this.showcenter = showcenter_;
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
		public String getshocenter() {
			return this.showcenter;
		}
	}
	public enum Debuff{
		none(0, 0, "0"),
		p1(-20, 30, "+1"),
		p2(-50, 80, "+2"),
		m1(30, -20, "-1"),
		m2(80, -50, "-2"),
		setnone(40, 40, "S0"),
		setp3(-80, 130, "P3"),
		setm3(120, -100, "M3");

		private boolean reverse;
		private int cost;
		private int selfcost;
		private String showname;
		private Debuff(int cost_, int selfcost_, String showname_) {
			this.cost = cost_;
			this.selfcost = selfcost_;
			this.showname = showname_;
		}
		public Debuff setreverse() {
			this.reverse = !this.reverse;
			return this;
			}
		public int getcost(boolean totarget) {
			boolean flag = (reverse) ?!totarget : totarget;
			return (flag) ? this.cost : this.selfcost;
		}
		public String getshowname() {
			return this.showname;
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
		l30(30, 1.35f),
		l50(50, 1.5f),
		l100(100, 1.8f);

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
	public enum Target{
		Enemy(1.0f),
		Self(1.0f),
		Random(0.6f),
		AllEnemies(2.25f);

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
		//this.debufflist;
		this.target = Target.Enemy;
		this.statusailmen = StatusAilmen.None;
		this.luck = Luck.l0;
	}

}
