package fr.uga.l3miage.example.repository;

import fr.uga.l3miage.example.models.Partie;
import fr.uga.l3miage.example.models.Session;
import fr.uga.l3miage.example.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session,Long> {

    @Override
    List<Session> findAll();
    Optional<Session> findById(Long id);

    @Override
    void deleteById(Long id);
    List<Session> findAllByPartie(Partie partie);

    List<Session> findAllByParticipant(Utilisateur participant);


}
