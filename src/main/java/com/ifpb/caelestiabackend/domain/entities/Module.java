package com.ifpb.caelestiabackend.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ifpb.caelestiabackend.domain.entities.Achievement.Achievement;
import com.ifpb.caelestiabackend.domain.entities.PraticalLesson.PraticalLesson;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do módulo não pode estar em branco!")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "module")
    @ToString.Exclude
    @Valid
    @JsonIgnore
    private Set<TheoricLesson> theoricLessons;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "module")
    @ToString.Exclude
    @Valid
    @JsonManagedReference
    private Set<PraticalLesson> praticalLessons;

    @OneToOne(mappedBy = "module", cascade = CascadeType.ALL)
    private Achievement achievement;

    @CreationTimestamp
    @EqualsAndHashCode.Exclude
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @EqualsAndHashCode.Exclude
    private LocalDateTime updatedAt;

    public void addTheoricLesson(TheoricLesson theoricLesson) {
        List<TheoricLesson> theoricLessonsList;

        if (getTheoricLessons() == null) {
            theoricLessonsList = new ArrayList<>();
        } else {
            theoricLessonsList = new ArrayList<>(getTheoricLessons());
        }

        theoricLessonsList.add(theoricLesson);
        setTheoricLessons(new HashSet<>(theoricLessonsList));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Module module = (Module) o;
        return Objects.equals(id, module.id) && Objects.equals(name, module.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
