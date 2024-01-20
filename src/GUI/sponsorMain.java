package GUI;

/*
 * To change this license header, choose License
import com.google.zxing.qrcode.QRCodeWriter;Headers in Project Properties.
        * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
         import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
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
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import pidev.entities.evennement;

/**
 *
 * @author wajihbenhmida
 */
public class sponsorMain extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
        //AddeventAdmin
        // listevent_1 
        //listevent  fxml de user 
        //listeSponsor
        Parent parent = FXMLLoader.load(getClass().getResource("listeSponsor.fxml"));
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
