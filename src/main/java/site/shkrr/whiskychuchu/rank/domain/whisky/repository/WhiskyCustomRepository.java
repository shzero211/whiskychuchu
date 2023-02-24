package site.shkrr.whiskychuchu.rank.domain.whisky.repository;

import site.shkrr.whiskychuchu.rank.controller.dto.AdminOwnerWhisky;
import site.shkrr.whiskychuchu.rank.controller.dto.WhiskyMainRankDto;

import java.util.List;

public interface WhiskyCustomRepository {
    List<WhiskyMainRankDto> getWhiskyMainRankOrderBySaleRank();

    List<WhiskyMainRankDto> getWhiskyMainRankOrderBy(String field);
    void resetAllSaleRank();

    List<AdminOwnerWhisky> getAdminOwnerWhisky();

    void updateOwnerWhisky(Long id, Long  ownerRank,String  ownerComment);

    void resetAllOwnerRank();
}
