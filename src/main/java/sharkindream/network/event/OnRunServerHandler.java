package sharkindream.network.event;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import sharkindream.config.Setting;
import sharkindream.gui.event.OnMoveScreenHandler;
import sharkindream.network.client.Client;

public class OnRunServerHandler implements OnRunServerIF{

	private ExecutorService service = Executors.newSingleThreadExecutor();

	@Override
	public void onRunServer(int port) {
		if(Setting.isRunClient) {
			Client client = new Client();
			OnConnectedServerHandler handle = new OnConnectedServerHandler() ;
			OnMoveScreenHandler moveScreenHandler = new OnMoveScreenHandler();
			client.addConnecedListener(handle);
			client.addMoveScreenListener(moveScreenHandler);
			client.setPort(port);
			service.submit(client);

		}
	}
}
