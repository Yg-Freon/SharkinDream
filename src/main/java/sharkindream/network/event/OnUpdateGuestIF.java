package sharkindream.network.event;

import java.util.EventListener;

import sharkindream.network.stream.playerstream.Guest;
import sharkindream.network.stream.playerstream.GuestStream;

public interface OnUpdateGuestIF extends EventListener{

	public void onAllReady();

	public void OnupdateGuestStatus(GuestStream guestStream);

	void onUpdateGuestInfo(Guest guest);

}
