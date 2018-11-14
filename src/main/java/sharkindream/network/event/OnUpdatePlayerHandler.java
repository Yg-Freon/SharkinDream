package sharkindream.network.event;

import sharkindream.network.server.GameFlow;
import sharkindream.network.server.SrvThread;
import sharkindream.network.stream.playerstream.CharacterStatusStream;

public class OnUpdatePlayerHandler implements OnUpdatePlayerIF{

	@Override
	public void OnupdateMemberStatus(CharacterStatusStream characterStatusStream) {
		for(SrvThread srvth: GameFlow.getClientMember()) {
			srvth.sendPlayerStatus(characterStatusStream);
		}
	}




}
