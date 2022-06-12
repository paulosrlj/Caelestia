package com.ifpb.caelestiabackend.domain.entities;

import lombok.*;

import javax.persistence.*;

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
    private Module moduleId;
}
