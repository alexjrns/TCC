/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import model.dao.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.entidade.Usuario;
import org.smarties.model.util.Excecoes;

/**
 *
 * @author Alex
 */
public class UsuarioDAO implements Serializable {
    public UsuarioDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            usuario = em.merge(usuario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuario findUsuario(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public Usuario findUsuarioCod(int codigo) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
            "SELECT u FROM Usuario u WHERE u.codigo = :pCodigo", Usuario.class)
            .setParameter("pCodigo", codigo)
            .getSingleResult();
        } finally {
            em.close();
        }
    }
    
    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public int getUsuarioCodigo() {
        return new ComumDAO(emf).getCodigoMaximo("Usuario");
    }
    
    public Usuario logar(String login, String senha){
        Usuario user;
        try{
            EntityManager em = getEntityManager();
            user = (Usuario) em.createNamedQuery("Usuario.findByLogin")
            .setParameter("lgn", login)
            .setParameter("sen", senha)
            .getSingleResult();
        }catch(Exception e){
            Excecoes.erro(e);
            return null;
        }
        return user;
    }
    
    public void salvarLogin(Usuario usuario) throws Exception{
        Usuario usu = this.findUsuarioCod(usuario.getCodigo());
        usu.setUltimologin(usuario.getUltimologin());
        this.edit(usu);
    }
    
    public void salvar(Usuario usuario) throws Exception{
        if((usuario.getCodigo() != 0) && (usuario.getCodigo() < this.getUsuarioCodigo())){
            Usuario usu = this.findUsuarioCod(usuario.getCodigo());
            usu.setLogin(usuario.getLogin());
            usu.setSenha(usuario.getSenha());
            usu.setNome(usuario.getNome());
            this.edit(usu);
        }
        else
            this.create(usuario);
    }
}
