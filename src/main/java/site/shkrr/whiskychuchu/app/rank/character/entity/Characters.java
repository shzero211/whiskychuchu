package site.shkrr.whiskychuchu.app.rank.character.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.shkrr.whiskychuchu.app.rank.whiskycharacter.entity.WhiskyCharacter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Characters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "characters")
    private List<WhiskyCharacter> whiskyCharacterList;
}
