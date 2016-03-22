/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.persistence.EntityManager;
import metier.modele.Commande;

/**
 *
 * @author qvecchio
 */
public class CommandeDao {
    public void create(Commande cmd) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            em.persist(cmd);
        }
        catch(Exception e) {
            throw e;
        }
    }
    
    public Commande update(Commande cmd) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            cmd = em.merge(cmd);
        }
        catch(Exception e){
            throw e;
        }
        return cmd;
    }
    
    public Commande findById(Long id) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Commande cmd = null;
        try {
            cmd = em.find(Commande.class, id);
        }
        catch(Exception e) {
            throw e;
        }
        return cmd;
    }
    
    /*public Commande findByIdAndLivreur(Long id, Livreur l) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        Commande cmd = null;
        try {
            cmd = em.find(Commande.class, id);
            if(cmd.getLivreur() != l)
                return null;
        }
        catch(Exception e) {
            throw e;
        }
        return cmd;
    }*/
}
