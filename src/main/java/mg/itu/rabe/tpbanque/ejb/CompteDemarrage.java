/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.rabe.tpbanque.ejb;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import mg.itu.rabe.tpbanque.entities.CompteBancaire;

/**
 *
 * @author Princia Rabe
 */
@Singleton
@Startup
public class CompteDemarrage {

    @EJB
    private GestionnaireCompte g;

    @PostConstruct
    public void init() {
        if (g.nbComptes() == 0) {
            CompteBancaire c1 = new CompteBancaire("John Lennon", 150000);
            CompteBancaire c2 = new CompteBancaire("Paul McCartney", 950000);
            CompteBancaire c3 = new CompteBancaire("Ringo Starr", 20000);
            CompteBancaire c4 = new CompteBancaire("Georges Harrisson", 100000);
            g.creerCompte(c1);
            g.creerCompte(c2);
            g.creerCompte(c3);
            g.creerCompte(c4);
        } else {
        }
    }
}
