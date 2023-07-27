package fr.uga.l3miage.example.repository;


import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Override
    List<Question> findAll();


    @Modifying
    @Transactional
    @Query("UPDATE Question q SET q.label = :label WHERE q.id = :id")
    void updateLabel(@Param("id") Long id, @Param("label") String label);

    @Override
    Optional<Question> findById(Long id);

    List<Question> findAllByMiahoot(Miahoot miahoot);

    @Override
    void deleteById(Long id);
}
