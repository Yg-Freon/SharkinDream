package sharkindream.gui.event;

import java.util.EventListener;

import sharkindream.network.client.ClientGamePlayFlow;

public interface OnMoveScreenIF extends EventListener{


	public void onMoveMainManuScreen();

	public void onMoveClientGameScreen(ClientGamePlayFlow cflow);
}
