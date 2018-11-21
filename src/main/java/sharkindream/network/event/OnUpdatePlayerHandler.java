package sharkindream.network.event;

import java.util.List;
import java.util.Map;

import sharkindream.network.server.GameFlow;
import sharkindream.network.server.SrvThread;
import sharkindream.network.stream.playerstream.Guest;
import sharkindream.network.stream.playerstream.PlayerStatus;

public class OnUpdatePlayerHandler implements OnUpdatePlayerIF{


	@Override
	public void OnInitMemberStatus(List<PlayerStatus> playerlist) {
		for(SrvThread srvth: GameFlow.getClientMember()) {
			srvth.sendPlayeresStatus(playerlist);
		}
	}

	@Override
	public void onAttackTurn(Guest id, PlayerStatus player) {
	//攻撃しますか？
		for(SrvThread srvth: GameFlow.getClientMember()) {
			srvth.sendCanAttack(id, player);
		}
	}

	@Override
	public void onDefenceTurn(Map<Guest, PlayerStatus> playerStatuslist) {
		//防御しますか？
	}

	@Override
	public void onTurnEnd() {
		//戦闘シーン
	}

	@Override
	public void onSwitchTurn() {
		//ターンの切り替わり
	}



}
