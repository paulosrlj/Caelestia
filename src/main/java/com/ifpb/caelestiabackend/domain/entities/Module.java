package com.ifpb.caelestiabackend.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "module", orphanRemoval = false)
    @ToString.Exclude
    @Valid
    @JsonIgnore
    private Set<TheoricLesson> theoricLessons;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
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
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Module module = (Module) o;
        return id != null && Objects.equals(id, module.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
