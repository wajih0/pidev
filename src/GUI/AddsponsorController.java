/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.TextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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
    void Save(MouseEvent event) {
connection = DataSource.getInstance().getCnx();
        String name = nom.getText();
       

        if (name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            getQuery();
            insert();
            clean();

        }

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
        // TODO
    }    

    @FXML
    private void close(MouseEvent event) {
           Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void getQuery() {
                 
  if (update == false) {
            
            query = "INSERT INTO `sponsor`( `nom`) VALUES (?)";

        }else{
            query = "UPDATE `sponsor` SET "
                   
                    + "`email`= ? WHERE id = '"+id+"'";
        }    }

    private void insert() {
             try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nom.getText());
          
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
