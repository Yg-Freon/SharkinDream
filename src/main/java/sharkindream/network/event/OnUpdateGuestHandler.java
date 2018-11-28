package sharkindream.network.event;

import sharkindream.network.client.Client;
import sharkindream.network.server.GameFlow;
import sharkindream.network.server.SrvThread;
import sharkindream.network.stream.playerstream.Guest;
import sharkindream.network.stream.playerstream.GuestListData;

public class OnUpdateGuestHandler implements OnUpdateGuestIF{

	@Override
	public void onAllReady() {
		GameFlow.setIsrecruit(false);
	}

	@Override
	public void OnupdateGuestStatus(GuestListData guestlistdata) {
		for(SrvThread srvth: GameFlow.getClientMember()) {
			srvth.sendGuestStatus(guestlistdata);
		}
	}

	@Override
	public void onUpdateGuestInfo(Guest guest) {

		Client.cflow.sendGuestInfo(guest);
	}

}
