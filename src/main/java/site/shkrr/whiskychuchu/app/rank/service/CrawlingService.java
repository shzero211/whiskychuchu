package site.shkrr.whiskychuchu.app.rank.service;

import site.shkrr.whiskychuchu.app.rank.service.dto.CrawledWhiskyData;

import java.util.List;

public interface CrawlingService {
  public List<CrawledWhiskyData> crawling();
}
