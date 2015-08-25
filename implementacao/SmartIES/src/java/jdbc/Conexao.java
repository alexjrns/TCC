/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Alex
 */
public class Conexao {
    private static EntityManagerFactory emf;

    public static EntityManagerFactory createEntityManagerFactory(){
        if (emf == null) {
           emf = Persistence.createEntityManagerFactory("smarties");
        }
        return emf;
    }

    public static EntityManager createEntityManager() {
        if (emf == null) {
            emf = createEntityManagerFactory();
        }
        return emf.createEntityManager();
    }

    public static void closeFactory() {
        createEntityManager().close();
        emf = null;
    }
}
