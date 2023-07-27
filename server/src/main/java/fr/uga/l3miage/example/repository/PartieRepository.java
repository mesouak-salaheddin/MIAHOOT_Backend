package fr.uga.l3miage.example.repository;

import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.models.Partie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PartieRepository extends JpaRepository<Partie,Long> {
    @Override
    List<Partie> findAll();
    Optional<Partie> findById(Long id);

    List<Partie> findAllByMiahoot(Miahoot miahoot);

}
