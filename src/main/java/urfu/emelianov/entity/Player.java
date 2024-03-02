package urfu.emelianov.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import urfu.emelianov.dto.Country;
import urfu.emelianov.dto.Gender;

import java.time.LocalDate;
import java.util.Objects;

/**
 *  * Класс {@code Player} представляет JPA-сущность игрока.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Имя не должно быть пустым")
    @Size(max = 25, message = "Имя должно содержать не более {max} символов")
    private String name;

    @NotBlank(message = "Фамилия не должна быть пустой")
    @Size(max = 25, message = "Фамилия должна содержать не более {max} символов")
    private String surname;

    @NotNull(message = "Пол не должен быть пустым")
    @Enumerated(EnumType.STRING)
    private Gender sex;

    @NotNull(message = "Дата рождения не должна быть пустой")
    @Past(message = "Дата рождения должна быть прошлой")
    private LocalDate birthday;

//    @NotNull(message = "Команда не должна быть пустой")
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @NotNull(message = "Страна не должна быть пустой")
    @Enumerated(EnumType.STRING)
    private Country country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
