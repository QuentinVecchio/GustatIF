/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.service;

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
}
