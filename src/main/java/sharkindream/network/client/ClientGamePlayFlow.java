package sharkindream.network.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import sharkindream.gui.gamescreen.GameScreenController;
import sharkindream.myprofile.Profile;
import sharkindream.network.stream.playerstream.CharacterStatusStream;
import sharkindream.network.stream.playerstream.Guest;
import sharkindream.network.stream.playerstream.GuestStream;

public class ClientGamePlayFlow {

	GameScreenController gamescreencontroller;
	private boolean isrecruit;
	private boolean isplaying;

	private Socket cSocket;
	private GuestStream guestinfo;
	private CharacterStatusStream playerinfo;

	ClientGamePlayFlow(Socket csocket){
		this.cSocket = csocket;
	}


	public void start() {
		/*****************************************
		 * 1.待機画面の準備
		 * 2.ホストがゲームを開始したら,例外を投げてそちらの処理へ
		 * 3.勝敗
		 *********************************************/


		this.lobbyManu();
		this.gameplaymanu();
		//scoremanu();
	}

	private void lobbyManu() {
		isrecruit = true;
		while(isrecruit) {
			try {
				ObjectInputStream readerGuestStatus = new ObjectInputStream(cSocket.getInputStream());

				guestinfo = (GuestStream)readerGuestStatus.readObject();
				setIsrecruit(!guestinfo.getIsAllReady());

				//gui更新
				//TODO:結合が密になっているので、イベントバスで処理したい
				gamescreencontroller.updateGuestInfo(guestinfo);

			} catch (IOException | ClassNotFoundException e) {
				//全員readyしたら、サーバーからイベントをキャッチし、例外をスローしてもらう
				e.printStackTrace();
			}
		}
	}

	public void syncPlayerName() {
		//プレイヤー名の送信
		//受け取った文字データをそのまま送信
		//正しければ終了 そうでなければもう一度
		try {

			//クライアント to サーバー
			PrintWriter writerplayername = new PrintWriter(cSocket.getOutputStream(), true);
			//サーバー to クライアント
			BufferedReader readeranswer = new BufferedReader
					(new InputStreamReader
							(cSocket.getInputStream()));

			OutputStream writersuccessflag = cSocket.getOutputStream();
			ByteChanger bc = new ByteChanger();


			String answer;

			while(true) {
				writerplayername.println(Profile.name);
				answer = readeranswer.readLine();
				if(answer.equals(Profile.name)) {
					writersuccessflag.write(bc.toStream(true));
					writersuccessflag.flush();
					break;
				}else {
					writersuccessflag.write(bc.toStream(false));
					writersuccessflag.flush();
				}
			}

		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}


	private void gameplaymanu() {
		System.out.println("client:gamestart");
		isplaying = true;
		while(isplaying) {
			try {
				ObjectInputStream readerPlayerStatus = new ObjectInputStream(cSocket.getInputStream());
				playerinfo = (CharacterStatusStream) readerPlayerStatus.readObject();

			} catch (IOException | ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}


	}

	private void setIsrecruit(boolean flag) {
		isrecruit = flag;
	}

	public void setGameController(GameScreenController controller) {
		this.gamescreencontroller = controller;
	}


	//クライアント → サーバー
	public void sendGuestInfo(Guest guest) {
		try {
			ObjectOutputStream writerGuestInfo = new ObjectOutputStream(cSocket.getOutputStream());
			writerGuestInfo.writeObject(guest);
			writerGuestInfo.flush();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
