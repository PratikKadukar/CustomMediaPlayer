package application;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaView;

//simple media player using javaFX with the help of sceneBuilder..

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
//			
//			BorderPane root= new BorderPane();
//			Scene scene=new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
			//
			Parent root=FXMLLoader.load(getClass().getResource("CustomMediaPlayer.fxml"));
			Scene scene=new Scene(root);
			
			primaryStage.setTitle("PK's-Custom-Media-Player");
			
			//double click for full screen mode.
			
			{	scene.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					
					if (arg0.getClickCount()==2) {
						primaryStage.setFullScreen(true);
					}
					
				}
			});
			
			}
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
