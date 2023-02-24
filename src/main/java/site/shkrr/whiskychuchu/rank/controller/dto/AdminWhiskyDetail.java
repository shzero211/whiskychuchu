package site.shkrr.whiskychuchu.rank.controller.dto;

import lombok.*;

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
