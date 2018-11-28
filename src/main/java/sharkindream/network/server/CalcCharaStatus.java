package sharkindream.network.server;

import sharkindream.gamecharacter.GameCharacter;
import sharkindream.gamecharacter.GameCharacter.CharacterClass;
import sharkindream.gamecharacter.GameCharacter.Status;
import sharkindream.gamecharacter.Minion;
import sharkindream.gamecharacter.classtype.BeastTamer;
import sharkindream.gamecharacter.classtype.Bishop;
import sharkindream.gamecharacter.classtype.MadScientist;
import sharkindream.gamecharacter.classtype.None;
import sharkindream.gamecharacter.classtype.Prototype;
import sharkindream.gamecharacter.classtype.Sage;
import sharkindream.gamecharacter.classtype.Shielder;
import sharkindream.gamecharacter.classtype.Soldier;
import sharkindream.gamecharacter.classtype.Witch;


public class CalcCharaStatus {




	public int[] calcStatuslist(Minion minion) {

		int[] status = new int[6];
		status =  calcStatus(minion);

		int sumpara = 0;
		for(int para : minion.getstatuslist()) {
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



	private int[] calcStatus(Minion minion) {

		float[] statusmult = {1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f};
		int count = 0;
		for(int parameter: minion.getstatuslist()) {

			switch(parameter) {

			case 0:
				statusmult[count] = 1.0f / 2.0f;	//プラス1
				break;
			case 1:
				statusmult[count] = 1.0f / 1.9f;
				break;
			case 2:
				statusmult[count] = 1.0f / 1.75f;
				break;
			case 3:
				statusmult[count] = 1.0f / 1.55f;
				break;
			case 4:
				statusmult[count] = 1.0f / 1.3f;
				break;
			case 5:
				statusmult[count] = 1.0f;
				break;
			case 6:
				statusmult[count] = 1.3f;
				break;
			case 7:
				statusmult[count] = 1.55f;
				break;
			case 8:
				statusmult[count] = 1.75f;
				break;
			case 9:
				statusmult[count] = 1.9f;
				break;
			case 10:
				statusmult[count] = 2.0f;
				break;
			default:
				statusmult[count] = 0.1f;
				break;

			}

			count += 1;
		}

		int status[] = new int[6];


		status[0] = (int)(statusmult[0] * getstatus(minion.getMinionClass(), Status.Strength) * 150);
		status[1] = (int)(statusmult[1] * getstatus(minion.getMinionClass(), Status.Vitality) * 150);
		status[2] = (int)(statusmult[2] * getstatus(minion.getMinionClass(), Status.Intelligence) * 150);
		status[3] = (int)(statusmult[3] * getstatus(minion.getMinionClass(), Status.Mind) * 150);
		status[4] = (int)(statusmult[4] * getstatus(minion.getMinionClass(), Status.HP) * 150);
		status[5] = (int)(statusmult[5] * getstatus(minion.getMinionClass(), Status.MP) * 150);
		return status;
	}



	private float getstatus(CharacterClass classtype, GameCharacter.Status status) {

		Prototype chara = new None();
		switch(classtype) {
		case None:
			chara = new None();
			break;
		case Soldier:
			chara = new Soldier();
			break;
		case Shielder:
			chara = new Shielder();
			break;
		case Witch:
			chara = new Witch();
			break;
		case Sage:
			chara = new Sage();
			break;
		case Bishop:
			chara = new Bishop();
			break;
		case MadScientist:
			chara = new MadScientist();
			break;
		case BeastTamer:
			chara = new BeastTamer();
			break;
		}
		return chara.getstatusmult(status);
	}

}
