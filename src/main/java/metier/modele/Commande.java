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
    private Client client;
    //private Livreur livreur;
    
    public Commande() {
    }

    public Commande(Client client, Date dateDebut, Date dateFin, Map<Produit, Integer> contenues) {
        this.client = client;
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
        return "Commande{" + "id=" + id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", contenues=" + contenues + '}';
    }
}
