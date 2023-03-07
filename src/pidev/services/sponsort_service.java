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

    public sponsort_service(int i, String sabrine) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

   

    @Override
    public void add(sponsor s) {
 String qry ="INSERT INTO `sponsor`( `id`,`nom`, `image`) VALUES ('"+s.getId()+"','"+s.getNom()+"','"+s.getImage()+"')";

    try{
        Statement stm = cnx.createStatement();
        stm.executeUpdate(qry);        
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
    }    }

    @Override
    public List<sponsor> afficher() {
           cnx=DataSource.getInstance().getCnx();

      
String requete ="select * from sponsor";

        List<sponsor> list=new ArrayList<>();
        try {
       
      
            Statement stm=cnx.createStatement();
           ResultSet rs= stm.executeQuery(requete);
           while(rs.next()){
               sponsor s = new sponsor();
               s.setId(rs.getInt(1));
               s.setNom(rs.getNString(2));
               s.setImage(rs.getNString(3));
               list.add(s);
                       
           }
        } catch (SQLException ex) {
            Logger.getLogger(event_service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;  
    }
    
        
    public void update(sponsor u ) {
String requete = "UPDATE `sponsor` SET `nom`='"+u.getNom()+"',`image`='"+u.getImage()+"' WHERE id=" + u.getId();
        try {
            cnx=DataSource.getInstance().getCnx();

            Statement st=cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(sponsort_service.class.getName()).log(Level.SEVERE, null, ex);
        }
      /*   try {
        String qry = "UPDATE `sponsor` SET `nom`='"+u.getNom()+"' WHERE `id`='"+u.getId()+"'"  ;

            cnx = DataSource.getInstance().getCnx();

            Statement stm = cnx.createStatement();
            stm.executeUpdate(qry);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }*/
    
  
   /* public void supprimer_sponsor(String nom) {
   String requete = "DELETE FROM sponsor WHERE nom = '" + nom + "';";
    try {
        Statement stm = cnx.createStatement();
            cnx=DataSource.getInstance().getCnx();

        stm.executeUpdate(requete);
        System.out.println("L'événement " + nom + " a été supprimé avec succès.");
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la suppression de l'événement " + nom + " : " + ex.getMessage());
    }
                }*/}
    
    public void supprimerid(String u) {
         try {
            String qry = "DELETE FROM `sponsor` WHERE Nom='" + u + "'";
            cnx = DataSource.getInstance().getCnx();

            Statement stm = cnx.createStatement();

            stm.executeUpdate(qry);
            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
    }
    
   /* public void supprimer_sponsor(String id) throws SQLException {
    Connection cnx = DataSource.getInstance().getCnx();
    PreparedStatement pst = null;
    try {
        pst = cnx.prepareStatement("DELETE FROM sponsor WHERE id = ?");
       
        int result = pst.executeUpdate();
        if(result > 0) {
            System.out.println("Sponsor supprimé avec succès.");
        } else {
            System.out.println("Impossible de supprimer le sponsor.");
        }
    } catch(SQLException e) {
        e.printStackTrace();
    } finally {
        if(pst != null) {
            pst.close();
        }
    }*/
    

    
    
    
    
    




}
