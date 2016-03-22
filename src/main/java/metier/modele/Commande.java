/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.modele;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author qvecchio
 */
@Entity
public class Commande implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDebut;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFin;
    private Map<Produit, Integer> contenues;
    @OneToOne
    private Client client;
    @OneToOne
    private Restaurant restaurant;
    @OneToOne
    private Livreur livreur;
    
    public Commande() {
    }

    public Commande(Client client, Restaurant restaurant, Livreur livreur, Date dateDebut, Date dateFin, Map<Produit, Integer> contenues) {
        this.client = client;
        this.restaurant = restaurant;
        this.livreur = livreur;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.contenues = contenues;
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Livreur getLivreur() {
        return livreur;
    }

    public void setLivreur(Livreur livreur) {
        this.livreur = livreur;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Map<Produit, Integer> getContenues() {
        return contenues;
    }

    public void setContenues(Map<Produit, Integer> contenues) {
        this.contenues = contenues;
    }

    @Override
    public String toString() {
        if(contenues.isEmpty()) {
            return "Commande vide";
        } else {
            String str = "Commande " + id + "\n";
            Float poidT = new Float(0); 
            Float prixT = new Float(0);
            str += "Restaurant " + restaurant.getDenomination() + "\n";
            str += restaurant.getAdresse() + "\n\n";//Adresse 
            str += "A livrer chez " + client.getNom() + " " + client.getPrenom() + "\n\n";
            str += "Contenues de la commande";
            for(Produit p : contenues.keySet()) {
                str += "\t" + p.getDenomination() + " : " + contenues.get(p) + "\n";
                poidT += p.getPoids();
                prixT += p.getPrix();
            }
            str += "\n\n";
            str += "Prix totale de la commande : " + prixT + "€\n";
            str += "Poid totale de la commande : " + poidT + "g\n";
            return str;
        }
    }
}
