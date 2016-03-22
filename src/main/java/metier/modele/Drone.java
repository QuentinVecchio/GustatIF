/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author qvecchio
 */
@Entity
public class Drone extends Livreur{
    private Double avrSpeed;

    public Drone(Double avrSpeed, Double longitude, Double latitude, boolean IsFree, int poidMax) {
        super(longitude, latitude, IsFree, poidMax);
        this.avrSpeed = avrSpeed;
    }

    public Double getAvrSpeed() {
        return avrSpeed;
    }

    public void setAvrSpeed(Double avrSpeed) {
        this.avrSpeed = avrSpeed;
    }


    
}
