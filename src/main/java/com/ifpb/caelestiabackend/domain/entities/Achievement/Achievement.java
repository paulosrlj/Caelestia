package com.ifpb.caelestiabackend.domain.entities.Achievement;

import com.ifpb.caelestiabackend.domain.entities.Module;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String achievementName;

    private String description;

    private BigDecimal baseBonusPercentage;

    @Enumerated(EnumType.STRING)
    private BonusType bonusType;

    @OneToOne
    @JoinColumn(name = "module_id", referencedColumnName = "id", nullable = false)
    private Module module;
}
