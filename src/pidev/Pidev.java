/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.sql.Date;

import pidev.entities.evennement;
import pidev.entities.sponsor;
import pidev.entities.type_evennement;
import pidev.services.event_service;
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
       String  d= "	2008-11-11"; 
        Date d1 = new Date(2023 - 1900, 13 - 01, 13);
        
        evennement e = new evennement("hello","dezsss","sdhjk",d1,100,100,type_evennement.ART,1);
        
        event_service es = new event_service();
        sponsort_service pi=new sponsort_service();
        sponsor s= new sponsor();
        s.setNom("ahla bik");
        s.setId(17);
       
       pi.update(s);
      //  evennement e= new evennement( 1,"fir1113","lelelele");
      //  es.modifier(e);
        // es.add(e);
        System.out.println(es.afficher());
    //  es.supprimer("siki2");
          
       // System.out.println(es.afficher());
       
        sponsort_service ss = new  sponsort_service();
        sponsor sp1 = new sponsor(2,"marwa");
//ss.add(sp1);
       // System.out.println(ss.afficher());
            // sponsort_service ss=new sponsort_service(17,"jannet2");

      //  sponsor sp1=new sponsor( 17,"sabrine");
        //ss.add(sp1);
       // ss.afficher();
      // System.out.println(ss.afficher());
       // ss.update(sp1);
       // ss.supprimer_sponsor("jannet");
}

   
}