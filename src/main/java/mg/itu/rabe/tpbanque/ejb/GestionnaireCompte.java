/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.rabe.tpbanque.ejb;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;
import mg.itu.rabe.tpbanque.entities.CompteBancaire;

/**
 *
 * @author Princia Rabe
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "root", // nom et
        password = "secret", // mot de passe que vous avez donnés lors de la création de la base de données
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true"
        }
)

@Stateless
public class GestionnaireCompte {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    public void creerCompte(CompteBancaire c) {
        em.persist(c);
    }

    public List<CompteBancaire> getAllComptes() {
        String s = "select c from CompteBancaire as c";
        TypedQuery<CompteBancaire> query = em.createQuery(s, CompteBancaire.class);
        return query.getResultList();
    }
    
    public Long nbComptes(){
        String s = "select count(c) from CompteBancaire c";
        Query query =em.createQuery(s);
        return (Long) query.getSingleResult(); 
    }
    
     public void transferer(CompteBancaire source, CompteBancaire destination,int montant) {
        source.retirer(montant);
        destination.deposer(montant);
        update(source);
        update(destination);
    }

    public CompteBancaire update(CompteBancaire compteBancaire) {
        return em.merge(compteBancaire);
    }
    
    public CompteBancaire getCompte(Long Id){
        return em.find(CompteBancaire.class,Id); 
    }
}