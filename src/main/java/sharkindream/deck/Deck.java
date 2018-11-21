package sharkindream.deck;

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

import sharkindream.actioncard.ActionCard;

public class Deck implements Serializable{
	/*デッキID*/
	private int deckID = 0;
	public final int maxcardnum = 4;

	public ActionCard[] cardlist = new ActionCard[maxcardnum];

	public Deck() {
		for(int i = 0; i < maxcardnum; ++i) {
			cardlist[i] = new ActionCard();
		}
	}

	public Deck readDeckfromjson(int deckid) {

		Deck deck = new Deck();
		Gson gson = new GsonBuilder().serializeNulls().create();
		String PATH = "sav\\deck\\Deck" + deckid + ".json";
		try {
			String savfile = "";
			List<String> lines = Files.lines(Paths.get(PATH), StandardCharsets.UTF_8).collect(Collectors.toList());
			for(String line : lines) {
				savfile += line;
			}
			deck = gson.fromJson(savfile, Deck.class);

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			try {
				File dir = new File("sav\\deck");
				dir.mkdirs();
				File file = new File(PATH);
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
				this.deckID = deckid;
				pw.println(gson.toJson(this));
				pw.close();
			} catch (IOException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
		}

		return deck;

	}

	public void saveDecktojason() {

		Gson gson = new Gson();
		String PATH = "sav\\deck\\Deck" + this.deckID + ".json";
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

	public int getDeckID() {
		return this.deckID;
	}
}
