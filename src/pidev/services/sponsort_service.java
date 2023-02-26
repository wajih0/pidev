/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import pidev.entities.evennement;
import pidev.entities.sponsor;
import pidev.utils.DataSource;

/**
 *
 * @author wajih ben hmida
 */
public class sponsort_service implements IService<sponsor>{
Connection cnx ;

public sponsort_service(String nom){
    cnx=DataSource.getInstance().getCnx();
}

    public sponsort_service() {
    }

    

   

    @Override
    public void add(sponsor s) {
 String qry ="INSERT INTO `sponsor`( `nom`) VALUES ('"+s.getNom()+"')";

    try{
        Statement stm = cnx.createStatement();
        stm.executeUpdate(qry);        
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
    }    }

    @Override
    public List<sponsor> afficher() {
String requete ="select * from sponsor";
        List<sponsor> list=new ArrayList<>();
        try {
            Statement stm=cnx.createStatement();
           ResultSet rs= stm.executeQuery(requete);
           while(rs.next()){
               sponsor s = new sponsor(4, rs.getString("nom"));
               list.add(s);
                       
           }
        } catch (SQLException ex) {
            Logger.getLogger(event_service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;  
    }
    
        
    public void update(sponsor s) {
String requete = "UPDATE sponsor SET nom='" + s.getNom() + "' WHERE id=" + s.getId();
        try {
            Statement st=cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(sponsort_service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
  
  
    public void supprimer_sponsor(String nom) {
   String requete = "DELETE FROM sponsor WHERE nom = '" + nom + "';";
    try {
        Statement stm = cnx.createStatement();
        stm.executeUpdate(requete);
        System.out.println("L'événement " + nom + " a été supprimé avec succès.");
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la suppression de l'événement " + nom + " : " + ex.getMessage());
    }
                }
    
    
    
    
    




}
