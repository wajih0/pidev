/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.entities;

/**
 *
 * @author wajih ben hmida
 */
public class Personne {
    private int id , age;
    private String nom,prenom ;

    public Personne() {
    }
       public Personne(int age, String nom, String prenom) {
        this.age = age;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Personne(int i, String bensalah, String saif, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public String toString(){
        return "Personne{" + "id=" + id + ", age=" + age + ", nom=" + nom + ", prenom=" + prenom + "\n}";
    }
    
}
