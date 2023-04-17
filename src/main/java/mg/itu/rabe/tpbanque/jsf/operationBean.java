/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.rabe.tpbanque.jsf;

import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import mg.itu.rabe.tpbanque.ejb.GestionnaireCompte;
import mg.itu.rabe.tpbanque.entities.CompteBancaire;
import mg.itu.rabe.tpbanque.entities.OperationBancaire;

/**
 *
 * @author Princia Rabe
 */
@Named(value = "operationBean")
@ViewScoped
public class operationBean implements Serializable {

    private Long id;
    private CompteBancaire compte;
    @EJB
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of operationBean
     */
    public operationBean() {
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
        compte = gestionnaireCompte.getCompte(id);
    }

    public List<OperationBancaire> getAllOperations() {
        return compte.getOperations();
    }

}
