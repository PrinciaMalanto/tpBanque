/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.rabe.tpbanque.jsf;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.validation.constraints.PositiveOrZero;
import mg.itu.rabe.tpbanque.ejb.GestionnaireCompte;
import mg.itu.rabe.tpbanque.entities.CompteBancaire;
import mg.itu.rabe.tpbanque.jsf.util.Util;

/**
 *
 * @author Princia Rabe
 */
@Named(value = "ajoutCompte")
@RequestScoped
public class AjoutCompte {
    private String nom;
    @PositiveOrZero
    private int solde;
    
    @EJB
    private GestionnaireCompte gestionnaireCompte;
    
    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
    
    public String action(){      
        boolean erreur = false;
        if (erreur) { // en cas d'erreur, rester sur la même page
            return null;
        }
        CompteBancaire c = new CompteBancaire(this.nom,this.solde);
        gestionnaireCompte.creerCompte(c);
        Util.addFlashInfoMessage("Nouveau compte ajouté avec succès!");
        return "listeComptes?faces-redirect=true";
    }
    
}
