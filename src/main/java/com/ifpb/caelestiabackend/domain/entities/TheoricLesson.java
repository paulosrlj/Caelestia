package com.ifpb.caelestiabackend.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class TheoricLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 0, message = "O valor de Xp não pode ser menor que 0")
    @NotNull(message = "O valor de Xp não pode ser nulo!")
    private Long xpEarned;

    @NotNull(message = "O nome da lição não pode ser nulo!")
    @NotBlank(message = "O nome da lição não pode estar em branco!")
    private String lessonName;

    @NotNull(message = "A descrição não pode estar em branco!")
    private String description;

    @ManyToOne
    @JoinColumn(name = "module_id")
    @JsonIgnore
    private Module module;
}
