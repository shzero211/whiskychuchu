package site.shkrr.whiskychuchu.app.rank.whisky.service.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CrawledWhiskyData {
    private String whiskyName;
    private String whiskyPrice;
    private String whiskyPerPrice;
    public CrawledWhiskyData(String whiskyName,String whiskyPrice, String whiskyPerPrice){
        this.whiskyName=whiskyName;
        this.whiskyPrice=whiskyPrice;
        this.whiskyPerPrice=whiskyPerPrice;
    }
}
