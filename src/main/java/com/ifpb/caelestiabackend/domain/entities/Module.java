package com.ifpb.caelestiabackend.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
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

    private String name;

    private Integer qtyLessons = 0;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "module", orphanRemoval = false)
    @ToString.Exclude
    @JsonIgnore
    private Set<TheoricLesson> theoricLessons;

    public void addTheoricLesson(TheoricLesson theoricLesson) {
        List<TheoricLesson> theoricLessonsList;

        if (getTheoricLessons() == null) {
            theoricLessonsList = new ArrayList<>();
        } else {
            theoricLessonsList = new ArrayList<>(getTheoricLessons());
        }

        this.qtyLessons += 1;
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
