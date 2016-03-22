/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;

import com.google.maps.GeoApiContext;
import com.google.maps.model.LatLng;
import java.util.List;
import metier.modele.Drone;
import metier.modele.Livreur;
import metier.modele.LivreurCycliste;
import util.GeoTest;
import static util.GeoTest.getFlightDistanceInKm;
import static util.GeoTest.getTripDurationByBicycleInMinute;

/**
 *
 * @author quentinvecchio
 */
public class ServiceTechnique {
    
    public void sendMail(String destinataire, String objet, String corps) {
        System.out.println("Envoie du mail :");
        System.out.println("Dest : " + destinataire);
        System.out.println("Objet : " + objet);
        System.out.println(corps);
    }
    public Livreur findeNirestLivrer (List <Livreur> livreurs , Double longitude, Double latitude){
        Double TimeMin = Double.MAX_VALUE;
        Livreur nirestLivrer = null; 
        Double curentTime; 
        for (Livreur curenteLivrere : livreurs){
            LatLng[] step = new LatLng[0];
            LatLng livreurPostion  = new LatLng(curenteLivrere.getLatitude(),curenteLivrere.getLatitude());
            
            // livreur est un done 
            if (Drone.class == curenteLivrere.getClass()){
                Drone curDrone = (Drone) curenteLivrere;
                curentTime = getFlightDistanceInKm(livreurPostion , new LatLng(latitude,longitude) )
                        *curDrone.getAvrSpeed();
            }
            
            // livreur est un Cycliste
            else  if (LivreurCycliste.class == curenteLivrere.getClass()){
            curentTime = getTripDurationByBicycleInMinute(livreurPostion ,new LatLng(latitude,longitude), step );
            
            } 
            
            //en cas d'érreurs 
            else {
                System.out.println("érreur : ServiseTeniques.findeNirestLivrer a revoire "); //TODO sup 
                        curentTime = Double.MAX_VALUE;
            }
            
            //recherche max Val : 
            if (curentTime<TimeMin){
                TimeMin = curentTime; 
                nirestLivrer = curenteLivrere; 
            }
        }
        
        return nirestLivrer; 
    }
}
