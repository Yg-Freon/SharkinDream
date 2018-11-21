package sharkindream.gamecharacter.minion.classtype;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Bishop extends Prototype{

	//物理攻撃↓ 物理防御- 魔法攻撃↓ 魔法防御↑ HP- MP-
	//回復に倍率がかかる1.5

	public final static String name = "Bishop";
	public final static String comment = "回復力に高い倍率がかかる";

	Canvas canvas = new Canvas(50, 50);
	GraphicsContext g;


	public Bishop() {
		super(0.9F, 0.9F, 0.9F, 1.1F, 0.9F, 1.0F);
	}

	private void draw(Paint color) {

		g = canvas.getGraphicsContext2D();

		g.beginPath();

		g.appendSVGPath("M 23.42 12.44 C 25.75 13.67 27.80 15.53 29.86 17.16 C 33.45 20.21 37.04 22.36 39.11 26.81 C 41.54 30.89 38.10 36.04 37.80 40.50 C 37.17 39.38 36.52 38.27 35.87 37.16 C 32.50 36.93 29.37 35.86 25.99 35.91 C 20.52 35.85 13.87 35.91 9.11 38.96 C 8.55 35.46 6.93 31.36 7.79 27.88 C 8.86 24.06 12.14 21.52 15.03 19.06 C 17.80 16.84 20.48 14.43 23.42 12.44 Z");
		g.setFill(color);
		g.fill();
		g.closePath();

		g.beginPath();
		g.appendSVGPath("M 23.17 13.77 C 24.73 14.15 26.05 15.53 27.34 16.45 C 23.86 22.67 25.33 28.57 24.86 35.30 C 23.93 35.27 23.00 35.24 22.07 35.21 C 21.76 28.63 23.04 22.80 19.79 16.69 C 20.89 15.68 21.97 14.66 23.17 13.77 Z");
		g.setFill(Paint.valueOf("#f4f4f4"));
		g.fill();
		g.closePath();

		g.beginPath();
		g.appendSVGPath("M 18.81 17.01 C 22.08 22.88 20.77 28.46 20.99 34.83 C 17.23 35.08 13.62 35.91 10.03 37.04 C 8.86 33.81 7.54 29.80 9.36 26.56 C 11.50 22.76 15.77 20.11 18.81 17.01 Z");
		g.setFill(Paint.valueOf("#f4f4f4"));
		g.fill();
		g.closePath();

		g.beginPath();
		g.appendSVGPath("M 28.16 16.98 C 32.37 20.93 36.98 23.09 38.90 28.94 C 39.27 31.55 38.09 34.43 37.77 37.04 C 33.91 35.92 30.05 35.00 26.01 34.86 C 26.25 28.45 24.88 22.89 28.16 16.98 Z");
		g.setFill(Paint.valueOf("#f4f4f4"));
		g.fill();
		g.closePath();

		g.beginPath();
		g.appendSVGPath("M 18.01 38.17 C 24.01 37.55 30.01 38.05 35.86 39.45 L 37.44 40.51 C 30.35 39.48 23.74 38.14 16.53 39.40 C 14.35 39.86 12.20 40.17 9.98 40.35 L 10.00 39.06 C 12.74 39.14 15.34 38.67 18.01 38.17 Z");
		g.setFill(color);
		g.fill();
		g.closePath();

	}

	public Canvas geticon() {

		draw(Paint.valueOf("#000000"));
		return canvas;
	}

	@Override
	public Canvas geticon(Paint color) {
		draw(color);
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
}