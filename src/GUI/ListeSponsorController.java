/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
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
import javafx.scene.layout.HBox;


/**
 * FXML Controller class
 *
 * @author wajihbenhmida
 */
public class ListeSponsorController implements Initializable {
        @FXML
    private TableView<sponsor> sponsortable;
           @FXML
    private TableColumn<sponsor,String> id;

    @FXML
    private TableColumn<sponsor, String> nom;
      String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    sponsor sponsor = null ;
     ObservableList<sponsor>  sponsorList = FXCollections.observableArrayList();


    @FXML
    void close(MouseEvent event) {
         Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();

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
    void print(MouseEvent event) {

    }

    @FXML
    void refreshtable() {
           try {
            sponsorList.clear();
            
            query = "SELECT * FROM `sponsor`";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
                sponsorList.add(new  sponsor(
                        resultSet.getInt("id"),
                        resultSet.getString("nom")));
              
                sponsortable.setItems(sponsorList);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ListeSponsorController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadDate();
    }    

    private void LoadDate() {
connection = DataSource.getInstance().getCnx();
refreshtable();
      id.setCellValueFactory(new PropertyValueFactory<sponsor,String>("id"));

        nom.setCellValueFactory(new PropertyValueFactory<sponsor,String>("nom"));
        
         Callback<TableColumn<sponsor, String>, TableCell<sponsor, String>> cellFoctory;
            cellFoctory = (TableColumn<sponsor, String> param) -> {
                // make cell containing buttons
                final TableCell<sponsor, String> cell = new TableCell<sponsor, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        //that cell created only on non-empty rows
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                            
                        } else {
                            
                            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.CLOSE);
                            FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                            
                            deleteIcon.setStyle(
                                    " -fx-cursor: hand ;"
                                            + "-glyph-size:28px;"
                                            + "-fx-fill:#ff1744;"
                            );
                            editIcon.setStyle(
                                    " -fx-cursor: hand ;"
                                            + "-glyph-size:28px;"
                                            + "-fx-fill:#00E676;"
                            );
                            deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                                
                                try {
                                    sponsor = sponsortable.getSelectionModel().getSelectedItem();
                                    query = "DELETE FROM `student` WHERE id  ="+sponsor.getId();
connection = DataSource.getInstance().getCnx();
                                    preparedStatement = connection.prepareStatement(query);
                                    preparedStatement.execute();
                                    refreshtable();
                                    
                                } catch (SQLException ex) {
                                    Logger.getLogger(ListeSponsorController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                                
                                
                                
                                
                            });
                            editIcon.setOnMouseClicked((MouseEvent event) -> {
                                
                                sponsor = sponsortable.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader ();
                                loader.setLocation(getClass().getResource("/tableView/addStudent.fxml"));
                                try {
                                    loader.load();
                                } catch (IOException ex) {
                                    Logger.getLogger(ListeSponsorController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                                AddsponsorController addsponsorController = loader.getController();
                                addsponsorController.setUpdate(true);
                                addsponsorController.setTextField(sponsor.getId(), sponsor.getNom() );
                                Parent parent = loader.getRoot();
                                Stage stage = new Stage();
                                stage.setScene(new Scene(parent));
                                stage.initStyle(StageStyle.UTILITY);
                                stage.show();
                                
                                
                                
                                
                            });
                            
                            HBox managebtn = new HBox(editIcon, deleteIcon);
                            managebtn.setStyle("-fx-alignment:center");
                            HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                            HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                            
                            setGraphic(managebtn);
                            
                            setText(null);
                            
                        }
                    }
                    
                };
                
                return cell;
            };
         editCol.setCellFactory(cellFoctory);
         sponsortable.setItems(sponsorList);
         
         
    }
        
    }
    
    
    
}
