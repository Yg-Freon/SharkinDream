package sharkindream.actioncard.resource;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Magical extends Resource{



	Canvas canvas;
	GraphicsContext g;

	public Magical() {
		super(new Canvas(50,50));
		canvas = super.canvas;
	}

	private void draw(Paint color) {
		g = canvas.getGraphicsContext2D();

		g.beginPath();

		g.appendSVGPath("M 26.83 4.39 C 26.11 6.19 23.88 7.22 22.49 8.52 C 18.73 11.56 16.96 16.22 16.84 20.96 C 17.15 24.07 18.55 27.16 20.21 29.78 C 23.55 34.87 30.05 37.10 35.91 36.03 C 38.76 35.24 40.91 33.50 43.90 33.05 C 43.51 35.32 42.14 36.80 41.08 38.75 C 43.18 39.35 47.81 38.94 49.02 40.90 C 44.01 43.30 38.34 41.66 33.03 43.98 C 28.41 46.07 21.59 46.61 17.17 43.94 C 21.86 44.63 28.10 46.58 32.15 43.12 C 25.34 42.57 18.73 43.62 11.95 42.75 C 8.70 42.28 4.82 42.47 1.86 40.95 C 3.00 39.28 6.55 39.59 8.38 38.72 C 8.53 38.21 8.67 37.71 8.81 37.20 C 10.21 37.88 11.17 38.85 12.84 38.70 C 20.20 38.30 27.55 38.12 34.91 38.57 C 36.39 38.62 38.01 38.80 39.46 38.47 C 40.74 37.54 41.30 36.27 42.01 34.92 C 37.46 36.50 33.27 37.62 28.42 36.43 C 19.36 34.54 14.07 23.50 17.14 15.02 C 18.01 10.88 21.21 9.20 23.11 5.74 C 18.81 5.37 14.67 7.82 11.60 10.61 C 7.49 14.31 5.51 19.63 4.84 25.00 C 5.34 28.38 6.42 31.69 7.20 35.02 C 2.15 26.64 4.36 15.08 12.01 9.04 C 16.31 5.35 21.19 3.96 26.83 4.39 Z");
		g.setFill(color);
		g.fill();
		g.closePath();

		g.beginPath();
		g.appendSVGPath("M 31.45 9.64 C 32.35 9.04 33.48 10.33 34.02 10.93 C 35.92 13.35 36.21 17.13 36.49 20.10 C 36.47 23.53 35.62 28.36 32.59 30.45 C 31.12 30.74 30.15 29.15 29.11 28.33 C 30.35 28.54 31.56 28.80 32.82 28.94 C 36.09 25.67 36.62 19.38 35.05 15.10 C 34.52 13.41 33.61 10.21 31.20 11.03 C 29.42 12.44 28.83 15.40 28.22 17.49 C 27.36 20.24 28.44 22.38 28.80 25.10 C 27.09 23.18 27.31 19.85 27.64 17.47 C 28.10 14.73 28.87 11.17 31.45 9.64 Z");
		g.setFill(color);
		g.fill();
		g.closePath();
	}

	public Canvas geticon() {

		draw(Paint.valueOf("#000000"));
		return canvas;
	}

	public Canvas geticon(Paint color) {
		draw(color);
		return canvas;
	}
}
