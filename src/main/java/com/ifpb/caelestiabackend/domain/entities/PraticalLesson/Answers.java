package com.ifpb.caelestiabackend.domain.entities.PraticalLesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answers {

    @NotBlank(message = "A descrição da alternativa 1 não pode estar em branco")
    private String answer1;

    @NotBlank(message = "A descrição da alternativa 2 não pode estar em branco")
    private String answer2;

    @NotBlank(message = "A descrição da alternativa 3 não pode estar em branco")
    private String answer3;

    @NotBlank(message = "A descrição da alternativa 4 não pode estar em branco")
    private String answer4;

    @NotNull(message = "A resposta da questão não pode estar em branco")
    private Long correctAnswer;

    @Embedded
    @Valid
    private AnswersImages answersImages;
}
