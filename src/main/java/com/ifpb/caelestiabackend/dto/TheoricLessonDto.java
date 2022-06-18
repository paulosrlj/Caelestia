package com.ifpb.caelestiabackend.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TheoricLessonDto {

    @Min(value = 0, message = "O valor de Xp não pode ser menor que 0")
    @NotNull(message = "O valor de Xp não pode ser nulo!")
    private Long xpEarned;

    @NotNull(message = "O nome da lição não pode ser nulo!")
    @NotBlank(message = "O nome da lição não pode estar em branco!")
    private String lessonName;

    @NotNull(message = "A descrição não pode estar em branco!")
    private String description;

}
