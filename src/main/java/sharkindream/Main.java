package sharkindream;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {

	public static AnchorPane mainscreen;

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

			Scene scene = new Scene(root,1100,700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setMinWidth(1100);
			primaryStage.setMinHeight(700);
			primaryStage.setMaxWidth(1600);
			primaryStage.setMaxHeight(900);
			primaryStage.show();


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
