package sio.neurolink.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.NoResultException;
import sio.neurolink.entity.Admin;

public class AdminRepository {

    // On récupère la connexion définie dans persistence.xml
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("neurolinkPU");

    public Admin findByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Admin a WHERE a.email = :email", Admin.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // Aucun admin trouvé avec cet email
        } finally {
            em.close();
        }
    }
}