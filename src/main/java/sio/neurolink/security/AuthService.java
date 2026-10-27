package sio.neurolink.security;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.persistence.*;
import sio.neurolink.entity.Admin;
import sio.neurolink.entity.PersAdmin;
import sio.neurolink.entity.User;
import java.util.List;

public class AuthService {

    // On initialise la connexion à la base de données une seule fois
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("neurolinkPU");

    public User login(String email, String passwordSaisi) throws Exception {
        EntityManager em = emf.createEntityManager();
        User utilisateur = null;

        // 1. On cherche dans la table Admin
        List<Admin> listeAdmins = em.createQuery("SELECT a FROM Admin a WHERE a.email = :email", Admin.class)
                .setParameter("email", email)
                .getResultList();

        if (!listeAdmins.isEmpty()) {
            // Si on a trouvé quelqu'un dans Admin
            utilisateur = listeAdmins.get(0);
        } else {
            // 2. Sinon, on cherche dans la table PersAdmin
            List<PersAdmin> listePersAdmins = em.createQuery("SELECT p FROM PersAdmin p WHERE p.email = :email", PersAdmin.class)
                    .setParameter("email", email)
                    .getResultList();

            if (!listePersAdmins.isEmpty()) {
                utilisateur = listePersAdmins.get(0);
            }
        }

        // 3. Si on n'a rien trouvé du tout
        if (utilisateur == null) {
            em.close();
            throw new Exception("Email ou mot de passe incorrect.");
        }

        // 4. On vérifie le mot de passe
        String hashEnBase = utilisateur.getPassword();
        if (hashEnBase == null || !BCrypt.verifyer().verify(passwordSaisi.toCharArray(), hashEnBase).verified) {
            em.close();
            throw new Exception("Email ou mot de passe incorrect.");
        }

        // 5. Si c'est un PersAdmin, on vérifie s'il est bloqué
        if (utilisateur instanceof PersAdmin) {
            PersAdmin pAdmin = (PersAdmin) utilisateur;
            if (pAdmin.getBloque() != null && pAdmin.getBloque() == true) {
                em.close();
                throw new Exception("Ce compte a été bloqué par l'administrateur.");
            }
        }

        // 6. Si tout est bon, on ferme et on retourne l'utilisateur
        em.close();
        return utilisateur;
    }
}