/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

import java.sql.Date;


/**
 *
 * @author wajih ben hmida
 */
public class sponsor {
 
    private int id;
    private String nom;

    public sponsor( int par, String nom) {
   
        this.nom = nom;
    }

    public sponsor(int aInt, String string, Date date, String string0, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public sponsor(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "sponsor{" + "id=" + id + ", nom=" + nom + '}';
    }
    
    
    
    
}
