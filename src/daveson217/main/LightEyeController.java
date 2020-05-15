package daveson217.main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author David Babalola
 */
public class LightEyeController implements Initializable {
    @FXML
    private AnchorPane parentAnchorPane;        
    @FXML
    private Label timeElapsedLabel;
    
    private Timeline countdownTimeline;
    private int elapsedTimeInSeconds = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void aboutApp(ActionEvent event) {  
        // Alert
        Platform.runLater(()-> {
            Stage stage = (Stage) parentAnchorPane.getScene().getWindow();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(stage);
            alert.setTitle("LightEye v1.0 beta");            
            alert.setHeaderText("LightEye 20-20-20");
            alert.setContentText("The 20-20-20 rule states that for every 20 minutes spent looking at a screen, a person should look at something 20 feet away for 20 seconds.\n "
                    + "Protect your eyes.\nThis application reminds you to do this every 20 minutes.\n www.github.com/Daveson217");
            alert.showAndWait();
        });    
    }

    @FXML
    private void startCounting(ActionEvent event) {              
       countdownTimeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {               
                elapsedTimeInSeconds += 1;
                timeElapsedLabel.setText(formatTime(elapsedTimeInSeconds * 1000)); 
                                
                if (elapsedTimeInSeconds >= 1200) {
                    countdownTimeline.stop();
                    elapsedTimeInSeconds = 0;                    
                    timeElapsedLabel.setText("00 min: 00 secs");
                    Notifications.create().title("LightEye 20-20-20").text("Its time to look at something 20 feet away for 20 seconds!").hideAfter(new Duration(10000)).position(Pos.TOP_CENTER).showWarning();                    
                    startCounting(event); // Restart countdown
                }
            }
        }));
        countdownTimeline.setCycleCount(Timeline.INDEFINITE);
        countdownTimeline.play();
        
    }
    
    private String formatTime(int milliseconds) {        
            // This method returns the elapsed time.
            long minutes = (milliseconds / 1000) / 60;
            long seconds = (milliseconds / 1000) % 60;
            return String.format("%02d min: %02d secs", minutes, seconds);            
        }

    @FXML
    private void stopCounting(ActionEvent event) {
        countdownTimeline.stop();
        elapsedTimeInSeconds = 0;    
        timeElapsedLabel.setText("00 min: 00 secs");
    }

    @FXML
    private void pauseCounting(ActionEvent event) {
        countdownTimeline.pause();
    }
}
