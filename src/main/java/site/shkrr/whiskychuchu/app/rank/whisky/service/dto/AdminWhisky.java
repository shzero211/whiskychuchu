package site.shkrr.whiskychuchu.app.rank.whisky.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.enums.CountryType;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.enums.IngredientType;

import javax.persistence.*;

@Getter
@Setter
@Builder
public class AdminWhisky {
    private Long id;

    private String name;

    private String imgUrl;

    private String countryType;

    private String  ingredientType;

}
