/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.TextField;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import pidev.entities.evennement;
import pidev.entities.sponsor;
import pidev.utils.DataSource;

/**
 * FXML Controller class
 *
 * @author wajihbenhmida
 */
public class AddsponsorController implements Initializable {
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement  ;
    ResultSet resultSet = null ;
    sponsor sponsor = null ;
    private boolean update;
    int id;
    @FXML
    private javafx.scene.control.TextField nom;

    
    
    @FXML
    private Button Timage;

    @FXML
    private ImageView imgajoutincours;

    @FXML
    private Label imgpathttt;
    
    @FXML
    void addimgcours(ActionEvent event) {
        
         FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        File file = fileChooser.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            imgajoutincours.setImage(image);
            imgajoutincours.setFitWidth(200);
            imgajoutincours.setFitHeight(200);
            imgajoutincours.scaleXProperty();
            imgajoutincours.scaleYProperty();
            imgajoutincours.setSmooth(true);
            imgajoutincours.setCache(true);
            FileInputStream fin = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fin.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            byte[] person_image = bos.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger("ss");
        }
        imgpathttt.setText(file.getAbsolutePath());
    }
        
        

    

    @FXML
    void Save(MouseEvent event) {
connection = DataSource.getInstance().getCnx();
        String name = nom.getText();
        String img = imgpathttt.getText();
       

        if (name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please remplir les champs ");
            alert.showAndWait();

        } else {
            getQuery();
            insert();
            clean();

        }
        JOptionPane.showMessageDialog(null, "sponsor ajouter");
        Notifications.create()
            .title("New Linge Added")
            .text("A new sponsor has been added successfully!")
            .showInformation();


    }
    
   
     @FXML
    void retour(MouseEvent event) throws IOException {
     Parent page1 = FXMLLoader.load(getClass().getResource("listeSponsor.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show(); 
    }

    @FXML
    void clean() {
                nom.setText(null);


    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    }    

    @FXML
    private void close(MouseEvent event) {
           Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void getQuery() {
                 
  if (update == false) {
            
            query = "INSERT INTO `sponsor`( `nom`, `image`) VALUES (?,?)";

        }else{
            query = "UPDATE `sponsor` SET "
                   
                    + "`email`= ? WHERE id = '"+id+"'";
        }    }

    private void insert() {
             try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nom.getText());
            preparedStatement.setString(2,imgpathttt.getText() );
          
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(AddsponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    void setTextField(int id, String name) {

        id = id;
        nom.setText(name);
       

    }

    void setUpdate(boolean b) {
        this.update = b;

    }
    
}
