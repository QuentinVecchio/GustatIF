/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import metier.modele.Livreur;

/**
 *
 * @author Hugues
 */
public class LivreurDao {
    public void create(Livreur livreur) throws Throwable {
           EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            em.persist(livreur);
        }
        catch(Exception e) {
            throw e;
        }
}
    public Livreur update(Livreur livreur) throws Throwable {
        EntityManager em = JpaUtil.obtenirEntityManager();
        try {
            livreur = em.merge(livreur);
        }
        catch(Exception e){
            throw e;
        }
        return livreur;
    }

    /**
     *
     * @param isFreeCnd
     * @param poidMaxCnd
     * @return
     */
    public List<Livreur> find(boolean isFreeCnd, int poidMaxCnd ) {
        EntityManager em = JpaUtil.obtenirEntityManager();
        String conditaion = "";
        if (isFreeCnd) conditaion+= "l.IsFree=True AND";
        conditaion+= "l.poidMax<"+poidMaxCnd;  
        List<Livreur> toReturn;
        try {
            toReturn = em.createQuery("Select l from Livreur l Where"+conditaion).getResultList();
        }
        catch(Exception e) {
            throw e;
        }
        return toReturn;
    }
}

