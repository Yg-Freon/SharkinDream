package sharkindream.gamecharacter.minion.classtype;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Paint;
import sharkindream.gamecharacter.GameCharacter.Status;

public abstract class Prototype {


	protected String name = "";
	protected String comment = "";


	protected float mstrength = 1.0f;
	protected float mvitality = 1.0f;
	protected float mintelligence = 1.0f;
	protected float mmind = 1.0f;
	protected float mHP = 1.0f;
	protected float mMP = 1.0f;


	public Prototype(float mstr, float mvit, float mint, float mmin, float mhp, float mmp) {
		this.mstrength = mstr;
		this.mvitality = mvit;
		this.mintelligence = mint;
		this.mmind = mmin;
		this.mHP = mhp;
		this.mMP = mmp;
	}

	public float getstatusmult(Status status) {
		switch(status) {
		case Strength:
			return this.mstrength;
		case Vitality:
			return this.mvitality;
		case Intelligence:
			return this.mintelligence;
		case Mind:
			return this.mmind;
		case HP:
			return this.mHP;
		case MP:
			return this.mMP;
		default:
			return this.mHP;
		}
	}

	public abstract String getname();
	public abstract Canvas geticon(Paint color);
	public abstract String getcomment();


}
