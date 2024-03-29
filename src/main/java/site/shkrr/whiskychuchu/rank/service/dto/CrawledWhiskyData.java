package site.shkrr.whiskychuchu.rank.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import site.shkrr.whiskychuchu.rank.domain.whisky.Whisky;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class CrawledWhiskyData {
    private String whiskyImgUrl;
    private String whiskyName;
    private int whiskyPrice;
    private int whiskyPerPrice;
    private Long saleRank;
    //whisky 엔티티 생성시 매개변수로  리스트의 순서인 saleRank 주입 필요
    public Whisky toEntity(){
        return Whisky.builder()
                .name(whiskyName)
                .price(whiskyPrice)
                .perPrice(whiskyPerPrice)
                .saleRank(saleRank)
                .build();
    }
}
