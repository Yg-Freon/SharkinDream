package sharkindream.gamecharacter.classtype;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Paint;
import sharkindream.gamecharacter.GameCharacter.CharacterClass;

public class Player extends Prototype{

	public final static String name = "Player";
	public final static String comment = "操作主、つまりあなた";

	public Player() {
		super(1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f);
	}


	public CharacterClass getPlayerCharacterClass() {
		return CharacterClass.Player;
	}


	@Override
	public String getname() {
		// TODO 自動生成されたメソッド・スタブ
		return this.name;
	}


	@Override
	public Canvas geticon(Paint color) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}


	@Override
	public Canvas geticon(Paint color, Paint backcolor) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}


	@Override
	public String getcomment() {
		// TODO 自動生成されたメソッド・スタブ
		return this.comment;
	}

}
