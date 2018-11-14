package sharkindream.network.event;

import java.util.EventListener;

import sharkindream.network.stream.playerstream.CharacterStatusStream;

public interface OnUpdatePlayerIF extends EventListener{

	void OnupdateMemberStatus(CharacterStatusStream characterStatusStream);

}
