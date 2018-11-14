package sharkindream.network.stream.playerstream;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import sharkindream.deck.Deck;
import sharkindream.network.event.OnUpdateGuestHandler;
import sharkindream.party.Party;

public class GuestStream implements Serializable{


	private List<Guest> list = new ArrayList<>();

	boolean isAllReady = false;

	transient private OnUpdateGuestHandler listner = new OnUpdateGuestHandler();


	public Stream<List<Guest>> toStream(){
		return Stream.of(list);
	}

	public List<Guest> getList() {
		return list;
	}


	public void clearAllReady() {
		for(Guest guest: getList()) {
			guest.isready = false;
		}
	}


	public boolean isAllReady() {
		//全員がレディーを押したら終了
		boolean isallready = false;
		for(Guest guest: getList()) {
			isallready = guest.isready;
			if(!isallready) break;
		}
		return isallready;

	}


	public void addGuest(Guest guest) {
		guest.playerID = list.size();
		list.add(guest);
		onUpdateGuestStatus();
	}

	public void removeGuest(Guest guest) {
		int index = list.indexOf(guest);
		if(index != -1) list.remove(index);
		onUpdateGuestStatus();
	}

	public int getGuestID(Guest guest) {
		return guest.playerID;
	}


	public Guest getGuestStatusByID(int id) {
		 List<Guest> guestStatuses = list.stream().filter(l -> l.playerID == id).collect(Collectors.toList());
		if(guestStatuses.size() > 2) System.err.println("ERROR : IDが重複しています");

		Guest guest = null;
		if(guestStatuses.isEmpty()) {
			System.err.println("ERROR : ID :" + id + "が存在しません");
		}else {
			guest = guestStatuses.get(0);
		}
		return guest;
	}


	public void removeGuestByID(int id) {
		list.remove(list.indexOf(getGuestStatusByID(id)));
	}

	public void updateGuestByID(int id, Guest guest) {

		removeGuestByID(id);
		list.add(guest);
		if(checkAllReady())  onAllReady();
		onUpdateGuestStatus();
	}


	public Deck getDeckByID(int id) {
		return getGuestStatusByID(id).deck;
	}


	public Party getPartyByID(int id) {
		return getGuestStatusByID(id).party;
	}


	public String getPalyerNameByID(int id) {
		return getGuestStatusByID(id).getName();
	}


	public boolean getReadyByID(int id) {
		return getGuestStatusByID(id).isready;
	}


	private boolean checkAllReady() {
		boolean isallready = false;
		for(Guest guest: list) {
			isallready = guest.isready;
			if(!isallready) break;
		}

		isAllReady = isallready;
		return isAllReady;
	}


	//リスナ登録
	public void addUpdateGuestListner(OnUpdateGuestHandler handler) {
		this.listner = handler;
	}

	public void onAllReady() {
		if(listner != null) {
			listner.onAllReady();
		}
	}

	public void onUpdateGuestStatus() {
		if(listner != null) {
			listner.OnupdateGuestStatus(this);
		}
	}

	public boolean getIsAllReady() {
		return isAllReady;
	}


}
