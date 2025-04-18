package com.persistencia;

import com.miproyecto.model.Odontologo;
import com.miproyecto.model.Usuario;

import javax.persistence.*;
import java.util.List;

public class OdontologoJpaController {

    private EntityManagerFactory emf;

    public OdontologoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("odontologicaPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Odontologo odontologo) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(odontologo);
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

    public void edit(Odontologo odontologo) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            odontologo = em.merge(odontologo);
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

    public void destroy(String dni) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Odontologo odontologo = em.getReference(Odontologo.class, dni);
            em.remove(odontologo);
            em.getTransaction().commit();
        } catch (EntityNotFoundException enfe) {
            throw new Exception("El odontólogo con DNI " + dni + " no existe.", enfe);
        } finally {
            em.close();
        }
    }

    public Odontologo findOdontologo(String dni) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Odontologo.class, dni);
        } finally {
            em.close();
        }
    }

    public List<Odontologo> findOdontologoEntities() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Odontologo> query = em.createQuery("SELECT o FROM Odontologo o", Odontologo.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Odontologo findOdontologoByUsuario(Usuario usuario) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Odontologo> query = em.createQuery("SELECT o FROM Odontologo o WHERE o.usuario = :usuario", Odontologo.class);
            query.setParameter("usuario", usuario);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}
