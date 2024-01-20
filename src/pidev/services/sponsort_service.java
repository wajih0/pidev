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
               s.setNom(rs.getString(2));
               s.setImage(rs.getString(3));
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
   }
    
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
    


    
    
    
    
    




}
