package sharkindream.network.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import sharkindream.network.client.ByteChanger;
import sharkindream.network.stream.playerstream.Guest;
import sharkindream.network.stream.playerstream.GuestStream;
import sharkindream.network.stream.playerstream.PlayerStatus;

public class SrvThread extends Thread{



	private Socket soc;
	private Guest guest;
	int id=-1;

	private GuestStream gst;

	public SrvThread(Socket sct, GuestStream guestst) {
		soc = sct;
		gst = guestst;
		System.out.println("Connect to :" + soc.getInetAddress());
	}

	@Override
	public void run() {
		try {
			this.getGuestName();
			this.receiveGuestInfo();

			//gamestart
			//初期化
			//ゲームスタート



		} catch (IOException | ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			System.out.println(id);
			gst.removeGuestByID(id);
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
						gst.addGuest(guest);
						id = guest.playerID;
						System.out.println("welcome!" + guest.getName() +" your ID:" + guest.playerID);
						break;
					}
				}
		 }

	}


	public void sendGuestStatus(GuestStream guestst) {


		try {
			ObjectOutputStream writerGuestStatus = new ObjectOutputStream(soc.getOutputStream());
			writerGuestStatus.writeObject(guestst);
			writerGuestStatus.flush();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}


	public void sendPlayeresStatus(List<PlayerStatus> playerlist) {

		try {
			ObjectOutputStream writerGuestStatus = new ObjectOutputStream(soc.getOutputStream());
			writerGuestStatus.writeObject(playerlist);
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




		}
	}

	private void onReceiveGuestInfo() {
		gst.updateGuestByID(guest.playerID, guest);

	}



}
