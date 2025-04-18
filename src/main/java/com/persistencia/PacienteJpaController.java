package com.persistencia;

import com.miproyecto.model.Paciente;
import com.miproyecto.model.Responsable;
import com.miproyecto.model.Turno;
import javax.persistence.*;
import java.util.List;

public class PacienteJpaController {

    private EntityManagerFactory emf;

    public PacienteJpaController() {
        this.emf = Persistence.createEntityManagerFactory("odontologicaPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Paciente paciente) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(paciente);
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

    public Paciente findPaciente(String dni) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paciente.class, dni);
        } finally {
            em.close();
        }
    }

    public List<Paciente> findPacienteEntities() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Paciente> query = em.createQuery("SELECT p FROM Paciente p", Paciente.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void destroy(String dni) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Paciente paciente = em.getReference(Paciente.class, dni);
            em.remove(paciente);
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

    public void edit(Paciente paciente) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            paciente = em.merge(paciente);
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

    public List<Turno> findTurnosByPaciente(Paciente paciente) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Turno> query = em.createQuery("SELECT t FROM Turno t WHERE t.paciente = :paciente", Turno.class);
            query.setParameter("paciente", paciente);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Paciente> findPacientesByResponsable(Responsable responsable) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Paciente> query = em.createQuery("SELECT p FROM Paciente p WHERE p.responsable = :responsable", Paciente.class);
            query.setParameter("responsable", responsable);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
