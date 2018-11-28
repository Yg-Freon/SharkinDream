package sharkindream.network.event;

import java.util.EventListener;

import sharkindream.network.stream.playerstream.Guest;
import sharkindream.network.stream.playerstream.GuestListData;

public interface OnUpdateGuestIF extends EventListener{

	public void onAllReady();

	public void onUpdateGuestInfo(Guest guest);

	public void OnupdateGuestStatus(GuestListData guestlistdata);



}
