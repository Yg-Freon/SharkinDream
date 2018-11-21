package sharkindream.gui.mainmenu.playmanu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sharkindream.network.client.Client;
import sharkindream.network.event.OnConnectedServerHandler;
import sharkindream.network.event.OnRunServerHandler;
import sharkindream.network.server.Server;


public class SubManuPlayController{

	Client client = null;

	@FXML
	public TextField serverport;
	@FXML
	public TextField serveraddress;

	private ExecutorService service = Executors.newSingleThreadExecutor();


	@FXML
	public void onBuild() {

		OnRunServerHandler handle = new OnRunServerHandler();

		Server server = new Server();
		if(server.setPort(serverport.getCharacters())) {

			server.addRunServerListner(handle);
			service.submit(server);
		}else {
			System.out.println("正しい値を入力してください");
		}
	}

	@FXML
	public void onJoin() {
		OnConnectedServerHandler handle = new OnConnectedServerHandler() ;

		Client client = new Client();
		if(client.setAddress(serveraddress.getCharacters())) {
			client.addConnecedListener(handle);
			service.submit(client);

		}else{
			System.out.println("正しい値を入力してください");
		}
	}

}
