package sio.neurolink.security;

import at.favre.lib.crypto.bcrypt.BCrypt;
import sio.neurolink.entity.Admin;
import sio.neurolink.entity.PersAdmin;
import sio.neurolink.entity.User;
import sio.neurolink.repository.AdminRepository;
import sio.neurolink.repository.PersAdminRepository;

public class AuthService {

    private AdminRepository adminRepository = new AdminRepository();
    private PersAdminRepository persAdminRepository = new PersAdminRepository();

    public User login(String email, String passwordSaisi) throws Exception {
        User utilisateur = null;

        // 1. On demande au repository Admin de chercher l'email
        utilisateur = adminRepository.findByEmail(email);

        // 2. Si on ne l'a pas trouvé, on demande au repository PersAdmin
        if (utilisateur == null) {
            utilisateur = persAdminRepository.findByEmail(email);
        }

        // 3. Si on n'a rien trouvé du tout
        if (utilisateur == null) {
            throw new Exception("Email ou mot de passe incorrect.");
        }

        // 4. On vérifie le mot de passe
        String hashEnBase = utilisateur.getPassword();
        if (hashEnBase == null || !BCrypt.verifyer().verify(passwordSaisi.toCharArray(), hashEnBase).verified) {
            throw new Exception("Email ou mot de passe incorrect.");
        }

        // 5. Si c'est un PersAdmin, on vérifie s'il est bloqué
        if (utilisateur instanceof PersAdmin) {
            PersAdmin pAdmin = (PersAdmin) utilisateur;
            if (pAdmin.getBloque() != null && pAdmin.getBloque() == true) {
                throw new Exception("Ce compte a été bloqué par l'administrateur.");
            }
        }

        // Si tout est bon, on retourne l'utilisateur
        return utilisateur;
    }
}