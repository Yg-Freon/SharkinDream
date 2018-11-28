package sharkindream.gui.event;

import java.util.EventListener;

import sharkindream.network.stream.playerstream.GuestListData;

public interface OnUpdateGameScreenIF extends EventListener{

	public void onUpdateGuestInfo(GuestListData guestinfo);
}
