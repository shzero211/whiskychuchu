package site.shkrr.whiskychuchu.app.rank.whisky.entity.dto;

import lombok.*;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.enums.CountryType;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.enums.IngredientType;

import javax.persistence.*;
@Getter
@Setter
@Builder
public class AdminWhiskyDetail {

    private Long id;

    private int price;

    private int perPrice;

    private Long saleRank;

    private String name;

    private String savedPath;

    private String savedName;

    private String countryType;

    private String ingredientType;

    private String flavorType;
}
