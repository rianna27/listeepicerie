package application;
	

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class Main extends Application {
	private Stage primaryStage1;
	@Override
	public void start(Stage primaryStage) 
	{
	   this.primaryStage1=primaryStage;
	   mainWindow();
	} 
	
	
	//pour charger la fenetre principale
	public void mainWindow()
	{
		
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("Sample.fxml"));
			AnchorPane pane=loader.load();
			Scene scene = new Scene(pane);
			SampleController sampleController=loader.getController();
			sampleController.setMain(this);
			primaryStage1.setScene(scene);
			primaryStage1.setTitle("Liste d'épicerie");
			primaryStage1.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// pour charger la fenetre secondaire
	public void secondWindow()
	{
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("secondWindow.fxml"));
			AnchorPane pane=loader.load();
			Scene scene = new Scene(pane);
			secondWindowController sampleWindowController=loader.getController();
			sampleWindowController.setMain(this);
			primaryStage1.setScene(scene);
			primaryStage1.setTitle("About");
			primaryStage1.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
	
	

	

  
 

	