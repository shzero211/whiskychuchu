package site.shkrr.whiskychuchu.rank.domain.whiskycharacter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.shkrr.whiskychuchu.rank.domain.character.Characters;
import site.shkrr.whiskychuchu.rank.domain.whisky.Whisky;

import javax.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WhiskyCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "whisky_id")
    private Whisky whisky;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "characters_id")
    private Characters characters;
}
