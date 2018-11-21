package sharkindream.network.event;

import java.util.EventListener;
import java.util.List;
import java.util.Map;

import sharkindream.network.stream.playerstream.Guest;
import sharkindream.network.stream.playerstream.PlayerStatus;

public interface OnUpdatePlayerIF extends EventListener{

	void OnInitMemberStatus(List<PlayerStatus> playerlist);

	void onAttackTurn(Guest id, PlayerStatus player);

	void onDefenceTurn(Map<Guest, PlayerStatus> playerStatuslist);

	void onTurnEnd();

	void onSwitchTurn();



}
