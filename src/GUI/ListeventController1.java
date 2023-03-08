/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import pidev.entities.evennement;
import pidev.services.event_service;

/**
 * FXML Controller class
 *
 * @author wajihbenhmida
 */
public class ListeventController1 implements Initializable {

  Connection cnx ;

     @FXML
    private TableView<evennement> EVENTTAB;
  //    @FXML
    //private TableColumn<evennement, String> id;
     @FXML
    private TableColumn<evennement, String> nom;
     
     @FXML
    private TableColumn<evennement, String> description;
   
 @FXML
    private TableColumn<evennement, String> lieu;
    @FXML
    private TableColumn<evennement, String> date;

    
@FXML
    private TableColumn<evennement, String> prix;
   

    @FXML
    private TableColumn<evennement, String> nb_participants;

      @FXML
        private javafx.scene.control.TextField search;
      
 @FXML
    private TableColumn<evennement, String> type_evenement;
    

    @FXML
    private TableColumn<?,?> sponsor_id;
    




    
    
     String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    evennement evennement = null ;
         ObservableList<evennement>  eventlist = FXCollections.observableArrayList();
        //  ObservableList<evennement> datalist=FXCollections.observableArrayList();

event_service e = new event_service();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         LoadDate();
         
        

          }
    
    
        
@FXML
void generer(MouseEvent event) throws IOException {
   long millis = System.currentTimeMillis();
    java.sql.Date DateRapport = new java.sql.Date(millis);

    String DateLyoum = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(DateRapport);
    System.out.println("Date d'aujourdhui : " + DateLyoum);

    com.itextpdf.text.Document document = new com.itextpdf.text.Document();

    try {
        PdfWriter.getInstance(document, new FileOutputStream(String.valueOf(DateLyoum + ".pdf")));//yyyy-MM-dd
       
       document.open();
        Paragraph ph1 = new Paragraph("Rapport Pour les evennement :" + DateRapport);
        Paragraph ph2 = new Paragraph(".");
        PdfPTable table = new PdfPTable(8);

        //On créer l'objet cellule.
        PdfPCell cell;

        //contenu du tableau.
      table.addCell("Nom");
      table.addCell("Description");
        table.addCell("lieu");
         table.addCell("Prix");
       
        
                        table.addCell("sponsor_id");
                         table.addCell("Date ");

       
        
        table.addCell("nombre participant ");
        table.addCell("Type evennement");


        evennement r = new evennement();
        e.afficher().forEach(e -> {
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(String.valueOf(e.getNom()));
                                     table.addCell(String.valueOf(e.getDescription()));
            table.addCell(String.valueOf(e.getLieu()));
            table.addCell(String.valueOf(e.getPrix()));
            table.addCell(String.valueOf(e.getSponsors()));
                        table.addCell(String.valueOf(e.getDate()));
                                                table.addCell(String.valueOf(e.getNb_participants()));

                        
                                                table.addCell(String.valueOf(e.getType_evenement()));
                        


            
           
            
        });
        document.add(ph1);
        document.add(ph2);
        document.add(table);
        //  document.addAuthor("Bike");
        // AlertDialog.showNotification("Creation PDF ", "Votre fichier PDF a ete cree avec success", AlertDialog.image_checked);
    } catch (Exception e) {
        System.out.println(e);
    }
    document.close();

    ///Open FilePdf
   Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Fichier PDF généré");
    alert.setHeaderText("Le fichier PDF a été généré avec succès !");
    alert.setContentText("Voulez-vous ouvrir le fichier PDF maintenant ?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        File file = new File(DateLyoum + ".pdf");
        Desktop desktop = Desktop.getDesktop();
    if (file.exists()) //checks file exists or not  
    {
        desktop.open(file); //opens the specified file   
    }

}}
    
    
    @FXML
    void supprimer( ActionEvent event) throws SQLException{
        
    int myIndex = EVENTTAB.getSelectionModel().getSelectedIndex();
        String nom = EVENTTAB.getSelectionModel().getSelectedItem().getNom();
        e.supprimerid(nom);
         JOptionPane.showMessageDialog(null, "event supprimer ");
        
    }

  
   
    
 @FXML
void refreshtable(MouseEvent event) {
    LoadDate();
   }

@FXML
    void filter(KeyEvent event) {
       String searchText = search.getText() != null ? search.getText().toLowerCase() : "";
        ObservableList<evennement> filteredPeople = FXCollections.observableArrayList(e.afficher());

ObservableList<evennement> newdata = filteredPeople.stream()
        .filter(n -> {
            String nom = n.getNom() != null ? n.getNom().toLowerCase() : "";
            String description = n.getDescription() != null ? n.getDescription().toLowerCase() : "";
            String lieu = n.getLieu() != null ? n.getLieu().toLowerCase() : "";

            return nom.contains(searchText)
                    || nom.equals(searchText)
                    || description.contains(searchText)
                    || description.equals(searchText)
                    || lieu.contains(searchText)
                    || lieu.equals(searchText);
        })
        .collect(Collectors.toCollection(FXCollections::observableArrayList));

EVENTTAB.setItems(newdata);


    }




    @FXML
    void close(MouseEvent event) {
             Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }


   

    @FXML
    void AddEVENT(MouseEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("AddeventAdmin.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListeSponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    
    private void LoadDate() {
        EVENTTAB.setItems(FXCollections.observableArrayList(e.afficher()));
    //connection = DataSource.getInstance().getCnx();
   
    
   // id.setCellValueFactory(new PropertyValueFactory<evennement,String>("id"));

    nom.setCellValueFactory(new PropertyValueFactory<evennement,String>("nom"));

    description.setCellValueFactory(new PropertyValueFactory<evennement,String>("description"));
        lieu.setCellValueFactory(new PropertyValueFactory<evennement,String>("lieu"));
    prix.setCellValueFactory(new PropertyValueFactory<evennement,String>("prix"));

    sponsor_id.setCellValueFactory(new PropertyValueFactory<>("sponsors"));
    date.setCellValueFactory(new PropertyValueFactory<evennement,String>("date"));
    nb_participants.setCellValueFactory(new PropertyValueFactory<evennement,String>("nb_participants"));
    type_evenement.setCellValueFactory(new PropertyValueFactory<evennement,String>("type_evenement"));
   

EVENTTAB.setEditable(true);
    
}
    
}
