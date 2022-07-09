package com.ifpb.caelestiabackend.domain.entities.User.Player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
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
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(regexp = ".+[@].+[\\.].+")
    private String email;

    @NotBlank(message = "A senha não pode estar em branco")
    @NotNull(message = "A senha não pode ser nula")
    private String passwordHash;

    @NotBlank(message = "O nome não pode estar em branco")
    @NotNull(message = "O nome não pode ser nulo")
    private String name;

    @NotBlank(message = "O apelido não pode estar em branco")
    @NotNull(message = "O apelido não pode ser nulo")
    private String nickname;

    private Long totalPoints = 0L;

    private Integer level = 1;

    private Integer coins = 0;

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
