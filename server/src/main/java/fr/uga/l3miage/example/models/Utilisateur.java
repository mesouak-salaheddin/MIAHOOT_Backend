package fr.uga.l3miage.example.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String firebaseId;


    @ManyToMany(cascade = CascadeType.ALL)
    List<Miahoot> miahootsConcus;

    @ManyToMany(cascade = CascadeType.ALL)
    List<Miahoot> miahootsPresentes;

    @OneToMany(mappedBy = "participant",cascade = CascadeType.ALL)
    List<Session> sessions;

    @OneToMany(mappedBy = "presentateur",cascade = CascadeType.ALL)
    private List<Partie> parties;

    public void addMiahootConcu(Miahoot miahoot){
        this.miahootsConcus.add(miahoot);
    }

    public void supprMiahootConcu(Miahoot miahoot) {this.miahootsConcus.remove(miahoot);}

    public void supprMiahootPresente(Miahoot miahoot) {this.miahootsPresentes.remove((miahoot));}

    public void addMiahootPresente(Miahoot miahoot){
        this.miahootsPresentes.add(miahoot);
    }

    public Utilisateur() {
    }

    public Utilisateur(Long id, String userName, String firebaseId) {
        this.id = id;
        this.username = userName;
        this.firebaseId = firebaseId;
    }

}

