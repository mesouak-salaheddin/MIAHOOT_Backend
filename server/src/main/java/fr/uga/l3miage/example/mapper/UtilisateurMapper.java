package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.Utilisateur;
import fr.uga.l3miage.example.request.CreateUtilisateurRequest;
import fr.uga.l3miage.example.response.UtilisateurDTO;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {

    UtilisateurDTO toDto(Utilisateur entity);

    Utilisateur toEntity(CreateUtilisateurRequest request);

    void mergeTestEntity(@MappingTarget @NonNull Utilisateur utilisateur, UtilisateurDTO utilisateurDTO);
}
