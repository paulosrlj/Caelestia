package com.ifpb.caelestiabackend.domain.entities.PraticalLesson;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ifpb.caelestiabackend.domain.entities.Module;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PraticalLesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "O nome do módulo não pode estar em branco!")
    private String lessonName;

    @Column(nullable = false)
    @NotBlank(message = "A descrição da lição não pode estar em braco")
    private String description;

    @Column(nullable = false)
    @Min(value = 0, message = "O valor de Xp não pode ser menor que 0")
    @NotNull(message = "O valor de Xp não pode ser nulo!")
    private Long xpEarned;

    @Embedded
    @Valid
    @NotNull(message = "As respostas não podem ser nulas")
    private Answers answers;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    @NotNull(message = "O módulo não pode ser nulo")
    @JsonBackReference
    private Module module;

    @CreationTimestamp
    @EqualsAndHashCode.Exclude
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @EqualsAndHashCode.Exclude
    private LocalDateTime updatedAt;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        PraticalLesson that = (PraticalLesson) o;
//        return id != null && Objects.equals(id, that.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PraticalLesson that = (PraticalLesson) o;
        return Objects.equals(id, that.id) && Objects.equals(lessonName, that.lessonName) && Objects.equals(description, that.description) && Objects.equals(xpEarned, that.xpEarned) && Objects.equals(answers, that.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lessonName, description, xpEarned, answers);
    }
}
