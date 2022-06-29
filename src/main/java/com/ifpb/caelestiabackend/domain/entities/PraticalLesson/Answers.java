package com.ifpb.caelestiabackend.domain.entities.PraticalLesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
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

    @Column(nullable = false)
    @NotBlank(message = "A descrição da alternativa 1 não pode estar em branco")
    private String answer1;

    @Column(nullable = false)
    @NotBlank(message = "A descrição da alternativa 2 não pode estar em branco")
    private String answer2;

    @Column(nullable = false)
    @NotBlank(message = "A descrição da alternativa 3 não pode estar em branco")
    private String answer3;

    @Column(nullable = false)
    @NotBlank(message = "A descrição da alternativa 4 não pode estar em branco")
    private String answer4;

    @Column(nullable = false)
    @NotNull(message = "A resposta da questão não pode estar em branco")
    private Long correctAnswer;

    @Embedded
    @Valid
    private AnswersImages answersImages;
}
