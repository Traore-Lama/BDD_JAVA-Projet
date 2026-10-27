package sio.neurolink.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pers_admin")
public class PersAdmin extends User {

    @Column(nullable = false, length = 255)
    private String nom;

    @Column(nullable = false, length = 255)
    private String prenom;

    @Column(nullable = true)
    private Boolean bloque;

    // --- GETTERS ET SETTERS ---
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public Boolean getBloque() { return bloque; }
    public void setBloque(Boolean bloque) { this.bloque = bloque; }
}