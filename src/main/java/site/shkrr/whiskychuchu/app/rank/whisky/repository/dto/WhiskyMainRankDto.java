package site.shkrr.whiskychuchu.app.rank.whisky.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.enums.CountryType;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.enums.IngredientType;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class WhiskyMainRankDto {
    private Long id;

    private int price;

    private int perPrice;

    private String name;

    private String savedName;

}
