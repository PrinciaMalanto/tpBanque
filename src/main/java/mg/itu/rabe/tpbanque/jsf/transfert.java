/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.rabe.tpbanque.jsf;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import mg.itu.rabe.tpbanque.ejb.GestionnaireCompte;
import mg.itu.rabe.tpbanque.entities.CompteBancaire;
import mg.itu.rabe.tpbanque.jsf.util.Util;

/**
 *
 * @author Princia Rabe
 */
@Named(value = "transfert")
@RequestScoped
public class transfert {

    private Long idSource;
    private Long idDestination;
    private int montant;

    @EJB
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of transfert
     */
    public transfert() {
    }

    public Long getIdSource() {
        return idSource;
    }

    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }

    public Long getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(Long idDestination) {
        this.idDestination = idDestination;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String transfertArgent() {
        CompteBancaire source = gestionnaireCompte.getCompte(this.idSource);
        CompteBancaire destination = gestionnaireCompte.getCompte(this.idDestination);
        boolean erreur = false;
        if (source == null) {
            // Message d'erreur associé au composant source ; form:source est l'id client
            // si l'id du formulaire est "form" et l'id du champ de saisie de l'id de la source est "source"
            // dans la page JSF qui lance le transfert.
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
            erreur = true;
        } else {
            if (source.getSolde() < montant) { // à compléter pour le cas où le solde du compte source est insuffisant...
                Util.messageErreur("Le montant est trop grand. Le solde du compte source est insuffisant.");
                erreur = true;
            }
        }
        if (erreur) { // en cas d'erreur, rester sur la même page
            return null;
        }
        gestionnaireCompte.transferer(source, destination, montant);
        // Message de succès ; addFlash à cause de la redirection.
        Util.addFlashInfoMessage("Transfert correctement effectué");

        return "listeComptes?faces-redirect=true";
    }

}
