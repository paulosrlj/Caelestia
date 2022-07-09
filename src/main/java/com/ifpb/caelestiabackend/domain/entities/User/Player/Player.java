package com.ifpb.caelestiabackend.domain.entities.User.Player;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String passwordHash;

    private String name;

    private String nickname;

    private Long totalPoints;

    private Integer level;

    private Integer coins;

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
        Player player = (Player) o;
        return Objects.equals(id, player.id) && Objects.equals(email, player.email) && Objects.equals(passwordHash, player.passwordHash) && Objects.equals(name, player.name) && Objects.equals(nickname, player.nickname) && Objects.equals(totalPoints, player.totalPoints) && Objects.equals(level, player.level) && Objects.equals(coins, player.coins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, passwordHash, name, nickname, totalPoints, level, coins);
    }
}
