/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;
import dao.ClientDao;
import dao.CommandeDao;
import dao.JpaUtil;
import dao.ProduitDao;
import dao.RestaurantDao;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.modele.Client;
import metier.modele.Commande;
import metier.modele.Produit;
import metier.modele.Restaurant;

/**
 *
 * @author qvecchio
 */
public class Service {
    private final ClientDao clientDao = new ClientDao();
    private final RestaurantDao restaurantDao = new RestaurantDao();
    private final ProduitDao produitDao = new ProduitDao();
    private final CommandeDao commandeDao = new CommandeDao();
    
    public boolean clientExist(String pseudo) {
        JpaUtil.creerEntityManager();
        try {
            return (clientDao.findByPseudo(pseudo) != null);
        } catch (Exception e) {
            System.out.println(e);
        } catch (Throwable ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        JpaUtil.fermerEntityManager();
        return false;
    }
    
    public void createClient(Client client) {
        JpaUtil.creerEntityManager();
        try {
            JpaUtil.ouvrirTransaction();
            clientDao.create(client);
            JpaUtil.validerTransaction();
        } catch (Exception e) {
            System.out.println(e);
        } catch (Throwable ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        JpaUtil.fermerEntityManager();
    }
    
    public Client updateClient(Client client) {
        JpaUtil.creerEntityManager();
        try {
            return clientDao.update(client);
        } catch (Exception e) {
            System.out.println(e);
        } catch (Throwable ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        JpaUtil.fermerEntityManager();
        return null;
    }
     
    public Client Connection(String pseudo, String password) {
        try {
            return clientDao.findClientByPseudoAndPassword(pseudo, password);
        } catch (Exception e) {
            System.out.println(e);
        } catch (Throwable ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        JpaUtil.fermerEntityManager();
        return null;
    }
    
    public List<Client> findAllClient() {
        JpaUtil.creerEntityManager();
        try {
            return clientDao.findAll();
        } catch (Exception e) {
            System.out.println(e);
        } catch (Throwable ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        JpaUtil.fermerEntityManager();
        return null;
    }

    public Restaurant findRestaurantById(Long id) {
        JpaUtil.creerEntityManager();
        try {
            return restaurantDao.findById(id);
        } catch (Exception e) {
            System.out.println(e);
        } catch (Throwable ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        JpaUtil.fermerEntityManager();
        return null;
    }
       
    public List<Restaurant> findAllRestaurant() {
        JpaUtil.creerEntityManager();
        try {
            return restaurantDao.findAll();
        } catch (Exception e) {
            System.out.println(e);
        } catch (Throwable ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        JpaUtil.fermerEntityManager();
        return null;
    }
    
    public Produit findProduitById(Long id) {
        JpaUtil.creerEntityManager();
        try {
            return produitDao.findById(id);
        } catch (Exception e) {
            System.out.println(e);
        } catch (Throwable ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        JpaUtil.fermerEntityManager();
        return null;
    }
    
    public List<Produit> findAllProduit() {
        JpaUtil.creerEntityManager();
        try {
            return produitDao.findAll();
        } catch (Exception e) {
            System.out.println(e);
        } catch (Throwable ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        JpaUtil.fermerEntityManager();
        return null;
    }
    
    public void createCommande(Commande cmd) {
        JpaUtil.creerEntityManager();
        try {
            //Chercher un livreur
            //Envoyer un mail
            JpaUtil.ouvrirTransaction();
            commandeDao.create(cmd);
            JpaUtil.validerTransaction();
        } catch (Exception e) {
            System.out.println(e);
        } catch (Throwable ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        JpaUtil.fermerEntityManager();
    }
}
