package sharkindream.gamecharacter.minion.classtype;

public abstract class Prototype {


	public enum Status{
		strength,
		vitality,
		intelligence,
		mind,
		HP,
		MP;
	}

	public float mstrength = 1.0f;
	public float mvitality = 1.0f;
	public float mintelligence = 1.0f;
	public float mmind = 1.0f;
	public float mHP = 1.0f;
	public float mMP = 1.0f;


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
		case strength:
			return this.mstrength;
		case vitality:
			return this.mvitality;
		case intelligence:
			return this.mintelligence;
		case mind:
			return this.mmind;
		case HP:
			return this.mHP;
		case MP:
			return this.mMP;
		default:
			return this.mHP;
		}
	}

}
