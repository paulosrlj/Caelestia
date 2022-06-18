package com.ifpb.caelestiabackend.domain.entities;

import lombok.*;

import javax.persistence.*;
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

    private Long xpEarned;

    private String lessonName;

    private String description;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;
}
