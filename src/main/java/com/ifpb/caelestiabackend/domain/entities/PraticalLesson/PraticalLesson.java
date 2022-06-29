package com.ifpb.caelestiabackend.domain.entities.PraticalLesson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PraticalLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do módulo não pode estar em branco!")
    private String lessonName;

    @NotBlank(message = "A descrição da lição não pode estar em braco")
    private String description;

    @Min(value = 0, message = "O valor de Xp não pode ser menor que 0")
    @NotNull(message = "O valor de Xp não pode ser nulo!")
    private Long xpEarned;

    @Embedded
    @Valid
    private Answers answers;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}