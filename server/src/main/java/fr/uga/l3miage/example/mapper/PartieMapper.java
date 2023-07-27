package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.Partie;
import fr.uga.l3miage.example.response.PartieDTO;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PartieMapper {
    @Mapping(source = "miahoot.id", target = "miahootId")
    @Mapping(source = "isActive", target = "isActive")
    PartieDTO toDto(Partie partie);

    Partie toEntity(Long miahootId);

    void mergePartie(@MappingTarget @NonNull Partie partie, PartieDTO partieDTO);

}
