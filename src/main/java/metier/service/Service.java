/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;
import dao.ClientDao;
import dao.CommandeDao;
import dao.DroneDao;
import dao.JpaUtil;
import dao.LivreurCyclisteDao;
import dao.LivreurDao;
import dao.ProduitDao;
import dao.RestaurantDao;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metier.modele.Client;
import metier.modele.Commande;
import metier.modele.Drone;
import metier.modele.Livreur;
import metier.modele.LivreurCycliste;
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
    private final DroneDao droneDao = new DroneDao();
    private final LivreurDao livreurDao = new LivreurDao();
    private final LivreurCyclisteDao livreurCyclisteDao = new LivreurCyclisteDao();
    private final ServiceTechnique serviceTechnique = new ServiceTechnique();
    
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
    
    public boolean createClient(Client client) {
        JpaUtil.creerEntityManager();
        try {
            JpaUtil.ouvrirTransaction();
            clientDao.create(client);
            JpaUtil.validerTransaction();
            String contenu = "Bonjour " + client.getPseudo();
            contenu += "\n\nVotre compte a bien été créé";
            contenu += "\n\nVos informations : \n" + client.getNom() + " " + client.getPrenom() + "\n";
            contenu += client.getAdresse();
            contenu += "\n" + client.getMail();
            serviceTechnique.sendMail(client.getMail(),"Confirmation création du compte ", contenu);
            JpaUtil.fermerEntityManager();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        } catch (Throwable ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        JpaUtil.fermerEntityManager();
        return false;
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
     
    public Client connection(String pseudo, String password) {
        JpaUtil.creerEntityManager();
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
    
    public boolean createCommande(Commande cmd) {
        JpaUtil.creerEntityManager();
        try {
            //Chercher un livreur
            Float poidT = new Float(0);
            for(Produit p : cmd.getContenues().keySet())
                poidT += cmd.getContenues().get(p);
            List<Livreur> list = livreurDao.find(true, poidT);
            Livreur l = serviceTechnique.findBestLivreur(list, cmd.getClient().getLatitude(), cmd.getClient().getLongitude());
            if(l == null) {
                JpaUtil.fermerEntityManager();
                return false;
            } else {
                cmd.setLivreur(l);
                //Envoyer un mail
                JpaUtil.ouvrirTransaction();
                commandeDao.create(cmd);
                JpaUtil.validerTransaction();
                serviceTechnique.sendMail(l.getMail(),"Livraison commande " + cmd.getId(), cmd.toString());
                JpaUtil.fermerEntityManager();
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        } catch (Throwable ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        JpaUtil.fermerEntityManager();
        return false;
    }
    
    public void createLivreurCycliste(LivreurCycliste livreur) {
        JpaUtil.creerEntityManager();
        try {
            JpaUtil.ouvrirTransaction();
            livreurCyclisteDao.create(livreur);
            JpaUtil.validerTransaction();
        } catch (Exception e) {
            System.out.println(e);
        } catch (Throwable ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        JpaUtil.fermerEntityManager();
    }
    
    public void createDrone(Drone drone) {
        JpaUtil.creerEntityManager();
        try {
            JpaUtil.ouvrirTransaction();
            droneDao.create(drone);
            JpaUtil.validerTransaction();
        } catch (Exception e) {
            System.out.println(e);
        } catch (Throwable ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        JpaUtil.fermerEntityManager();
    }
    
    public Livreur findLivreurById(Long id) {
        JpaUtil.creerEntityManager();
        try {
            return livreurDao.findById(id);
        } catch (Exception e) {
            System.out.println(e);
        } catch (Throwable ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        JpaUtil.fermerEntityManager();
        return null;
    }
    
    public Commande valideCommande(String mail, Long num) {
        JpaUtil.creerEntityManager();
        try {
            JpaUtil.ouvrirTransaction();
            Commande cmd = commandeDao.findById(num);
            if(cmd != null) {
                if(cmd.getLivreur().getMail().equals(mail) == false) {
                    return null;
                } else {
                    cmd.setDateFin(new Date(System.currentTimeMillis()));
                }
            }
            return cmd;
        } catch (Exception e) {
            System.out.println(e);
        } catch (Throwable ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        JpaUtil.fermerEntityManager();
        return null;
    }
    
    public List<Commande> findAllCommande() {
        JpaUtil.creerEntityManager();
        try {
            return commandeDao.findAll();
        } catch (Exception e) {
            System.out.println(e);
        } catch (Throwable ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        JpaUtil.fermerEntityManager();
        return null;
    }
    
    public List<Commande> findAllCommandeEnCours() {
        JpaUtil.creerEntityManager();
        try {
            return commandeDao.findAllEnCours();
        } catch (Exception e) {
            System.out.println(e);
        } catch (Throwable ex) {
            Logger.getLogger(Service.class.getName()).log(Level.SEVERE, null, ex);
        }
        JpaUtil.fermerEntityManager();
        return null;
    }
}
