package application;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

public class CustomMediaPlayerController implements Initializable  {
	
	private String path ;
	private  MediaPlayer mediaPlayer;
	
	
	@FXML
	private MediaView mediaView;
	
	@FXML
	private Slider progressBar;
	
	@FXML
	private Slider volumeController;
	
	public void selectFileMethod(ActionEvent event) {
		
		//code for selecting particular file to play
		
		FileChooser fileChooser= new FileChooser();
		File file = fileChooser.showOpenDialog(null);	
		path = file.toURI().toString();
		System.out.println(path);
		
		if (path != null) {
			
			Media media = new Media(path);
			
			mediaPlayer = new MediaPlayer(media);
			
			mediaView.setMediaPlayer(mediaPlayer);
			
			DoubleProperty widthproperty = mediaView.fitWidthProperty();
			DoubleProperty heightproperty = mediaView.fitHeightProperty();
			
			widthproperty.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
			heightproperty.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
			
			//showing video progressbar
			
			mediaPlayer.currentTimeProperty().addListener(new ChangeListener<javafx.util.Duration>() {

				@Override
				public void changed(ObservableValue<? extends javafx.util.Duration> arg0, javafx.util.Duration arg1,
						javafx.util.Duration arg2) {
					
					progressBar.setValue(arg2.toSeconds());
				}
				
			});
			
			//controlling progress Bar by mouse click
			
			progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					
					mediaPlayer.seek(javafx.util.Duration.seconds(progressBar.getValue()));
					
				}
			});
			
			//controlling progressbar by mouse dragg
			
			progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
										
					mediaPlayer.seek(javafx.util.Duration.seconds(progressBar.getValue()));
					
				}
			});
			
			//actual length of progressbar = actual length of video
			
			mediaPlayer.setOnReady(new Runnable() {
				
				@Override
				public void run() {
					javafx.util.Duration totalDuration= media.getDuration();
					progressBar.setMax(totalDuration.toSeconds());
				}
			});
			
			//controlling video volume by volume controller slider
			
			volumeController.setValue(mediaPlayer.getVolume()*100);
			volumeController.valueProperty().addListener(new InvalidationListener() {
				
				@Override
				public void invalidated(Observable arg0) {
					mediaPlayer.setVolume(volumeController.getValue()/100);
				}
			});
			
			
			
			mediaPlayer.play();
			
		}
	}
	
	////code for different buttons with different functionality like play, pause,stop, slowvodeo,fastforward, skip+10 sec, skip-10sec
	
	public void play(ActionEvent event) {
		mediaPlayer.play();
		mediaPlayer.setRate(1);
	}
	
	public void pause(ActionEvent event) {
		mediaPlayer.pause();
	}
	
	public void stop(ActionEvent event) {
		mediaPlayer.stop();
	}
	
	public void slowRate(ActionEvent event) {
		mediaPlayer.setRate(0.5);
	}
	
	public void fastForward(ActionEvent event) {
		mediaPlayer.setRate(2);
	}
	
	public void skip10Sec(ActionEvent event) {
		mediaPlayer.seek(mediaPlayer.getCurrentTime().add(javafx.util.Duration.seconds(10)));
	}
	
	public void back10Sec(ActionEvent event) {
		mediaPlayer.seek(mediaPlayer.getCurrentTime().add(javafx.util.Duration.seconds(-10)));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
