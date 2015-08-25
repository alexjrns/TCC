/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Alex
 */
public class ComumDAO {
    private EntityManagerFactory emf = null;

    public ComumDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public int getCodigoMaximo(String tabela) {
        EntityManager em = getEntityManager();
        int cod = 0;
        try{
            cod = em.createQuery("SELECT MAX(t.codigo) FROM " + tabela + " t", Integer.class).getSingleResult();
        }catch(Exception e){
        }
        return cod + 1;
    }

    public void executaLimpeza(String tabela){
        EntityManager em = getEntityManager();
        em.createQuery("VACUUM FULL FREEZE VERBOSE ANALYZE " + tabela);
    }
}
