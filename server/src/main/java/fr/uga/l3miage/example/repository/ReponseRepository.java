package fr.uga.l3miage.example.repository;

import fr.uga.l3miage.example.models.Question;
import fr.uga.l3miage.example.models.Reponse;
import fr.uga.l3miage.example.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReponseRepository extends JpaRepository<Reponse,Long> {
    @Override
    List<Reponse> findAll();

    List<Reponse> findAllByLabel(final String label);
    @Override
    void deleteById(Long id);

    @Override
    Optional<Reponse> findById(Long id);

    List<Reponse> findAllByQuestion(Question question);

    List<Reponse> findAllBySession(Session session);


}
