/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daveson217.main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author David Babalola
 */
public class LightEyeLauncher extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {        
        Platform.setImplicitExit(false);        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/resources/LightEye.fxml"));
        Parent root = loader.load();                 
        Scene scene = new Scene(root);  
        //primaryStage.initStyle(StageStyle.DECORATED);  
               
        primaryStage.setOnCloseRequest( e ->  {
            e.consume();            
            primaryStage.close();
            System.exit(0);
        } );
       primaryStage.setResizable(false);
       primaryStage.setScene(scene);
       primaryStage.setTitle("LightEye v1.0 beta");
       primaryStage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {       
        launch(args);
    }
    
}
