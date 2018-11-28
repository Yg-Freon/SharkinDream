package sharkindream.gamecharacter.classtype;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class BeastTamer extends Prototype{

	public final static String name = "BeastTamer";
	public final static String comment = "敵から攻撃を受けたとき、そのカードをスロットに加える";

	Canvas canvas = new Canvas(50, 50);
	GraphicsContext g;

	public BeastTamer() {
		super(1.1f, 0.9f, 1.1f, 0.9f, 0.9f, 1.0f);
	}


	private void draw(Paint color, Paint backcolor) {

		g = canvas.getGraphicsContext2D();

		g.beginPath();

		g.appendSVGPath("M 30.04 12.03 C 34.93 12.42 39.34 14.28 43.38 16.99 C 41.63 16.61 39.86 15.99 38.08 15.86 C 35.67 16.37 33.45 17.41 31.10 18.09 C 30.21 16.47 29.52 14.70 28.77 13.01 C 25.07 13.09 21.10 12.63 17.46 13.39 C 16.26 15.06 15.58 17.16 14.75 19.04 C 13.06 18.11 11.35 17.21 9.64 16.29 C 8.53 16.57 7.41 16.84 6.29 17.11 C 7.33 16.36 8.39 15.64 9.46 14.93 C 10.58 15.43 11.62 16.12 12.68 16.73 C 13.31 16.91 13.94 17.08 14.57 17.26 C 15.01 16.16 15.46 15.07 15.91 13.97 C 14.02 14.36 12.12 14.75 10.22 15.12 C 16.53 11.47 23.01 11.79 30.04 12.03 Z");
		g.setFill(color);
		g.fill();
		g.closePath();

		g.beginPath();
		g.appendSVGPath("M 15.32 36.14 C 16.24 38.07 17.12 40.01 18.01 41.95 C 15.11 42.02 13.16 41.30 10.63 39.94 C 12.52 40.25 14.39 40.60 16.28 40.90 C 15.79 39.71 15.30 38.52 14.80 37.34 C 13.26 38.23 11.76 39.20 10.18 40.00 C 8.99 39.45 7.89 38.76 6.79 38.06 C 7.84 38.22 8.88 38.40 9.92 38.59 C 11.72 37.77 13.52 36.95 15.32 36.14 Z");
		g.setFill(color);
		g.fill();
		g.closePath();

	}

	public Canvas geticon() {

		draw(Paint.valueOf("#000000"), Paint.valueOf("#f4f4f4"));
		return canvas;
	}

	@Override
	public Canvas geticon(Paint color) {
		draw(color, Paint.valueOf("#f4f4f4"));
		return canvas;
	}

	@Override
	public String getname() {
		// TODO 自動生成されたメソッド・スタブ
		return this.name;
	}

	@Override
	public String getcomment() {
		// TODO 自動生成されたメソッド・スタブ
		return this.comment;
	}


	@Override
	public Canvas geticon(Paint color, Paint backcolor) {
		draw(color, backcolor);
		return canvas;
	}
}
