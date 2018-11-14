package sharkindream.network.client;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javafx.concurrent.Task;
import javafx.scene.layout.AnchorPane;
import sharkindream.gui.event.OnMoveScreenHandler;
import sharkindream.network.event.OnConnectedServerHandler;

public class Client extends Task<Void>{

	private static AnchorPane gameinfolobbyscreen;
	private OnConnectedServerHandler listener = null;
	private OnMoveScreenHandler movelistener = null;
	public static ClientGamePlayFlow cflow;

	private String ipaddress = "127.0.0.1";
	private int port = 8888;




	@Override
	protected Void call() throws Exception{
		connectServer();

		return null;

	}

	public void connectServer() {

	Socket cSocket = null;
	BufferedReader csInput = null;
	DataOutputStream writer = null;
	BufferedReader reader = null;

	label : try{
		//IPアドレスとポート番号を指定してクライアント側のソケットを作成
		cSocket = new Socket(ipaddress, port);


		System.out.println("connecting...");

		if(cSocket.isConnected()) {
			System.out.println("connected");

			cflow = new ClientGamePlayFlow(cSocket);

			this.OpenClientScreen(cflow);

			//名前の同期
			cflow.syncPlayerName();
			//接続完了イベント発火
			this.onConnectedServer();

			//現在選択しているデッキ等の送信
			cflow.start();



		}else {
			System.out.println("faled");
			break label;
		}


		}catch(Exception e){
			e.printStackTrace();
			System.out.println("faled");
		}finally{
			try {
				if (reader != null) {
					reader.close();
				}
				if (writer != null) {
					writer.close();
				}
				if (csInput != null) {
					csInput.close();
				}
				if (cSocket != null) {
					cSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		    System.out.println("クライアント側終了です");
		}
	}


	public boolean setAddress(CharSequence ipport) {

		boolean success = false;

		label : try {
			if(ipport.length() == 0) break label;

			int colon = ipport.toString().indexOf(":");
			if(colon == -1) {

				ipaddress = ipport.toString();
			}else {
				ipaddress = ipport.toString().substring(0, colon);
				port = Integer.parseInt(ipport.toString().substring(colon+1));
			}
			success = true;

		}catch(Exception e){
			e.printStackTrace();
		}

		return success;
	}

	public void setPort(int prt) {
		this.port = prt;
	}

	public int getPort() {
		return this.port;
	}

	public String getIpaddress() {
		return this.ipaddress;
	}



	public static void switchGameInfoLobbymanu(AnchorPane lobbyscreen) {
		((AnchorPane)gameinfolobbyscreen.getChildren().get(1)).getChildren().clear();
		((AnchorPane)gameinfolobbyscreen.getChildren().get(1)).getChildren().add(lobbyscreen);

		AnchorPane.setTopAnchor(lobbyscreen, 0d);
		AnchorPane.setBottomAnchor(lobbyscreen, 0d);
		AnchorPane.setLeftAnchor(lobbyscreen, 0d);
		AnchorPane.setRightAnchor(lobbyscreen, 0d);
	}



	//リスナ登録
	public void addConnecedListener(OnConnectedServerHandler handler) {
		this.listener = handler;
	}

	public void removeConnectedListener() {
		this.listener = null;
	}

	public void onConnectedServer() {
		if(listener != null) {
			listener.onConnectedServer();
		}
	}


	//リスナ登録
	public void addMoveScreenListener(OnMoveScreenHandler handler) {
		this.movelistener = handler;
	}

	public void removeMoveScreenListener() {
		this.movelistener = null;
	}

	public void OpenClientScreen(ClientGamePlayFlow cflow) {
		if(movelistener != null) {
			movelistener.onMoveClientGameScreen(cflow);
		}
	}





}
