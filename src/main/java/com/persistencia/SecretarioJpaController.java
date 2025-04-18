package com.persistencia;

import com.miproyecto.model.Secretario;
import javax.persistence.*;
import java.util.List;

public class SecretarioJpaController {

    private EntityManagerFactory emf;

    public SecretarioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("odontologicaPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Secretario secretario) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(secretario);
            em.getTransaction().commit();
        } catch (RuntimeException re) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw re;
        } finally {
            em.close();
        }
    }

    public Secretario findSecretario(String dni) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Secretario.class, dni);
        } finally {
            em.close();
        }
    }

    public List<Secretario> findSecretarioEntities() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Secretario> query = em.createQuery("SELECT s FROM Secretario s", Secretario.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void destroy(String dni) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Secretario secretario = em.getReference(Secretario.class, dni);
            em.remove(secretario);
            em.getTransaction().commit();
        } catch (RuntimeException re) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw re;
        } finally {
            em.close();
        }
    }

    public void edit(Secretario secretario) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            secretario = em.merge(secretario);
            em.getTransaction().commit();
        } catch (RuntimeException re) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw re;
        } finally {
            em.close();
        }
    }
}
