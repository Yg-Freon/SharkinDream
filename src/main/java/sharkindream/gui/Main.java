package sharkindream.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sharkindream.gui.event.OnMoveScreenHandler;
import sharkindream.gui.title.TitleController;


public class Main extends Application {

	public static AnchorPane mainscreen;

	/*
	public static FXMLLoader gamescene;
	public static FXMLLoader mainmanuscene;
	public static AnchorPane gameinfoscene;
	*/

	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setOnCloseRequest((WindowEvent e) ->{
				//e.consume();
				System.out.println("終了します");
				System.exit(0);
			});
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Form.fxml"));

			mainscreen = (AnchorPane) root.getChildren().get(0);


			//---------------------------------------
			FXMLLoader titlefxml =new FXMLLoader(getClass().getResource("/sharkindream/gui/title/Title.fxml"));
			AnchorPane title = (AnchorPane)titlefxml.load();
			((TitleController)titlefxml.getController()).addMoveScreenListener(new OnMoveScreenHandler());
			((TitleController)titlefxml.getController()).readyanimation();

			switchMainScreen(title);
			//----------------------------------------------


			Scene scene = new Scene(root,1100,700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setMinWidth(1100);
			primaryStage.setMinHeight(700);
			primaryStage.setMaxWidth(1600);
			primaryStage.setMaxHeight(900);
			primaryStage.show();

			//-------------------------------

			/*
			gamescene = new FXMLLoader(getClass().getResource("/sharkindream/gui/gamescreen/GameScreen.fxml"));
			mainmanuscene = new FXMLLoader(getClass().getResource("mainmanu/MainManu.fxml"));

			gameinfoscene = (AnchorPane)FXMLLoader.load(getClass().getResource("/sharkindream/gui/gamescreen/infomation/lobby/selectplayertype/LobbyScreen.fxml"));
			*/



		} catch(Exception e) {
			e.printStackTrace();
		}
	}



	public static void main(String[] args) {
		launch(args);
	}


	public static void switchMainScreen(AnchorPane screen) {

		Platform.runLater( () -> {
			mainscreen.getChildren().clear();

			mainscreen.getChildren().add(screen);
			AnchorPane.setTopAnchor(screen, 0d);
			AnchorPane.setBottomAnchor(screen, 0d);
			AnchorPane.setLeftAnchor(screen, 0d);
			AnchorPane.setRightAnchor(screen, 0d);
		});


	}


}
