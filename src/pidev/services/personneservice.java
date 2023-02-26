/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import static java.awt.Event.INSERT;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import pidev.entities.Personne;
import pidev.utils.DataSource;

/**
 *
 * @author wajih ben hmida
 */
public class personneservice implements IService<Personne> {
Connection cnx ;
      public personneservice(){
            cnx = DataSource.getInstance().getCnx();      

}
    @Override
    public void add(Personne t) {
    String qry ="INSERT INTO `personne`( `nom`, `prenom`, `age`) VALUES ('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getAge()+"')";

    try{
        Statement stm = cnx.createStatement();
        stm.executeUpdate(qry);        
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
    }}

    @Override
    public List<Personne> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

   



 
   
    
}
