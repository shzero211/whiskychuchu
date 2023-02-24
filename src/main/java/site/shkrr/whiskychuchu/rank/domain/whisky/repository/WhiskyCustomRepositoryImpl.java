package site.shkrr.whiskychuchu.rank.domain.whisky.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.shkrr.whiskychuchu.rank.controller.dto.AdminOwnerWhisky;
import site.shkrr.whiskychuchu.rank.controller.dto.WhiskyMainRankDto;

import javax.persistence.EntityManager;
import java.util.List;

import static site.shkrr.whiskychuchu.app.rank.whisky.entity.QWhisky.whisky;
@Repository
@RequiredArgsConstructor
public class WhiskyCustomRepositoryImpl implements WhiskyCustomRepository {
    private final JPAQueryFactory queryFactory;
    private final EntityManager em;
    @Override
    public List<WhiskyMainRankDto> getWhiskyMainRankOrderBySaleRank(){
        return queryFactory.select(Projections.constructor(WhiskyMainRankDto.class,
                        whisky.id,
                        whisky.price,
                        whisky.perPrice,
                        whisky.name,
                        whisky.savedName,
                        whisky.flavorType.stringValue(),
                        whisky.savedPath,
                        whisky.ownerComment
                ))
                .from(whisky)
                .where(whisky.saleRank.ne(0L))
                .orderBy(whisky.saleRank.asc())
                .fetch();
    }

    @Override
    public List<WhiskyMainRankDto> getWhiskyMainRankOrderBy(String field) {
        if(field.equals("salerank")){
            return queryFactory.select(Projections.constructor(WhiskyMainRankDto.class,
                            whisky.id,
                            whisky.price,
                            whisky.perPrice,
                            whisky.name,
                            whisky.savedName,
                            whisky.flavorType.stringValue(),
                            whisky.savedPath,
                            whisky.ownerComment
                    ))
                    .from(whisky)
                    .where(whisky.saleRank.ne(0L))
                    .orderBy(whisky.saleRank.asc())
                    .fetch();
        }
        if(field.equals("ownerRank")){
            return queryFactory.select(Projections.constructor(WhiskyMainRankDto.class,
                            whisky.id,
                            whisky.price,
                            whisky.perPrice,
                            whisky.name,
                            whisky.savedName,
                            whisky.flavorType.stringValue(),
                            whisky.savedPath,
                            whisky.ownerComment
                    ))
                    .from(whisky)
                    .where(whisky.ownerRank.ne(0L))
                    .orderBy(whisky.ownerRank.asc())
                    .fetch();
        }
        if(field.equals("perprice")){
           return queryFactory.select(Projections.constructor(WhiskyMainRankDto.class,
                            whisky.id,
                            whisky.price,
                            whisky.perPrice,
                            whisky.name,
                            whisky.savedName,
                            whisky.flavorType.stringValue(),
                           whisky.savedPath,
                           whisky.ownerComment
                    ))
                    .from(whisky)
                   .where(whisky.saleRank.ne(0L))
                    .orderBy(whisky.perPrice.asc())
                    .fetch();
        }
        if(field.equals("smoky")){
            return queryFactory.select(Projections.constructor(WhiskyMainRankDto.class,
                            whisky.id,
                            whisky.price,
                            whisky.perPrice,
                            whisky.name,
                            whisky.savedName,
                            whisky.flavorType.stringValue(),
                            whisky.savedPath,
                            whisky.ownerComment
                    ))
                    .from(whisky)
                    .where(whisky.flavorType.stringValue().eq("스모키"))
                    .orderBy(whisky.perPrice.asc())
                    .fetch();
        }
        if(field.equals("sherry")){
            return queryFactory.select(Projections.constructor(WhiskyMainRankDto.class,
                            whisky.id,
                            whisky.price,
                            whisky.perPrice,
                            whisky.name,
                            whisky.savedName,
                            whisky.flavorType.stringValue(),
                            whisky.savedPath,
                            whisky.ownerComment
                    ))
                    .from(whisky)
                    .where(whisky.flavorType.stringValue().eq("쉐리"))
                    .orderBy(whisky.perPrice.asc())
                    .fetch();
        }
        if(field.equals("bourbon")){
            return queryFactory.select(Projections.constructor(WhiskyMainRankDto.class,
                            whisky.id,
                            whisky.price,
                            whisky.perPrice,
                            whisky.name,
                            whisky.savedName,
                            whisky.flavorType.stringValue(),
                            whisky.savedPath,
                            whisky.ownerComment
                    ))
                    .from(whisky)
                    .where(whisky.flavorType.stringValue().eq("버번"))
                    .orderBy(whisky.perPrice.asc())
                    .fetch();
        }
    return null;
    }

    /**
     *
     * 위스키 saleRank 초기화
     * */
    @Override
    public void resetAllSaleRank(){
       queryFactory.update(whisky).set(whisky.saleRank,0L).execute();
       em.clear();
    }

    @Override
    public List<AdminOwnerWhisky> getAdminOwnerWhisky() {
        return queryFactory.select(Projections.constructor(AdminOwnerWhisky.class,
                whisky.id,
                whisky.savedPath,
                whisky.name,
                whisky.ownerComment
        ))
                .from(whisky)
                .orderBy(whisky.saleRank.asc())
                .fetch();

    }

    @Override
    public void updateOwnerWhisky(Long id, Long ownerRank,String ownerComment) {
        queryFactory.update(whisky).set(whisky.ownerRank,ownerRank).set(whisky.ownerComment,ownerComment).where(whisky.id.eq(id)).execute();
        em.clear();
    }

    @Override
    public void resetAllOwnerRank() {
        queryFactory.update(whisky).set(whisky.ownerRank,0L).execute();
        em.clear();
    }
}
