package fr.uga.l3miage.example.mapper;

import fr.uga.l3miage.example.models.Question;
import fr.uga.l3miage.example.models.Reponse;
import fr.uga.l3miage.example.response.QuestionDTO;
import fr.uga.l3miage.example.response.QuestionReponseDTO;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    @Mapping(source = "miahoot.id", target = "miahootId")
    @Mapping(target = "reponses", expression = "java(mapToNoIdReponse(entity.getReponses()))")
    QuestionDTO toDto(Question entity);

    //crée une liste de réponse sans id
    default List<QuestionReponseDTO> mapToNoIdReponse(List<Reponse> reponses) {
        return reponses.stream()
                .map(reponse -> QuestionReponseDTO.builder()
                        .id(reponse.getId())
                        .label(reponse.getLabel())
                        .estCorrecte(reponse.isEstCorrecte())
                        .build())
                .collect(Collectors.toList());
    }

    Question toEntity(String label);

    void mergeQuestion(@MappingTarget @NonNull Question question, QuestionDTO questionDTO);
}
