package GUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Console;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.StageStyle;
import javafx.scene.paint.Color;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 *
 * @author wajihbenhmida
 */
public class sponsorMain extends Application {
   // private double xoff = 0;
   // private double yoff = 0;
    
    
    @Override
    public void start(Stage primaryStage) throws IOException{
       
            Parent parent = FXMLLoader.load(getClass().getResource("listeSponsor.fxml"));
           Scene scene = new Scene(parent);
            scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.show();
      

        
    }
      /* Parent root = FXMLLoader.load((getClass()).getResource("/pidev/GUI/sponsor.fxml"));
              Scene scene = new Scene(root);
              //        Scene scene = new Scene(root, 300, 250);
              primaryStage.initStyle(StageStyle.DECORATED.UNDECORATED);
              
              


       
       root.setOnMousePressed(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                xoff = event.getSceneX();
                yoff = event.getSceneY();
            }
            
        });
       
         root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
              primaryStage.setX(event.getSceneX() - xoff);
              primaryStage.setY(event.getSceneY() - yoff);
            }
            
        });
        primaryStage.setScene(scene);
        primaryStage.show();
      
       
    }*/

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
