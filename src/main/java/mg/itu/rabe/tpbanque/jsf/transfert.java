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
        gestionnaireCompte.transferer(source, destination, this.montant);
        return "listeComptes?faces-redirect=true";
    }

}
