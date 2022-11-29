package site.shkrr.whiskychuchu.app.rank.whisky.service;

import site.shkrr.whiskychuchu.app.rank.whisky.service.dto.CrawledWhiskyData;

import java.util.List;

public interface CrawlingService {
  public List<CrawledWhiskyData> crawling();
}
