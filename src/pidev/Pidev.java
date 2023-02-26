/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.util.List;
import pidev.entities.evennement;
import pidev.entities.sponsor;
import pidev.entities.type_evennement;
import pidev.services.event_service;
import pidev.services.personneservice;
import pidev.services.sponsort_service;

/**
 *
 * @author wajih ben hmida
 */
public class Pidev {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Personne p1=new Personne(3, "bensalah", "saif");
        //personneservice ps=new personneservice();    
        //ps.add(p1);
        
        
        
        
        
        event_service es = new event_service(9, "siki3", "Ceci est un événement");
        evennement e= new evennement(9, "siki10","lelelele");
      //  es.modifier(e);
      // es.add(e);
    //  es.supprimer("siki2");
          
//es.afficher();
       



     
        sponsor sp1=new sponsor( 2,"jannet2");
        sponsort_service ss=new sponsort_service("jdbc:mysql://localhost:3306/wajih");
        //ss.add(sp1);
       // ss.afficher();
        System.out.println(ss.afficher());
        //ss.update(sp1);
       // ss.supprimer_sponsor("jannet");
}

   
}