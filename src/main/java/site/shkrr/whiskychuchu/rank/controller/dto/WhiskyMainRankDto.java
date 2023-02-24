package site.shkrr.whiskychuchu.rank.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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

    private String flavorType;

    private String savedPath;
    private String ownerComment;

}
