package sharkindream.gamecharacter.minion.classtype;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Witch extends Prototype{

	//物理攻撃- 物理防御↓ 魔法攻撃↑ 魔法防御- HP↓ MP↑

	public final static String name = "Witch";
	public final static String comment = "魔法攻撃が得意、耐久力がない";

	Canvas canvas = new Canvas(50, 50);
	GraphicsContext g;

	public Witch() {
		super(1.0F, 0.9F, 1.1F, 1.0F, 0.9F, 1.1F);
	}


	private void draw(Paint color) {

		g = canvas.getGraphicsContext2D();

		g.beginPath();

		g.appendSVGPath("M 20.42 8.46 C 28.07 6.47 36.90 10.38 40.76 17.25 C 43.41 21.46 43.57 26.87 42.56 31.61 C 39.99 41.46 28.48 47.52 18.90 44.09 C 11.16 41.69 5.79 34.08 5.94 26.01 C 6.22 17.78 12.46 10.39 20.42 8.46 Z");
		g.setFill(color);
		g.fill();
		g.closePath();

		g.beginPath();
		g.appendSVGPath("M 25.93 9.01 C 30.96 9.57 35.75 12.13 38.77 16.24 C 43.40 22.04 43.41 31.99 38.13 37.40 C 36.64 34.38 35.37 31.22 33.72 28.29 C 30.69 22.61 23.74 19.37 17.71 18.04 C 21.51 20.71 25.82 21.74 29.44 24.61 C 30.25 25.21 30.78 25.99 31.03 26.93 C 29.91 29.75 26.92 31.91 24.87 34.12 C 27.89 32.85 30.09 30.73 32.26 28.35 C 34.49 31.40 35.78 34.59 36.87 38.19 C 31.26 37.78 24.80 36.23 20.35 32.63 C 16.39 28.44 13.88 22.53 11.94 17.17 C 13.69 17.38 15.44 17.61 17.20 17.81 C 15.36 16.83 13.51 15.87 11.66 14.91 C 15.37 11.00 20.47 8.55 25.93 9.01 Z");
		g.setFill(Paint.valueOf("#f4f4f4"));
		g.fill();
		g.closePath();

		g.beginPath();
		g.appendSVGPath("M 25.22 22.99 C 23.41 25.35 21.34 27.39 19.00 29.23 C 20.61 25.88 21.83 24.57 25.22 22.99 Z");
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