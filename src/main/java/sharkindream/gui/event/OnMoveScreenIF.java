package sharkindream.gui.event;

import java.util.EventListener;

import javafx.scene.layout.AnchorPane;
import sharkindream.network.client.ClientGamePlayFlow;

public interface OnMoveScreenIF extends EventListener{

	public void onMoveTitle();

	public void onMoveMainManuScreen();

	public void onMoveEditDeckScreen();

	public void onMoveCreateMinionScreen();

	public void onMoveClientGameScreen(ClientGamePlayFlow cflow);

	public void onSwitchInfomationScreen(AnchorPane infoscreen);

}
