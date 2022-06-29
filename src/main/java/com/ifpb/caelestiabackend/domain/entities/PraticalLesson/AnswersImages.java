package com.ifpb.caelestiabackend.domain.entities.PraticalLesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswersImages {
    @NotBlank(message = "A url  da alternativa 1 não pode estar em branco")
    private String answer1ImageUrl;

    @NotBlank(message = "A url  da alternativa 2 não pode estar em branco")
    private String answer2ImageUrl;

    @NotBlank(message = "A url  da alternativa 3 não pode estar em branco")
    private String answer3ImageUrl;

    @NotBlank(message = "A url  da alternativa 4 não pode estar em branco")
    private String answer4ImageUrl;
}
