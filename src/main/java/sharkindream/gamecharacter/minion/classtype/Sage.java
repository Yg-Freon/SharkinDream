package sharkindream.gamecharacter.minion.classtype;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Sage extends Prototype{

	//物理攻撃↓ 物理防御- 魔法攻撃↑ 魔法防御↑ HP- MP-

	public final static String name = "Sage";
	public final static String comment = "魔法攻撃が得意で、魔法攻撃に強い";

	Canvas canvas = new Canvas(50, 50);
	GraphicsContext g;

	public Sage() {
		super(0.9F, 0.9F, 1.1F	, 1.1F, 1.0F, 1.0F);
	}

	private void draw(Paint color) {

		g = canvas.getGraphicsContext2D();

		g.beginPath();

		g.appendSVGPath("M 7.89 6.15 C 9.01 8.32 9.96 10.57 10.85 12.85 C 10.36 15.32 10.14 17.77 10.60 20.27 C 11.96 27.64 11.73 34.38 16.15 40.82 C 15.04 32.52 13.17 24.29 11.35 16.12 C 11.74 13.08 11.99 10.07 12.07 7.00 L 12.86 7.00 C 13.21 10.93 13.71 14.83 14.38 18.72 C 15.69 14.95 16.17 11.28 16.28 7.29 L 18.21 7.33 C 17.80 11.11 16.82 14.71 15.69 18.33 C 16.46 17.75 17.23 17.16 18.00 16.58 C 18.00 15.35 18.00 14.12 18.00 12.88 C 18.54 13.28 19.07 13.67 19.60 14.06 C 21.64 11.53 23.66 8.99 25.71 6.47 C 26.05 8.94 26.40 11.41 26.79 13.88 C 30.72 11.99 34.67 10.17 38.73 8.59 C 39.04 18.40 40.81 28.07 41.13 37.86 C 34.90 40.72 28.74 43.77 22.56 46.75 C 20.85 46.41 19.14 46.07 17.42 45.75 C 14.79 40.49 11.76 36.07 11.08 30.05 C 10.16 22.07 8.34 14.16 7.89 6.15 Z");
		g.setFill(color);
		g.fill();
		g.closePath();

		g.beginPath();
		g.appendSVGPath("M 24.75 8.94 C 25.19 10.64 25.64 12.33 26.09 14.02 C 23.35 15.35 20.60 16.68 17.86 18.03 C 19.61 15.74 21.37 13.47 23.10 11.17 C 23.65 10.43 24.20 9.69 24.75 8.94 Z");
		g.setFill(Paint.valueOf("#f4f4f4"));
		g.fill();
		g.closePath();

		g.beginPath();
		g.appendSVGPath("M 37.84 9.85 C 38.20 18.94 39.67 27.99 40.22 37.07 C 34.58 39.84 28.94 42.58 23.28 45.31 C 21.71 36.38 19.98 27.45 18.83 18.46 C 25.20 15.64 31.53 12.76 37.84 9.85 Z");
		g.setFill(Paint.valueOf("#f4f4f4"));
		g.fill();
		g.closePath();

		g.beginPath();
		g.appendSVGPath("M 12.98 19.04 C 14.58 19.00 16.17 18.94 17.77 18.89 C 19.17 27.84 20.81 36.76 22.29 45.69 C 20.93 45.39 19.56 45.10 18.19 44.82 C 16.48 36.22 14.91 27.59 12.98 19.04 Z");
		g.setFill(Paint.valueOf("#f4f4f4"));
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