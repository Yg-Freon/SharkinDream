package sharkindream.network.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

import sharkindream.gui.gamescreen.GameScreenController;
import sharkindream.myprofile.Profile;
import sharkindream.network.stream.playerstream.Guest;
import sharkindream.network.stream.playerstream.GuestListData;
import sharkindream.network.stream.playerstream.PlayerStatus;

public class ClientGamePlayFlow {

	GameScreenController gamescreencontroller;
	private boolean isrecruit;
	private boolean isplaying;

	private Socket cSocket;
	private GuestListData guestinfo;
	private Map<Integer, PlayerStatus> playerinfo;

	public enum AttackAction{
		None,
		Attack,
		Draw,
		Rest
	}

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

				guestinfo = (GuestListData)readerGuestStatus.readObject();
				setIsrecruit(!guestinfo.getIsAllReady());

				//gui更新
				//TODO:結合が密になっているので、イベントバスで処理したい
				if(gamescreencontroller != null) {
					gamescreencontroller.updateGuestInfo(guestinfo);
				}

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

		//初期化
		try {
			ObjectInputStream readerPlayerStatus = new ObjectInputStream(cSocket.getInputStream());
			playerinfo = (Map<Integer, PlayerStatus>) readerPlayerStatus.readObject();
			if(gamescreencontroller != null) {
				gamescreencontroller.initPlayerInfo(playerinfo);
				gamescreencontroller.startGameAnimation();
				while(!gamescreencontroller.isanimationfinished()) {
					//アニメーションが終了するまで待機
					Thread.sleep(1);
				}
			}
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			//TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		//画面更新

		isplaying = true;
		while(isplaying) {

			//自分の攻撃ターンか確認
			boolean canAttack = false;
			try {

				ObjectInputStream readerPlayerStatus = new ObjectInputStream(cSocket.getInputStream());
				canAttack = (boolean) readerPlayerStatus.readObject();

			} catch (IOException | ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			//true なら攻撃 手札補充
			if(canAttack) {
				//攻撃
				try {
					if(gamescreencontroller != null) {
						gamescreencontroller.onAttackTurn();
					}

					ObjectOutputStream writerActionResult = new ObjectOutputStream(cSocket.getOutputStream());
					writerActionResult.writeObject(getActionResult());
					writerActionResult.flush();

					switch(getActionResult()) {
					case Attack:
						ObjectOutputStream writerActionCard = new ObjectOutputStream(cSocket.getOutputStream());
						writerActionCard.writeObject(getActionCard());
						writerActionCard.flush();

						ObjectOutputStream writerAttackingMinion = new ObjectOutputStream(cSocket.getOutputStream());
						writerAttackingMinion.writeObject(getAttackingMinion());
						writerAttackingMinion.flush();

						ObjectOutputStream writerAttackedPlayer = new ObjectOutputStream(cSocket.getOutputStream());
						writerAttackedPlayer.writeObject(getAttackedPlayer());
						writerAttackedPlayer.flush();
						break;
					case Draw:
						break;
					case Rest:
						break;
					default:
						break;
					}

				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}


			//自分の防御ターンか確認
				//trueなら防御
			//ターン終了

		}
	}

	public void onAttackAction() {

	}

	private AttackAction getActionResult() {
		long t0 = System.currentTimeMillis();
		long time = 0;

		while(time < 30000) {
			time = System.currentTimeMillis() - t0;

			if(gamescreencontroller != null) {
				if(gamescreencontroller.getAttackActoin() != AttackAction.None) {
					return gamescreencontroller.getAttackActoin();
				}
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
		//パス処理
		return AttackAction.Rest;
	}


	private int getActionCard() {
		int card = -1;
		if(gamescreencontroller != null) {
			while(card == -1) {
				card = gamescreencontroller.getActionCard();
				if(card != -1) return card;
			}
		}
		return card;

	}

	private int getAttackingMinion() {
		int minion = 0;
		if(gamescreencontroller != null) {
			minion = gamescreencontroller.getattakingminion();
		}
		return minion;
	}

	private Guest getAttackedPlayer() {
		Guest attackedplayer = new Guest();
		if(gamescreencontroller != null) {
			if(gamescreencontroller.getAttackedPlayer() != null) {
				attackedplayer = gamescreencontroller.getAttackedPlayer();
			}
		}
		return attackedplayer;
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
