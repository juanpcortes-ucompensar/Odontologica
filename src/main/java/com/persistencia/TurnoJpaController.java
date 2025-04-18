package com.persistencia;

import com.miproyecto.model.Odontologo;
import com.miproyecto.model.Paciente;
import com.miproyecto.model.Turno;
import javax.persistence.*;
import java.util.List;

public class TurnoJpaController {

    private EntityManagerFactory emf;

    public TurnoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("odontologicaPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Turno turno) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(turno);
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

    public Turno findTurno(int idTurno) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Turno.class, idTurno);
        } finally {
            em.close();
        }
    }

    public List<Turno> findTurnoEntities() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Turno> query = em.createQuery("SELECT t FROM Turno t", Turno.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void destroy(int idTurno) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Turno turno = em.getReference(Turno.class, idTurno);
            em.remove(turno);
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

    public void edit(Turno turno) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            turno = em.merge(turno);
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

    public List<Turno> findTurnosByOdontologo(Odontologo odontologo) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Turno> query = em.createQuery("SELECT t FROM Turno t WHERE t.odontologo = :odontologo", Turno.class);
            query.setParameter("odontologo", odontologo);
            return query.getResultList();
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
}
