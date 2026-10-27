package sio.neurolink.entity;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 180)
    private String email;

    @Column(nullable = true)
    private String password;

    @Column(columnDefinition = "LONGTEXT")
    private String roles;

    @Column(name = "premiere_connexion", nullable = false)
    private Boolean premiereConnexion;

    @Column(name = "code_premiere_connexion", length = 6)
    private String codePremiereConnexion;

    // --- GETTERS ET SETTERS ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRoles() { return roles; }
    public void setRoles(String roles) { this.roles = roles; }
    public Boolean getPremiereConnexion() { return premiereConnexion; }
    public void setPremiereConnexion(Boolean premiereConnexion) { this.premiereConnexion = premiereConnexion; }
    public String getCodePremiereConnexion() { return codePremiereConnexion; }
    public void setCodePremiereConnexion(String codePremiereConnexion) { this.codePremiereConnexion = codePremiereConnexion; }
}