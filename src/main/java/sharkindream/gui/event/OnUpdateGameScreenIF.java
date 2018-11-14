package sharkindream.gui.event;

import java.util.EventListener;

import sharkindream.network.stream.playerstream.GuestStream;

public interface OnUpdateGameScreenIF extends EventListener{

	public void onUpdateGuestInfo(GuestStream guestinfo);
}
