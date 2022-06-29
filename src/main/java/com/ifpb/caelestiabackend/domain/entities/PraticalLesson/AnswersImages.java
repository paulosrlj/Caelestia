package com.ifpb.caelestiabackend.domain.entities.PraticalLesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswersImages {

    @URL(message = "A url da alternativa 1 é invalida")
    private String answer1ImageUrl;

    @URL(message = "A url da alternativa 2 é invalida")
    private String answer2ImageUrl;

    @URL(message = "A url da alternativa 3 é invalida")
    private String answer3ImageUrl;

    @URL(message = "A url da alternativa 4 é invalida")
    private String answer4ImageUrl;
}
