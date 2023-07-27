package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.Session;
import fr.uga.l3miage.example.request.CreateSessionRequest;
import fr.uga.l3miage.example.response.SessionDTO;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    @Mapping(source = "partie.id", target = "partieId")
    SessionDTO toDto(Session entity);

    
    Session toEntity(CreateSessionRequest request);

    void mergeSession(@MappingTarget @NonNull Session session, SessionDTO sessionDTO);

}
