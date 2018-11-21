package sharkindream.gamecharacter.minion.classtype;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Shielder extends Prototype{

	//物理攻撃- 物理防御↑ 魔法攻撃↓ 魔法防御- HP↑ MP↓

	public final static String name = "Shielder";
	public final static String comment = "物理攻撃に強く、魔法に弱い";

	Canvas canvas = new Canvas(50, 50);
	GraphicsContext g;

	public Shielder() {
		super(1.0F, 1.1F, 0.9F, 1.0F, 1.1F, 0.9F);
	}

	private void draw(Paint color) {

		g = canvas.getGraphicsContext2D();

		g.beginPath();

		g.appendSVGPath("M 25.00 13.03 C 30.12 14.29 34.99 16.00 40.33 16.27 C 39.54 21.60 38.46 26.90 37.07 32.11 C 36.28 34.92 35.29 38.13 33.32 40.36 C 31.20 42.85 27.82 44.42 24.99 46.00 C 20.82 43.67 16.79 41.95 14.73 37.30 C 12.03 30.67 10.85 23.31 9.67 16.27 C 15.01 16.00 19.88 14.29 25.00 13.03 Z");
		g.setFill(color);
		g.fill();
		g.closePath();

		g.beginPath();
		g.appendSVGPath("M 25.00 13.81 C 29.75 15.37 34.40 16.53 39.36 17.22 C 38.09 23.67 37.03 30.41 34.63 36.54 C 32.78 41.05 28.92 42.72 25.00 45.06 C 22.58 43.62 19.76 42.21 17.77 40.20 C 15.80 38.23 14.86 35.51 14.08 32.89 C 12.63 27.75 11.60 22.47 10.64 17.22 C 15.59 16.53 20.25 15.38 25.00 13.81 Z");
		g.setFill(Paint.valueOf("#f4f4f4"));
		g.fill();
		g.closePath();

		g.beginPath();
		g.appendSVGPath("M 15.63 19.90 C 18.90 21.08 21.85 22.82 24.69 24.80 C 27.55 22.84 30.52 21.13 33.72 19.78 C 31.42 22.36 28.58 24.25 26.05 26.56 C 27.38 30.19 29.83 33.30 31.20 36.94 C 28.49 34.23 26.60 31.01 24.63 27.76 C 22.69 30.93 20.88 34.00 18.48 36.85 C 19.62 33.23 21.48 30.01 23.46 26.80 C 20.83 24.57 17.96 22.44 15.63 19.90 Z");
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
