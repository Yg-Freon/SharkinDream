package sharkindream.gamecharacter.minion.classtype;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Soldier extends Prototype{

	//物理攻撃↑ 物理防御↑ 魔法攻撃- 魔法防御↓ HP- MP↓

	private final static String name = "Soldier";
	private final static String comment = "物理攻撃が得意、魔法耐性が低い";

	Canvas canvas = new Canvas(50, 50);
	GraphicsContext g;


	public Soldier() {
		super(1.1F, 1.1F, 1.0F	, 0.9F, 1.0F, 0.9F);
	}

	private void draw(Paint color) {

		g = canvas.getGraphicsContext2D();

		g.beginPath();

		g.appendSVGPath("M 27.11 3.03 L 28.67 3.29 C 28.51 6.60 28.24 9.87 28.51 13.18 C 27.89 12.41 27.27 11.63 26.66 10.86 C 25.81 11.16 24.95 11.45 24.09 11.73 C 24.96 8.84 25.69 5.69 27.11 3.03 Z");
		g.setFill(color);
		g.fill();
		g.closePath();

		g.beginPath();
		g.appendSVGPath("M 18.73 11.85 C 21.66 11.74 23.54 13.32 26.08 14.31 C 29.23 13.95 31.51 12.77 34.49 14.68 C 32.43 14.98 30.35 15.17 28.27 15.32 C 27.69 18.81 27.19 22.24 26.02 25.59 C 24.93 22.63 25.44 19.83 25.67 16.80 C 24.80 18.79 23.44 20.46 24.20 22.74 C 25.02 25.52 25.93 28.18 26.09 31.10 C 24.92 29.41 23.86 27.67 22.77 25.92 C 23.64 29.32 24.66 32.64 25.07 36.13 C 23.89 34.43 22.81 32.67 21.72 30.91 C 22.30 34.12 24.47 38.10 23.31 41.28 C 22.72 43.66 21.46 45.66 19.98 47.58 C 18.16 44.26 19.02 39.35 19.44 35.71 C 20.41 37.12 21.36 38.55 22.32 39.96 C 21.50 36.91 19.71 33.64 20.44 30.49 C 21.38 25.10 22.97 19.88 23.63 14.43 C 21.97 13.62 20.33 12.77 18.73 11.85 Z");
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
