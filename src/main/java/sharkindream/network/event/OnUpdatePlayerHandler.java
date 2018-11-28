package sharkindream.network.event;

import java.util.Map;

import sharkindream.network.client.ClientGamePlayFlow.AttackAction;
import sharkindream.network.server.GameFlow;
import sharkindream.network.server.SrvThread;
import sharkindream.network.stream.playerstream.Guest;
import sharkindream.network.stream.playerstream.PlayerStatus;

public class OnUpdatePlayerHandler implements OnUpdatePlayerIF{


	@Override
	public void OnInitMemberStatus(Map<Guest, PlayerStatus> playerStatuslist) {
		for(SrvThread srvth: GameFlow.getClientMember()) {
			srvth.sendPlayeresStatus(playerStatuslist);
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

	@Override
	public void onAttackerActioned(AttackAction actionResult) {
		GameFlow.setAttackedFlag(actionResult);
	}

	@Override
	public void onActionAttack(int attackCard, int attackMinion, Guest target) {

	}
}
