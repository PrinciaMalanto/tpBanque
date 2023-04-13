/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.rabe.tpbanque.jsf;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

import mg.itu.rabe.tpbanque.ejb.GestionnaireCompte;
import mg.itu.rabe.tpbanque.entities.CompteBancaire;
import mg.itu.rabe.tpbanque.jsf.util.Util;

/**
 *
 * @author Princia Rabe
 */
@Named(value = "updateBean")
@ViewScoped
public class updateBean implements Serializable {

    private Long id;
    private CompteBancaire compte;

    @EJB
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of updateBean
     */
    public updateBean() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void loadCompte() {
        this.compte = gestionnaireCompte.getCompte(id);
    }

    public String updateInfo() {
        compte=gestionnaireCompte.update(compte);
        Util.addFlashInfoMessage(" Compte de " + compte.getNom() + " modifié avec succès!");
        return "listeComptes?faces-redirect=true";
    }

}
