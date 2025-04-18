package com.persistencia;

import com.miproyecto.model.Horario;

import javax.persistence.*;
import java.util.List;

public class HorarioJpaController {

    private EntityManagerFactory emf;

    public HorarioJpaController() {
        this.emf = Persistence.createEntityManagerFactory("odontologicaPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Horario horario) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(horario);
            em.getTransaction().commit();
        } catch (RuntimeException re) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw re;
        } finally {
            em.close();
        }
    }

    public void edit(Horario horario) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(horario);
            em.getTransaction().commit();
        } catch (RuntimeException re) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw re;
        } finally {
            em.close();
        }
    }

    public void destroy(int id) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Horario horario = em.getReference(Horario.class, id);
            em.remove(horario);
            em.getTransaction().commit();
        } catch (RuntimeException re) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw re;
        } finally {
            em.close();
        }
    }

    public Horario findHorario(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Horario.class, id);
        } finally {
            em.close();
        }
    }

    public List<Horario> findHorarioEntities() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Horario> query = em.createQuery("SELECT h FROM Horario h", Horario.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
