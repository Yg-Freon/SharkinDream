package sharkindream.gamecharacter.minion;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import sharkindream.gamecharacter.GameCharacter;
import sharkindream.gamecharacter.Type;

public class Minion extends GameCharacter implements Serializable{

	private MinionClass minionclass = MinionClass.Soldier;
	private int minionID;

	public Minion() {
		super();
		super.setType(Type.Fire);
	}

	public Minion(int str, int vit, int inte, int mind, int hp, int mp) {
		super(str, vit, inte, mind, hp, mp);
		super.setType(Type.Fire);
	}

	public Minion(MinionClass classtype, Type type, int str, int vit, int inte, int mind, int hp, int mp) {
		super(type, str, vit, inte, mind, hp, mp);
		this.minionclass = classtype;
		super.setType(Type.Fire);
	}

	public boolean isequal(Minion targetminion) {
		boolean isequal = true;
		isequal = (this.minionclass == targetminion.minionclass) && isequal;
		isequal = (this.type == targetminion.type) && isequal;
		isequal = (this.HP == targetminion.HP) && isequal;
		isequal = (this.MP == targetminion.MP) && isequal;
		isequal = (this.strength == targetminion.strength) && isequal;
		isequal = (this.vitality == targetminion.vitality) && isequal;
		isequal = (this.intelligence == targetminion.intelligence) && isequal;
		isequal = (this.mind == targetminion.mind) && isequal;

		return isequal;
	}

	public Minion readMinionfromjson(int minionid) {

		Minion minion = new Minion();
		Gson gson = new GsonBuilder().serializeNulls().create();
		String PATH = "sav\\minion\\Minion" + minionid + ".json";
		try {
			String savfile = "";
			List<String> lines = Files.lines(Paths.get(PATH), StandardCharsets.UTF_8).collect(Collectors.toList());
			for(String line : lines) {
				savfile += line;
			}
			minion = gson.fromJson(savfile, Minion.class);

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			try {
				File dir = new File("sav\\minion");
				dir.mkdirs();
				File file = new File(PATH);
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
				this.minionID = minionid;
				pw.println(gson.toJson(this));
				pw.close();
			} catch (IOException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		}
		return minion;
	}

	public void saveMiniontoJson() {
		Gson gson = new Gson();
		String PATH = "sav\\minion\\Minion" + this.minionID + ".json";
		try {
			File file = new File(PATH);
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			pw.println(gson.toJson(this));
			pw.close();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}


	public MinionClass getMinionClass() {
		return minionclass;
	}

	public void setMinionClass(MinionClass minionclass_) {
		this.minionclass = minionclass_;
	}

	public Type getMinionType() {
		return this.type;
	}

	public void setMinionType(Type type) {
		this.type = type;
	}
}
