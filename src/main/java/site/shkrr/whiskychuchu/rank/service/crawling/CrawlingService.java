package site.shkrr.whiskychuchu.rank.service.crawling;

import site.shkrr.whiskychuchu.rank.service.dto.CrawledWhiskyData;

import java.util.List;

public interface CrawlingService {
  public List<CrawledWhiskyData> crawling();
}
