package sharkindream.actioncard.resource;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Physical {


	private GraphicsContext g;
	private Canvas canvas;



	private void draw() {
		canvas = new Canvas(50,50);
		g = canvas.getGraphicsContext2D();

		g.beginPath();

		g.appendSVGPath("M 13.79 4.01 C 15.52 6.82 17.03 9.76 18.78 12.56 C 21.92 12.00 25.05 11.41 28.19 10.91 C 26.86 13.05 25.41 15.11 23.92 17.15 C 28.88 27.22 34.72 36.94 39.34 47.16 C 36.79 46.40 34.36 44.44 31.69 44.49 C 29.71 44.33 27.45 44.19 25.66 43.27 C 23.73 41.19 22.59 38.31 21.19 35.86 C 19.18 31.80 16.65 27.93 15.03 23.70 C 19.23 29.46 21.65 36.48 25.70 42.35 C 27.30 43.77 30.34 43.81 32.38 44.07 C 30.61 42.53 28.55 41.59 27.51 39.52 C 23.62 32.30 20.08 24.86 15.97 17.76 C 14.58 19.75 14.19 20.74 14.42 23.20 C 12.68 23.76 10.95 24.37 9.23 24.99 C 10.77 21.26 12.27 17.54 13.79 13.81 C 14.99 13.11 16.41 12.77 17.71 12.29 C 16.41 9.93 15.25 7.43 13.69 5.23 C 12.67 4.49 10.82 5.67 9.68 5.84 C 10.61 8.40 11.82 10.84 12.79 13.38 C 10.88 11.00 9.73 8.30 8.97 5.36 C 10.58 4.90 12.18 4.42 13.79 4.01 Z");
		g.setFill(Paint.valueOf("#000000"));
		g.fill();

		g.closePath();
	}



	public Canvas geticon() {
		this.draw();
		return canvas;
	}
}
