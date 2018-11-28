package sharkindream.network.event;

import java.util.EventListener;
import java.util.Map;

import sharkindream.network.client.ClientGamePlayFlow.AttackAction;
import sharkindream.network.stream.playerstream.Guest;
import sharkindream.network.stream.playerstream.PlayerStatus;

public interface OnUpdatePlayerIF extends EventListener{

	void OnInitMemberStatus(Map<Guest, PlayerStatus> playerStatuslist);

	void onAttackTurn(Guest id, PlayerStatus player);

	void onDefenceTurn(Map<Guest, PlayerStatus> playerStatuslist);

	void onTurnEnd();

	void onSwitchTurn();

	void onAttackerActioned(AttackAction actionResult);

	void onActionAttack(int attackCard, int attackMinion, Guest target);



}
