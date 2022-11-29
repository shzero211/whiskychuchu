package site.shkrr.whiskychuchu.app.rank.whisky.service;

import site.shkrr.whiskychuchu.app.rank.whisky.service.dto.CrawledWhiskyData;

import java.util.List;
import java.util.Set;

public interface CrawlingService {
  public Set<CrawledWhiskyData> crawling();
}
