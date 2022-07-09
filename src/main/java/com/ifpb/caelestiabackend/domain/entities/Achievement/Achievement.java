package com.ifpb.caelestiabackend.domain.entities.Achievement;

import com.ifpb.caelestiabackend.domain.entities.Module;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "O nome da conquista não pode estar em branco!")
    private String achievementName;

    @Column(nullable = false)
    @NotBlank(message = "A descrição da conquista não pode estar em branco!")
    private String description;

    @Column(nullable = false)
    @Min(value = 0, message = "O valor bônus da conquista não pode ser igual ou menor que 0")
    @NotNull(message = "O valor bônus da conquista não pode ser nulo!")
    private BigDecimal baseBonusPercentage;

    @Column(nullable = false)
    @NotNull(message = "O tipo do bônus não pode estar em branco")
    @Enumerated(EnumType.STRING)
    private BonusType bonusType;

    @Column(nullable = false)
    @URL(message = "A url da imagem da conquista é inválida")
    private String urlImage;

    @OneToOne
    @JoinColumn(name = "module_id", referencedColumnName = "id", nullable = false)
    @NotNull(message = "O módulo não pode ser nulo")
    private Module module;


    @CreationTimestamp
    @EqualsAndHashCode.Exclude
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @EqualsAndHashCode.Exclude
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Achievement that = (Achievement) o;
        return Objects.equals(id, that.id) && Objects.equals(achievementName, that.achievementName) && Objects.equals(description, that.description) && Objects.equals(baseBonusPercentage, that.baseBonusPercentage) && bonusType == that.bonusType && Objects.equals(urlImage, that.urlImage) && Objects.equals(module, that.module);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, achievementName, description, baseBonusPercentage, bonusType, urlImage, module);
    }
}
