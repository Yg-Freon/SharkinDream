package sharkindream.gui.gamescreen.playerstatus;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;
import sharkindream.network.stream.playerstream.Guest;

public class PlayerStatusController {

	private boolean isactive = false;

	@FXML
	public Text firtstname;
	@FXML
	public Text othername;

	private boolean isready;

	public void initstatuscircle(Guest guest) {
		//String name = guest.name;


		//firtstname.setText(name.substring(0, 1));
	//	othername.setText(name.substring(1));

	}

	@FXML
	public void onClicked(MouseEvent e) {
		System.out.println(e.getTarget());
		if(isactive) {
			((AnchorPane)((SVGPath)e.getTarget()).getParent()).setScaleX(1.0);
			((AnchorPane)((SVGPath)e.getTarget()).getParent()).setScaleY(1.0);
			isactive = false;
		}else {
			((AnchorPane)((SVGPath)e.getTarget()).getParent()).setScaleX(1.1);
			((AnchorPane)((SVGPath)e.getTarget()).getParent()).setScaleY(1.1);
			isactive = true;
		}
	}

	public void setReady(boolean flag) {
		this.isready = flag;
	}
}
