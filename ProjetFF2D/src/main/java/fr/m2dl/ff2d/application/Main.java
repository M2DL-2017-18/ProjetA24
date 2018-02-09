package fr.m2dl.ff2d.application;

import fr.m2dl.ff2d.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/main.fxml"));

            VBox view = (VBox) loader.load();
            Controller controller = loader.getController();
   
            Scene scene = new Scene(view);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Ant Simulation");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {

		launch(args);
		
		
	}
	
}
