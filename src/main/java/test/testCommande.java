/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import metier.modele.Client;
import metier.modele.Commande;
import metier.modele.Livreur;
import metier.modele.Produit;
import metier.modele.Restaurant;
import metier.service.Service;

/**
 *
 * @author quentinvecchio
 */
public class testCommande {
    public static void main(String[] args) {
        Service service = new Service();
        Client c = service.Connection("qvecchio", "qvecchio");
        Restaurant r = service.findRestaurantById(new Long(1));
        Map<Produit, Integer> contenues = new HashMap<Produit, Integer>() {};
        System.out.println(c);
        System.out.println(r);
        contenues.put(r.getProduits().get(0), 1);
        Commande c1 = new Commande(c, r, null, null, null, contenues);
        c1.setDateDebut(new Date(System.currentTimeMillis()));
        c1.setDateFin(null);
        service.createCommande(c1);
    }
}
