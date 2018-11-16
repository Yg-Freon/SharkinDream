package sharkindream;

import javafx.fxml.FXML;
import sharkindream.gui.event.OnMoveScreenHandler;

public class FormController {


	private OnMoveScreenHandler listener;

	@FXML
	void initialize() {
		this.listener = new OnMoveScreenHandler();
		moveTitle();
	}


	//リスナ登録
	public void addMoveScreenListener(OnMoveScreenHandler handler) {
		this.listener  = handler;
	}

	public void removeMoveScreenListener() {
		this.listener = null;
	}

	public void moveTitle() {
		if(listener != null) {
			listener.onMoveTitle();
		}
	}
}
