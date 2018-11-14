package sharkindream.network.server;

import java.util.Random;

import sharkindream.actioncard.ActionCard;
import sharkindream.actioncard.ActionCard.Debuff;
import sharkindream.gamecharacter.minion.MinionClass;
import sharkindream.network.stream.playerstream.PlayCharacter;

public class CalcCardStatus {

	public int calcDamege(ActionCard card, PlayCharacter attacker, PlayCharacter target) {


		int damage = 0;
		Random rand = new Random();
		switch(card.resource) {

		case Physical:
			damage = (int) (card.power.getpower() * attacker.status[0] / target.status[1] * ((rand.nextFloat()/ 5.0f) + 0.5f));
			break;
		case Magical:
			damage = (int) (card.power.getpower() * attacker.status[2] / target.status[3] * ((rand.nextFloat()/ 5.0f) + 0.5f));
			break;
		case Debuff:
			damage = ((int) (card.power.getpower() * attacker.status[0] / target.status[1] * ((rand.nextFloat()/ 5.0f) + 0.5f))
					+ (int) (card.power.getpower() * attacker.status[2] / target.status[2] * ((rand.nextFloat()/ 5.0f) + 0.5f))) / 3;
			break;
		case Heal:
			damage = (int)(card.power.getpower() * (attacker.status[0] + attacker.status[2]) / 3) * -1;
			if(attacker.characterclass == MinionClass.Bishop) damage = (int)(damage * 1.5f);
			break;

		}

		return damage;
	}

	public int calcCost(ActionCard card) {

		int cost;
		int totalcost;
		switch(card.target) {
		case Enemy:
			cost = card.power.getcost();
			for(Debuff debuff : card.debufflist) {
				cost += debuff.getcost(true);
			}
			totalcost = (int) (cost * card.accuracy.getcost() * card.luck.getcost() * card.target.getcost());
			return totalcost;
		case Self:
			cost = card.power.getcost();
			for(Debuff debuff : card.debufflist) {
				cost += debuff.getcost(false);
			}
			totalcost = (int) (cost * card.accuracy.getcost() * card.luck.getcost() * card.target.getcost());
			return totalcost;

		case Random:
			cost = card.power.getcost();
			for(Debuff debuff : card.debufflist) {
				cost += debuff.getcost(true);
			}
			totalcost = (int) (cost * card.accuracy.getcost() * card.luck.getcost() * card.target.getcost());
			return totalcost;
		case Enemyis:
			cost = card.power.getcost();
			for(Debuff debuff : card.debufflist) {
				cost += debuff.getcost(true);
			}
			totalcost = (int) (cost * card.accuracy.getcost() * card.luck.getcost() * card.target.getcost());
			return totalcost;
		default:
			return 0;
		}

	}
}
