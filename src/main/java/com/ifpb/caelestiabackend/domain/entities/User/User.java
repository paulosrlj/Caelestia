package com.ifpb.caelestiabackend.domain.entities.User;

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
public class User {

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
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(passwordHash, user.passwordHash) && Objects.equals(name, user.name) && Objects.equals(nickname, user.nickname) && Objects.equals(totalPoints, user.totalPoints) && Objects.equals(level, user.level) && Objects.equals(coins, user.coins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, passwordHash, name, nickname, totalPoints, level, coins);
    }
}
