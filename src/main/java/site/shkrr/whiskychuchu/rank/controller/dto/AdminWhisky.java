package site.shkrr.whiskychuchu.rank.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdminWhisky {
    private Long id;

    private String name;

    private String savedName;

    private String countryType;

    private String  ingredientType;

    private String  flavorType;

    private Long saleRank;

    private String savedPath;
}
