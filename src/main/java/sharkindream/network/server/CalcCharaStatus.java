package sharkindream.network.server;

import sharkindream.gamecharacter.minion.MinionClass;
import sharkindream.gamecharacter.minion.classtype.BeastTamer;
import sharkindream.gamecharacter.minion.classtype.Bishop;
import sharkindream.gamecharacter.minion.classtype.MadScientist;
import sharkindream.gamecharacter.minion.classtype.None;
import sharkindream.gamecharacter.minion.classtype.Prototype;
import sharkindream.gamecharacter.minion.classtype.Sage;
import sharkindream.gamecharacter.minion.classtype.Shielder;
import sharkindream.gamecharacter.minion.classtype.Soldier;
import sharkindream.gamecharacter.minion.classtype.Witch;

public class CalcCharaStatus {




	private Prototype.Status[]  statusnamelist= {	Prototype.Status.strength,
													Prototype.Status.vitality,
													Prototype.Status.intelligence,
													Prototype.Status.mind,
													Prototype.Status.HP,
													Prototype.Status.MP
													};


	public int[] calcStatuslist(int[] parameterlist, MinionClass classType) {

		int[] status = new int[6];
		for(int i=0; i < 6; ++i) {
			status[i] = calcStatus(parameterlist[i], classType, statusnamelist[i]);
		}

		int sumpara = 0;
		for(int para : parameterlist) {
			sumpara += para;
		}

		float starank = 1.0f;
		switch(sumpara / 10) {
		case 0:
			starank = 3.0f;
			break;
		case 1:
			starank = 1.7f;
			break;
		case 2:
			starank = 1.0f;
			break;
		case 3:
			starank = 0.9f;
			break;
		case 4:
			starank = 0.72f;
			break;
		case 5:
			starank = 0.46f;
			break;
		case 6:
			 starank = 0.24f;
		default:
			starank = 1.0f;
			break;
		}

		for(int i=0; i < 6; ++i) {
			status[i] = (int)(status[i] * starank);
		}

		return status;
	}


	public int calcStatus(int parameter, MinionClass classtype, Prototype.Status mstatus) {

		float statusmult = 1.0f;
		switch(parameter) {

		case 0:
			parameter = 1;
			statusmult = 1.0f;	//プラス1
			break;
		case 1:
			statusmult = 1.15f;
			break;
		case 2:
			statusmult = 1.1f;
			break;
		case 3:
			statusmult = 1.05f;
			break;
		case 7:
			statusmult = 0.95f;
			break;
		case 8:
			statusmult = 0.9f;
			break;
		case 9:
			statusmult = 0.85f;
			break;
		case 10:
			statusmult = 0.8f;
			break;
		default:
			statusmult = 1.0f;
			break;

		}

		int status = (int) (parameter * statusmult * getstatus(classtype, mstatus) * 30);

		//int status = (int) ((1 / (Math.pow(Math.E , -(parameter - 5) * 2) + 1) + 0.5) * getstatus(classtype, mstatus))  * 130;

		//int status = (int) (((Math.tanh((parameter - 5) /2) + 1)/2 + 0.5) * getstatus(classtype, mstatus) * 300) ;
		return status;
	}

	private float getstatus(MinionClass classtype, Prototype.Status status ) {

		Prototype chara = new None();
		switch(classtype.getid()) {
		case 0:
			chara = new None();
			break;
		case 1:
			chara = new Soldier();
			break;
		case 2:
			chara = new Shielder();
			break;
		case 3:
			chara = new Witch();
			break;
		case 4:
			chara = new Sage();
			break;
		case 5:
			chara = new Bishop();
			break;
		case 6:
			chara = new MadScientist();
			break;
		case 7:
			chara = new BeastTamer();
			break;
		}
		return chara.getstatusmult(status);
	}

}
