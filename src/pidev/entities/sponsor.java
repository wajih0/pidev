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
    private String Image ; 

    public sponsor() {
    }

    
    public sponsor( int id, String nom ,String Image) {
        this.id=id;
   
        this.nom = nom;
        this.Image=Image ;
    }

    public sponsor(int id, String string) {
  this.id=id;
   
        this.nom = nom;    }

  public String getImage(){
      return Image ;
  }

    public void setImage(String Image) {
        this.Image = Image;
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
        return "sponsor{" + "id=" + id + ", nom=" + nom + ", Image=" + Image + '}';
    }

    
    
}
