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
public class LivreurCycliste extends Livreur  {
    private String nom; 
    private String prenom; 
    private String mail; 

    public LivreurCycliste() {
    
    }

    public LivreurCycliste(String nom, String prenom, String mail, Double longitude, Double latitude, boolean IsFree, int poidMax) {
        super(longitude, latitude, IsFree, poidMax);
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "LivreurCycliste{" + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + '}';
    }
}
