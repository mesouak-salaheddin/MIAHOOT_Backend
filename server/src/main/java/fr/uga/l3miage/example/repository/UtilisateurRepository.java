package fr.uga.l3miage.example.repository;

import fr.uga.l3miage.example.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
    @Override
    List<Utilisateur> findAll();
    Optional<Utilisateur> findById(Long Id);
    Optional<Utilisateur> findByFirebaseId(String firebaseId);

}
