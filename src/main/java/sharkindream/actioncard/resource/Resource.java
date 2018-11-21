package sharkindream.actioncard.resource;

import javafx.scene.canvas.Canvas;

public class Resource {

	protected Canvas canvas;

	Resource(Canvas canvas_){
		canvas = canvas_;
	}


	public Canvas geticon(String color) {

		return canvas;
	};
}
