package site.shkrr.whiskychuchu.app.rank.whisky.service;

import site.shkrr.whiskychuchu.app.rank.whisky.entity.dto.CrawledWhiskyData;

import java.util.List;
import java.util.Set;

public interface CrawlingService {
  public List<CrawledWhiskyData> crawling();
}
