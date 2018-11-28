package sharkindream.network.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import sharkindream.network.client.ByteChanger;
import sharkindream.network.client.ClientGamePlayFlow.AttackAction;
import sharkindream.network.event.OnUpdatePlayerHandler;
import sharkindream.network.stream.playerstream.Guest;
import sharkindream.network.stream.playerstream.GuestListData;
import sharkindream.network.stream.playerstream.PlayerStatus;

public class SrvThread extends Thread{



	private Socket soc;
	private Guest guest;
	int id=-1;

	private GuestListData gstlistdata;
	private OnUpdatePlayerHandler handler;

	public SrvThread(Socket sct, GuestListData guestlistdata) {
		soc = sct;
		this.gstlistdata = guestlistdata;
		System.out.println("Connect to :" + soc.getInetAddress());
	}

	@Override
	public void run() {
		try {
			this.getGuestName();
			this.receiveGuestInfo();
			
			//ゲームスタート-------------------------------------------------
			while(true) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}

		} catch (IOException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			gstlistdata.removeGuestByID(id);
		}


	}

	private void getGuestName() throws IOException {

		 {
				guest = new Guest();
				//名前をもらう
				BufferedReader readerguestname = new BufferedReader
						(new InputStreamReader
								(soc.getInputStream()));



				PrintWriter writerguestname = new PrintWriter(soc.getOutputStream(), true);
				//サーバー to クライアント


				InputStream readersuccessflag = soc.getInputStream();
				ByteChanger bc = new ByteChanger();


				while(true) {
					//System.out.println("server:名前を取得中");
					guest.setName(readerguestname.readLine());

					writerguestname.println(guest.getName());

					boolean flag = bc.toBool(readersuccessflag.read());

					if(flag) {
						//ゲストにIDを持たせる
						gstlistdata.addGuest(guest);
						id = guest.playerID;
						System.out.println("welcome!" + guest.getName() +" your ID:" + guest.playerID);
						break;
					}
				}
		 }

	}


	public void sendGuestStatus(GuestListData guestlistdata) {


		try {
			ObjectOutputStream writerGuestStatus = new ObjectOutputStream(soc.getOutputStream());
			writerGuestStatus.writeObject(guestlistdata);
			writerGuestStatus.flush();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}


	public void sendPlayeresStatus(Map<Guest, PlayerStatus> playerStatuslist) {

		try {
			ObjectOutputStream writerGuestStatus = new ObjectOutputStream(soc.getOutputStream());


			Map<Integer, PlayerStatus> clientplayerlist = new HashMap<>();
			for(Map.Entry<Guest, PlayerStatus> plist : playerStatuslist.entrySet()) {
				clientplayerlist.put(plist.getKey().playerID, plist.getValue());
			}

			writerGuestStatus.writeObject(clientplayerlist);
			writerGuestStatus.flush();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	public void sendCanAttack(Guest id, PlayerStatus player) {
		try {
			ObjectOutputStream writerGuestStatus = new ObjectOutputStream(soc.getOutputStream());
			writerGuestStatus.writeObject(id.isequal(guest));
			writerGuestStatus.flush();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		if(id.isequal(guest)) {
			AttackAction actionResult = AttackAction.None;

			try {

				ObjectInputStream readerPlayerStatus = new ObjectInputStream(soc.getInputStream());
				actionResult = (AttackAction) readerPlayerStatus.readObject();

			} catch (IOException | ClassNotFoundException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}


			switch(actionResult) {
			case Attack:
				try {
					ObjectInputStream readerActionCard = new ObjectInputStream(soc.getInputStream());
					int actionCardid = (int) readerActionCard.readObject();

					ObjectInputStream readerAttackingMinion = new ObjectInputStream(soc.getInputStream());
					int attackingMinion = (int) readerAttackingMinion.readObject();

					ObjectInputStream readerAttackedPlayer = new ObjectInputStream(soc.getInputStream());
					Guest attackedplayer = (Guest) readerAttackedPlayer.readObject();

					onAttackerActioned(actionResult);



				} catch (ClassNotFoundException | IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				break;
			case Draw:
				onAttackerActioned(actionResult);

				break;
			case Rest:
				onAttackerActioned(actionResult);
				break;
			default:
				break;
			}
		}

	}



	//クライアント -> サーバー

	public void receiveGuestInfo() throws ClassNotFoundException, IOException {
		while(true) {
			ObjectInputStream readerGuestInfo = new ObjectInputStream(soc.getInputStream());

			Guest guest_= (Guest)readerGuestInfo.readObject();
			guest_.playerID = guest.playerID;
			guest_.setName(guest.getName());

			guest = guest_;

			onReceiveGuestInfo();
			if(!GameFlow.getIsrecruit()) {
				break;
			}

		}
	}

	private void onReceiveGuestInfo() {
		gstlistdata.updateGuestByID(guest);

	}

	//リスナ登録
	public void addUpdatePlayerHandler(OnUpdatePlayerHandler handler) {
		this.handler = handler;
	}

	private void onAttackerActioned(AttackAction actionResult) {
		if(handler != null) {
			handler.onAttackerActioned(actionResult);
		}
	}

	private void onActionAttack(int attackCard, int attackMinion, Guest target) {
		if(handler != null) {
			handler.onActionAttack(attackCard, attackMinion, target);
		}
	}




}
