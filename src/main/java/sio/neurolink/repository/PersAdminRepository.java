package sio.neurolink.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.NoResultException;
import sio.neurolink.entity.PersAdmin;

public class PersAdminRepository {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("neurolinkPU");

    public PersAdmin findByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM PersAdmin p WHERE p.email = :email", PersAdmin.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // Aucun persAdmin trouvé avec cet email
        } finally {
            em.close();
        }
    }
}