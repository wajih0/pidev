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
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.geometry.Insets;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import pidev.entities.sponsor;
import pidev.utils.DataSource;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Desktop;
import java.awt.TextField;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import pidev.entities.evennement;
import pidev.services.sponsort_service;

/**
 * FXML Controller class
 *
 * @author wajihbenhmida
 */
public class ListeSponsorController implements Initializable {

    @FXML
    private javafx.scene.control.TextField ID;
    @FXML
    private TableView<sponsor> sponsortable;
    @FXML
    private TableColumn<sponsor, String> id;

    @FXML
    private TableColumn<sponsor, String> nom;
    @FXML
    private TableColumn<sponsor, String> Image;

    @FXML
    private javafx.scene.control.TextField MODIF;
    @FXML
    private ImageView imgg;
    @FXML
    private Label path;
    @FXML
    private Button Timage;

    @FXML
    private javafx.scene.control.TextField search;

    int index = -1;
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    sponsor sponsor = null;
    ObservableList<sponsor> sponsorList = FXCollections.observableArrayList();
    sponsort_service sp = new sponsort_service();

    @FXML
    void filter(KeyEvent event) {
        String searchText = search.getText() != null ? search.getText().toLowerCase() : "";
        ObservableList<sponsor> filteredPeople = FXCollections.observableArrayList(sp.afficher());

        ObservableList<sponsor> newdata = filteredPeople.stream().filter(n -> {
            String nom = n.getNom() != null ? n.getNom().toLowerCase() : "";

            return nom.contains(searchText)
                    || nom.equals(searchText);

        })
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        sponsortable.setItems(newdata);

    }

    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

    @FXML
    public void supprim(ActionEvent event) {
        int myIndex = sponsortable.getSelectionModel().getSelectedIndex();
        String nom = sponsortable.getSelectionModel().getSelectedItem().getNom();
        String imagee = sponsortable.getSelectionModel().getSelectedItem().getImage();
        sp.supprimerid(nom);
        JOptionPane.showMessageDialog(null, "sponsor supprimer ");
        refreshtable();

    }

    void modifier(MouseEvent event) {

    }

    public void delete(ActionEvent event) {

    }

    @FXML
    void getAddview() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("addsponsor.fxml"));
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
    void print(MouseEvent event) throws IOException {

        long millis = System.currentTimeMillis();
        java.sql.Date DateRapport = new java.sql.Date(millis);

        String DateLyoum = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(DateRapport);
        System.out.println("Date d'aujourdhui : " + DateLyoum);

        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(String.valueOf(DateLyoum + ".pdf")));//yyyy-MM-dd

            document.open();
            Paragraph ph1 = new Paragraph("Rapport Pour les sponsor :" + DateRapport);
            
            
            Paragraph ph2 = new Paragraph(".");
            PdfPTable table = new PdfPTable(1);

            //On créer l'objet cellule.
            PdfPCell cell;

            //contenu du tableau.
            table.addCell("Nom");

            evennement r = new evennement();
            sp.afficher().forEach(e -> {
                table.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(String.valueOf(e.getNom()));

            });
            document.add(ph1);
            document.add(ph2);
            document.add(table);
        } catch (Exception e) {
            System.out.println(e);
        }
        document.close();

        ///Open FilePdf
       Alert alert = new Alert(AlertType.CONFIRMATION);
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
    void refreshtable() {

        LoadDate();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadDate();

    }

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
            imgg.setImage(image);
            imgg.setFitWidth(200);
            imgg.setFitHeight(200);
            imgg.scaleXProperty();
            imgg.scaleYProperty();
            imgg.setSmooth(true);
            imgg.setCache(true);
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
        path.setText(file.getAbsolutePath());
    }
    
    
    private void LoadDate() {
    sponsortable.setItems(FXCollections.observableArrayList(sp.afficher()));
    id.setCellValueFactory(new PropertyValueFactory<>("id"));
    nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
    Image.setCellValueFactory(new PropertyValueFactory<>("Image"));
    sponsortable.setEditable(true);

  sponsortable.setRowFactory(tv -> {
    TableRow<sponsor> myRow = new TableRow<>();
    myRow.setOnMouseClicked((MouseEvent event) -> {
        if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
            int myIndex = sponsortable.getSelectionModel().getSelectedIndex();
            int id = Integer.parseInt(String.valueOf(sponsortable.getItems().get(myIndex).getId()));
            String idd = String.valueOf(id);
            ID.setText(idd);

            String nommmmmm = sponsortable.getItems().get(myIndex).getNom();
            String imageeeeeeee = sponsortable.getItems().get(myIndex).getImage();
            MODIF.setText(nommmmmm);
            path.setText(imageeeeeeee);

            // Chargement de l'image à partir du chemin d'accès stocké dans la base de données
            File file = new File(imageeeeeeee);
            Image c = new Image(file.toURI().toString());
            imgg.setImage(c);
            imgg.setFitWidth(200);
            imgg.setFitHeight(200);
            imgg.scaleXProperty();
            imgg.scaleYProperty();
            imgg.setSmooth(true);
            imgg.setCache(true);
        }
    });
    return myRow;
});


}

    

    @FXML
    private void delete(MouseEvent event) {
    }

    void getAddview(MouseEvent event) {

    }

    @FXML
    void getSelected(MouseEvent event) {

    }

    @FXML
    private void Edit(ActionEvent event) {

        sponsor s = new sponsor();
        s.setId(Integer.parseInt(ID.getText()));
        System.out.println(path.getText());

        s.setImage(path.getText());
        s.setNom(MODIF.getText());
        System.out.println(s.getId());
        System.out.println(s);

        sp.update(s);
        refreshtable();
    }

}
