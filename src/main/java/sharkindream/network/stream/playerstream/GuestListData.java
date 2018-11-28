package sharkindream.network.stream.playerstream;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import sharkindream.network.server.GameFlow;
import sharkindream.network.server.SrvThread;

public class GuestListData implements Serializable{

	private static List<Guest> guestlist = new ArrayList<>();

	private static boolean isAllReady = false;


	public List<Guest> getlist(){
		return this.guestlist;
	}

	public static void clearAllReady() {
		for(Guest guest: guestlist) {
			guest.isready = false;
		}
	}

	public static void addGuest(Guest guest) {
		guest.playerID = guestlist.size();
		guestlist.add(guest);
		onUpdateGuestStatus();
	}

	public static Guest getGuestStatusByID(int id) {
		 List<Guest> guestStatuses = guestlist.stream().filter(l -> l.playerID == id).collect(Collectors.toList());
		if(guestStatuses.size() > 2) System.err.println("ERROR : IDが重複しています");

		Guest guest = null;
		if(guestStatuses.isEmpty()) {
			System.err.println("ERROR : ID :" + id + "が存在しません");
		}else {
			guest = guestStatuses.get(0);
		}
		return guest;
	}

	public static void removeGuestByID(int id) {
		guestlist.remove(guestlist.indexOf(getGuestStatusByID(id)));
	}


	public static  void updateGuestByID(Guest guest) {

		removeGuestByID(guest.playerID);
		guestlist.add(guest);
		onUpdateGuestStatus();
		if(checkAllReady()) {
			onAllReady();
		}
	}

	private static boolean checkAllReady() {
		boolean isallready = false;
		if(guestlist.size() > 0) {	//-----------------------------
			for(Guest guest: guestlist) {
				isallready = guest.isready;
				if(!isallready) break;
			}
		}

		isAllReady = isallready;
		return isAllReady;
	}




	public static void onAllReady() {
		GameFlow.setIsrecruit(false);
	}

	public static void onUpdateGuestStatus() {
		for(SrvThread srvth: GameFlow.getClientMember()) {
			srvth.sendGuestStatus(new GuestListData());
		}
	}

	public boolean getIsAllReady() {
		return isAllReady;
	}
}
