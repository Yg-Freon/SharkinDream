package sharkindream.gamecharacter.minion.classtype;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class MadScientist extends Prototype{

	//物理攻撃↓ 物理防御↓ 魔法攻撃↓ 魔法防御↑ HP↓ MP↑↑
	//状態異常の確率up

	public final static String name = "MadScientist";
	public final static String comment = "状態異常にかける確立が高い";

	Canvas canvas = new Canvas(50, 50);
	GraphicsContext g;

	public MadScientist() {
		super(0.9F, 0.9F, 0.9F, 1.1F, 0.9F, 1.2F);
	}

	private void draw(Paint color) {

		g = canvas.getGraphicsContext2D();

		g.beginPath();

		g.appendSVGPath("M 18.15 7.99 L 18.80 8.01 C 18.78 14.02 19.70 19.44 16.66 24.95 C 21.45 25.96 28.56 27.34 32.78 24.23 C 34.93 31.28 38.06 38.09 40.86 44.90 C 29.73 46.78 18.81 47.62 7.84 44.29 C 11.04 37.19 13.90 29.94 16.92 22.77 C 19.00 18.47 18.08 12.68 18.15 7.99 Z");
		g.setFill(color);
		g.fill();
		g.closePath();

		g.beginPath();
		g.appendSVGPath("M 30.45 7.43 C 31.12 13.17 30.30 18.74 31.87 24.45 C 29.24 19.53 30.14 12.81 30.45 7.43 Z");
		g.setFill(color);
		g.fill();
		g.closePath();


		g.beginPath();
		g.appendSVGPath("M 29.94 34.23 C 31.36 38.64 25.10 40.04 22.00 39.10 C 18.72 38.22 17.40 36.30 19.01 33.11 C 22.63 31.29 27.16 30.65 29.94 34.23 Z");
		g.setFill(Paint.valueOf("#f4f4f4"));
		g.fill();
		g.closePath();


		g.beginPath();
		g.appendSVGPath("M 21.65 34.22 L 22.23 34.20 L 22.24 34.78 L 21.66 34.80 L 21.65 34.22 Z");
		g.setFill(color);
		g.fill();
		g.closePath();


		g.beginPath();
		g.appendSVGPath("M 27.97 33.73 C 27.11 34.35 26.25 34.96 25.38 35.57 C 26.40 36.06 27.41 36.57 28.42 37.10 C 27.05 36.81 25.69 36.39 24.30 36.18 C 22.95 36.73 21.68 37.48 20.40 38.14 C 21.40 37.19 22.40 36.24 23.44 35.34 C 24.93 34.75 26.46 34.24 27.97 33.73 Z");
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
