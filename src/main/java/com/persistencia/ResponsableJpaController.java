package com.persistencia;

import com.miproyecto.model.Responsable;
import javax.persistence.*;
import java.util.List;

public class ResponsableJpaController {

    private EntityManagerFactory emf;

    public ResponsableJpaController() {
        this.emf = Persistence.createEntityManagerFactory("odontologicaPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Responsable responsable) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(responsable);
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

    public Responsable findResponsable(String dni) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Responsable.class, dni);
        } finally {
            em.close();
        }
    }

    public List<Responsable> findResponsableEntities() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Responsable> query = em.createQuery("SELECT r FROM Responsable r", Responsable.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void destroy(String dni) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Responsable responsable = em.getReference(Responsable.class, dni);
            em.remove(responsable);
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

    public void edit(Responsable responsable) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            responsable = em.merge(responsable);
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
