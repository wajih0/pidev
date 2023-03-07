/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory; 
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import pidev.entities.evennement;
import pidev.entities.type_evennement;
import pidev.services.event_service;
import pidev.utils.DataSource;
import java.awt.TextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.stream.Collectors;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import java.awt.Color;


/**
 * FXML Controller class
 *
 * @author wajihbenhmida
 */
public class ListeventController implements Initializable {
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
    private TableColumn<evennement, String> sponsor_id;
    
    private ImageView imgqrcode;



    
    
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
         
         
                  /*    QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = "file:///C:/profetionel/java/pidev/20230304133814.pdf";
        int width = 300;
        int height = 300;
        String fileType = "png";
        
        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
           graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
        } catch (WriterException ex) {
            Logger.getLogger(sponsorMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ImageView qrView = new ImageView();
        imgqrcode.setImage(SwingFXUtils.toFXImage(bufferedImage, null));*/
        
        ///endqr
       


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
        PdfPTable table = new PdfPTable(7);

        //On créer l'objet cellule.
        PdfPCell cell;

        //contenu du tableau.
        table.addCell("Nom");
        table.addCell("Date ");
        table.addCell("Type evennement");
        table.addCell("Prix");
        table.addCell("Description");
        table.addCell("lieu");
        table.addCell("nombre participant ");
        
        


        evennement r = new evennement();
        e.afficher().forEach(e -> {
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    table.addCell(String.valueOf(e.getNom()));
                        table.addCell(String.valueOf(e.getDate()));
                                                table.addCell(String.valueOf(e.getType_evenement()));


            table.addCell(String.valueOf(e.getPrix()));
            table.addCell(String.valueOf(e.getDescription()));
            table.addCell(String.valueOf(e.getLieu()));
            table.addCell(String.valueOf(e.getNb_participants()));
            
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
    File file = new File(DateLyoum + ".pdf");
    Desktop desktop = Desktop.getDesktop();
    if (file.exists()) //checks file exists or not  
    {
        desktop.open(file); //opens the specified file   
    }

}
    
    
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
            Parent parent = FXMLLoader.load(getClass().getResource("addevent.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ListeSponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    @FXML
    void qrcode(MouseEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("qr_codr.fxml"));
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

    date.setCellValueFactory(new PropertyValueFactory<evennement,String>("date"));
    prix.setCellValueFactory(new PropertyValueFactory<evennement,String>("prix"));
    nb_participants.setCellValueFactory(new PropertyValueFactory<evennement,String>("nb_participants"));
    type_evenement.setCellValueFactory(new PropertyValueFactory<evennement,String>("type_evenement"));
    sponsor_id.setCellValueFactory(new PropertyValueFactory<evennement,String>("sponsor_id"));

EVENTTAB.setEditable(true);
    
}}
