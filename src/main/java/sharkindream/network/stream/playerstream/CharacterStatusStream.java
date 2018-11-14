package sharkindream.network.stream.playerstream;

import java.util.HashMap;
import java.util.Map;

import sharkindream.network.event.OnUpdatePlayerHandler;


public class CharacterStatusStream {


	private OnUpdatePlayerHandler handler = new OnUpdatePlayerHandler();

	private PlayCharacter[] member = new PlayCharacter[3];
	private Map<Integer, PlayCharacter[]> memberlist = new HashMap<>();

	private boolean isFinishGame = false;


	public Map<Integer, PlayCharacter[]> getmemberlist() {
		return memberlist;
	}

	public void addmember(PlayCharacter[] member_) {
		int id = memberlist.size();

		for(PlayCharacter chara : member_) {
			chara.id = id;
		}
		memberlist.put(id, member_);
	}

	public PlayCharacter[] getMemberByID(int id) {

		if(memberlist.containsKey(id)) {
			return memberlist.get(id);
		}else {
			System.out.println("ERROR: IDが存在しません");
			return null;
		}

	}

	public void removeMember(PlayCharacter[] member_) {

		int index = member[0].id;
		if(index != -1) memberlist.remove(index);
	}

	public void removeMemberByID(int id) {
		memberlist.remove(getMemberByID(id));
	}

	public void updateMemberByID(int id, PlayCharacter[] member_) {

		removeMemberByID(id);
		addmember(member_);
	}



	//リスナ登録
	public void addUpdatePlayerHandler(OnUpdatePlayerHandler handler) {
		this.handler = handler;
	}

	public void onUpdateMemberStatus() {
		if(handler != null) {
			handler.OnupdateMemberStatus(this);
		}
	}
}
