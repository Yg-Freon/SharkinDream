package sharkindream.network.server;

import java.util.Random;

import sharkindream.actioncard.ActionCard;
import sharkindream.actioncard.ActionCard.Debuff;
import sharkindream.actioncard.ActionCard.Resource;
import sharkindream.gamecharacter.GameCharacter.CharacterClass;
import sharkindream.network.stream.playerstream.PlayCharacter;

public class CalcCardStatus {

	public int calcDamege(ActionCard card, PlayCharacter attacker, PlayCharacter target) {


		int damage = 0;
		Random rand = new Random();
		switch(card.resource) {

		case Physical:
			damage = (int) (card.power.getpower() * attacker.getStatus()[0] / target.getStatus()[1] * ((rand.nextFloat()/ 5.0f) + 0.5f));
			break;
		case Magical:
			damage = (int) (card.power.getpower() * attacker.getStatus()[2] / target.getStatus()[3] * ((rand.nextFloat()/ 5.0f) + 0.5f));
			break;
		case Debuff:
			damage = ((int) (card.power.getpower() * attacker.getStatus()[0] / target.getStatus()[1] * ((rand.nextFloat()/ 5.0f) + 0.5f))
					+ (int) (card.power.getpower() * attacker.getStatus()[2] / target.getStatus()[2] * ((rand.nextFloat()/ 5.0f) + 0.5f))) / 3;
			break;
		case Heal:
			damage = (int)(card.power.getpower() * (attacker.getStatus()[0] + attacker.getStatus()[2]) / 3) * -1;
			if(attacker.getCharacterClass() == CharacterClass.Bishop) damage = (int)(damage * 1.5f);
			break;

		}

		return damage;
	}

	public int calcCost(ActionCard card) {

		int cost = 0;
		int pcost = 0;
		int dcost = 0;
		int totalcost;
		switch(card.target) {
		case Enemy:
			pcost = card.power.getcost();
			if(!(card.resource == Resource.Physical || card.resource == Resource.Magical)) pcost *= 2;
			for(Debuff debuff : card.debufflist) {
				dcost += debuff.getcost(true);
			}
			if(dcost < 0) dcost = 0;
			if(card.resource != Resource.Debuff) dcost *= 2;
			cost = pcost + dcost;
			totalcost = (int) (cost * card.accuracy.getcost() * card.luck.getcost() * card.target.getcost());
			break;

		case Self:
			pcost = card.power.getcost();
			if(!(card.resource == Resource.Physical || card.resource == Resource.Magical)) pcost *= 2;
			for(Debuff debuff : card.debufflist) {
				dcost += debuff.getcost(false);
			}
			if(dcost < 0) dcost = 0;
			if(card.resource != Resource.Debuff) dcost *= 2;
			cost = pcost + dcost;
			totalcost = (int) (cost * card.accuracy.getcost() * card.luck.getcost() * card.target.getcost());
			break;

		case Random:
			pcost = card.power.getcost();
			if(!(card.resource == Resource.Physical || card.resource == Resource.Magical)) pcost *= 2;
			for(Debuff debuff : card.debufflist) {
				dcost += debuff.getcost(true);
			}
			if(dcost < 0) dcost = 0;
			if(card.resource != Resource.Debuff) dcost *= 2;
			cost = pcost + dcost;
			totalcost = (int) (cost * card.accuracy.getcost() * card.luck.getcost() * card.target.getcost());
			break;

		case AllEnemies:
			pcost = card.power.getcost();
			if(!(card.resource == Resource.Physical || card.resource == Resource.Magical)) pcost *= 2;
			for(Debuff debuff : card.debufflist) {
				dcost += debuff.getcost(true);
			}
			if(dcost < 0) dcost = 0;
			if(card.resource != Resource.Debuff) dcost *= 2;
			cost = pcost + dcost;
			totalcost = (int) (cost * card.accuracy.getcost() * card.luck.getcost() * card.target.getcost());
			break;

		default:
			totalcost = 0;
			break;
		}
		if(totalcost < 10) totalcost = 10;
		return totalcost;

	}
}
