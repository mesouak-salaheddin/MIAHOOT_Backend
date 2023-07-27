package fr.uga.l3miage.example.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String description;

    @ManyToOne
    @JoinColumn(name = "partie.id")
    private Partie partie;

    @ManyToOne
    private Utilisateur participant;

    @OneToMany(mappedBy = "session",cascade = CascadeType.ALL)
    private List<Reponse> reponses;


}
