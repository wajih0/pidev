/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import pidev.entities.evennement;
import pidev.entities.sponsor;
import pidev.entities.type_evennement;
import pidev.services.event_service;
import pidev.services.sponsort_service;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author wajihbenhmida
 */
public class AddeventAdminController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField desc;
    @FXML
    private TextField lieu;
    @FXML
    private TextField prix;
    @FXML
    private DatePicker datepickerdate;
    @FXML
    private TextField nb_particip;
    @FXML
    private ChoiceBox<type_evennement> type;
        ObservableList<type_evennement> options = FXCollections.observableArrayList(type_evennement.MUSIQUE , type_evennement.ART , type_evennement.CINEMA , type_evennement.THEATRE ,type_evennement.FOIRE ,type_evennement.DANCE);
    @FXML
    private ChoiceBox<sponsor> choiceSponsor;
 sponsort_service ss=new sponsort_service();
         ObservableList<sponsor> sponsoorslistchoice = FXCollections.observableList(ss.afficher());


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    type.setItems(options);
    choiceSponsor.setItems(sponsoorslistchoice);
        
    
        // TODO
    }    
    
    private void Retour(ActionEvent event) throws IOException {
          Parent page1 = FXMLLoader.load(getClass().getResource("listevent.fxml"));
        Scene scene = new Scene(page1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show(); 
    
    }
    
       public boolean validateInputs() {
        if (nom.getText().isEmpty()) {
                Alert alert1 = new Alert(Alert.AlertType.WARNING);
                alert1.setTitle("erreur !");
                alert1.setContentText("remplir le champ du nom  !");
                alert1.setHeaderText(null);
                alert1.show();
                return false;
        } else if (desc.getText().chars().allMatch( Character::isDigit)) {
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("erreur !");
                alert2.setContentText("remplir le champ du description ");
                alert2.setHeaderText(null);
                alert2.show();
                return false;
        } else if (lieu.getText().chars().allMatch( Character::isDigit)) {
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("erreur !");
                alert2.setContentText("remplir le champ du lieu ");
                alert2.setHeaderText(null);
                alert2.show();
                return false;
        } else if (nb_particip.getText().isEmpty()) {
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("erreur !");
                alert2.setContentText("remplir le champ du nombre participant !");
                alert2.setHeaderText(null);
                alert2.show();
                return false;
        }else if (prix.getText().isEmpty()) {
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("erreur !");
                alert2.setContentText("remplir le champ du prix !");
                alert2.setHeaderText(null);
                alert2.show();
                return false;
        }
        return true;
    }

    @FXML
    private void ajoutereevent(ActionEvent event) {
        if(validateInputs()){
           event_service e =new event_service();
           evennement f = new evennement();
            LocalDate localDate = datepickerdate.getValue();
            java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
            f.setDescription(desc.getText());
            f.setNom(nom.getText());
            f.setDate(sqlDate);
            f.setLieu(lieu.getText());
            f.setNb_participants(Integer.parseInt(nb_particip.getText()));
            f.setPrix(Double.parseDouble( prix.getText()));
            f.setType_evenement(type.getValue());
            sponsor s=choiceSponsor.getValue();
            f.setSponsors(s.getId());
            System.out.println(s.getId());
            e.add(f);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Creation d'evennement");
            alert.setHeaderText("Creation d'evennement");
            alert.setContentText("Evennement crée!");
            alert.showAndWait();
            
               Notifications.create()
            .title("New Linge Added")
            .text("A new event has been added successfully!")
            .showInformation();


    }}
}
