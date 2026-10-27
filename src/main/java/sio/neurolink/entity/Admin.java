package sio.neurolink.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "admin")
public class Admin extends User {

    @Column(nullable = false, length = 255)
    private String nom;

    @Column(nullable = false, length = 255)
    private String prenom;

    @Column(name = "derniere_synchronisation")
    private LocalDateTime derniereSynchronisation;

    // --- GETTERS ET SETTERS ---
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public LocalDateTime getDerniereSynchronisation() { return derniereSynchronisation; }
    public void setDerniereSynchronisation(LocalDateTime derniereSynchronisation) { this.derniereSynchronisation = derniereSynchronisation; }
}