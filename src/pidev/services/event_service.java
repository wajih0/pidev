/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.entities.evennement;
import pidev.entities.type_evennement;
import pidev.utils.DataSource;

/**²
 *
 * @author wajih ben hmida
 */
public class event_service implements IService<evennement>{
    Connection cnx ;

    public event_service(int par, String nom, String description){
        cnx=DataSource.getInstance().getCnx();
                
    }
  
   /* @Override
    public void add(evennement e) {
 String qry ="INSERT INTO `evennement`( `nom`,`date`,`lieu`,`prix`,`description`,`nb_participants`,`type_evenement` ) VALUES ('"+e.getNom()+"','"+e.getDate()+"','"+e.getLieu()+"','"+e.getPrix()+"','"+e.getDescription()+"','"+e.getNb_participants()+"','"+e.getType_evenement()+"')";

         

    try{
        Statement stm = cnx.createStatement();
        
        stm.executeUpdate(qry);        
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
    }    }*/

    public event_service() {
    }

    public event_service(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
    
    public void add(evennement e) {
        try{
 String qry ="INSERT INTO `evennement`( `nom`,`date`,`lieu`,`prix`,`description`,`nb_participants`,`type_evenement`,`sponsor_id` )" +"VALUES (?,?,?,?,?,?,?,?)";
 PreparedStatement ps = cnx.prepareStatement(qry);
 ps.setString(1, e.getNom());
 ps.setDate(2,new java.sql.Date(System.currentTimeMillis()));
 ps.setString(3, "tunis");
 ps.setInt(4, 40);
 ps.setString(5, "aaaa");
 ps.setInt(6, 100);
 ps.setString(7, type_evennement.MUSIQUE.name());
 ps.setInt(8, 1);
 
 int rowsInserted=ps.executeUpdate();
          System.out.println(rowsInserted  + " ligne(s) inserée(s)");
          
         
        }catch(SQLException q) {
         q.printStackTrace();
      }    }

    @Override
    public List<evennement> afficher() {
 String requete ="select * from evennement";
        List<evennement> list=new ArrayList<>();
        try {
            Statement stm=cnx.createStatement();
           ResultSet rs= stm.executeQuery(requete);
           while(rs.next()){
               evennement p=new evennement
        (rs.getInt("id"), rs.getString("nom"), rs.getString("description"));
               list.add(p);
                       
           }
        } catch (SQLException ex) {
            Logger.getLogger(event_service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list; 
    }

    
  public List<evennement> modifier(evennement e) {
String requete = "UPDATE `evennement` SET `nom`='" + e.getNom() + "', `description`='" + e.getDescription() + "' WHERE `id`=" + e.getId();

    List<evennement> list = new ArrayList<>();
    try {
        Statement stm = cnx.createStatement();
        stm.executeUpdate(requete);
        System.out.println("L'événement " + e + " a été modifier avec succès.");
        list.add(e); // Ajoute l'événement mis à jour dans la liste
    } catch (SQLException ex) {
        Logger.getLogger(event_service.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
}


    
    public void supprimer(String nom) {
   String requete = "DELETE FROM evennement WHERE nom = '" + nom + "';";
    try {
        Statement stm = cnx.createStatement();
        stm.executeUpdate(requete);
        System.out.println("L'événement " + nom + " a été supprimé avec succès.");
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la suppression de l'événement " + nom + " : " + ex.getMessage());
    }
                }

  
    
    
    
}
