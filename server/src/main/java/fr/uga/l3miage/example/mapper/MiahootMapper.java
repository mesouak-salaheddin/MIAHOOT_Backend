package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.Miahoot;
import fr.uga.l3miage.example.request.CreateMiahootRequest;
import fr.uga.l3miage.example.response.MiahootDTO;

import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MiahootMapper {
    MiahootDTO toDto(Miahoot entity);
    Miahoot toEntity(CreateMiahootRequest request);
    void mergeTestEntity(@MappingTarget @NonNull Miahoot miahoot, MiahootDTO miahootDTO);
}
