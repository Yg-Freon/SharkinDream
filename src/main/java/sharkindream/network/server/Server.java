package sharkindream.network.server;

import java.io.IOException;
import java.net.ServerSocket;

import javafx.concurrent.Task;
import sharkindream.network.event.OnRunServerHandler;
import sharkindream.network.event.OnUpdateGuestHandler;
import sharkindream.network.event.OnUpdatePlayerHandler;

public class Server extends Task<Object>{

	private OnRunServerHandler listner;
	private int port = 8888;

	static ServerSocket sSocket = null;
	static ServerSocket sSocket_ = null;

	@Override
	protected Object call() throws Exception {
		runServer();
		return null;
	}


	public void runServer() {

		try {
			sSocket = new ServerSocket(port);
			System.out.println("入力待ち");
			//受入準備完了イベント発火
			this.onRunServer();

			GameFlow gameflow = new GameFlow();

			GameFlow.setUpdateHandler(new OnUpdateGuestHandler());
			gameflow.addUpdatePlayerHandler(new OnUpdatePlayerHandler());

			//ロビーメニュー
			gameflow.onlobbymenu(sSocket);

			//ゲーム開始
			//ステータス割り振り
			gameflow.gameInit();
			//手札配布
			gameflow.inithand();

			//ゲームスタート
			boolean roopflag = true;
			while(roopflag) {
				roopflag = gameflow.playGame();
			}

			//終わり

		}catch(Exception e) {
			e.printStackTrace();




		}finally {
			try {
				if(sSocket!=null) {
					sSocket.close();
				}

				System.out.println("サーバー側終了です");
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}


	public boolean setPort(CharSequence prt) {

		boolean success = false;
		label : try {
			if(prt.length() == 0) break label;
			port = Integer.parseInt(prt.toString());
			success = true;

		}catch(Exception e){

		}

		return success;
	}

	//リスナ登録
	public void addRunServerListner(OnRunServerHandler handler) {
		this.listner = handler;
	}

	public void removeRunServerListener() {
		this.listner = null;
	}

	public void onRunServer() {
		if(listner != null) {
			listner.onRunServer(port);
		}
	}

}
