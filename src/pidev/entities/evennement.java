/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.util.Date;
import java.util.List;


/**
 *
 * @author wajih ben hmida
 */
public class evennement {
    private int id ;
    private String nom , description ,lieu ;
    private Date Date ;
    private int prix,nb_participants;
    private type_evennement type_evenement; //enum
    private List<sponsor> sponsors;//one to many sponsor

    public evennement(int id, String nom, String description1) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.lieu = lieu;
        this.Date = Date;
        this.prix = prix;
        this.nb_participants = nb_participants;
        this.type_evenement = type_evenement;
        this.sponsors = sponsors;
    }
    

    public evennement() {
    }

    public evennement(String nom, String description, String lieu, int prix, int nb_participants, type_evennement type_evenement) {
        this.nom = nom;
        this.description = description;
        this.lieu = lieu;
        this.prix = prix;
        this.nb_participants = nb_participants;
        this.type_evenement = type_evenement;
    }

  

  

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getLieu() {
        return lieu;
    }

    public Date getDate() {
        return Date;
    }

    public int getPrix() {
        return prix;
    }

    public int getNb_participants() {
        return nb_participants;
    }

    public type_evennement getType_evenement() {
        return type_evenement;
    }

    public List<sponsor> getSponsors() {
        return sponsors;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setNb_participants(int nb_participants) {
        this.nb_participants = nb_participants;
    }

    public void setType_evenement(type_evennement type_evenement) {
        this.type_evenement = type_evenement;
    }

    public void setSponsors(List<sponsor> sponsors) {
        this.sponsors = sponsors;
    }

    @Override
    public String toString() {
        return "evennement{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", lieu=" + lieu + ", Date=" + Date + ", prix=" + prix + ", nb_participants=" + nb_participants + ", type_evenement=" + type_evenement + ", sponsors=" + sponsors + '}';
    }

    public evennement get(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
    
    
    
   
   
    
    
    
    
    
}
