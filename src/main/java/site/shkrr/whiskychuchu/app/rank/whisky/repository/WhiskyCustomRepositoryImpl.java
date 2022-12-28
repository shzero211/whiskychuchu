package site.shkrr.whiskychuchu.app.rank.whisky.repository;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.dto.WhiskyMainRankDto;

import java.util.List;

import static site.shkrr.whiskychuchu.app.rank.whisky.entity.QWhisky.whisky;
@Repository
@RequiredArgsConstructor
public class WhiskyCustomRepositoryImpl implements WhiskyCustomRepository {
    private final JPAQueryFactory queryFactory;
    @Override
    public List<WhiskyMainRankDto> getWhiskyMainRankOrderBySaleRank(){
        return queryFactory.select(Projections.constructor(WhiskyMainRankDto.class,
                        whisky.id,
                        whisky.price,
                        whisky.perPrice,
                        whisky.name,
                        whisky.savedName,
                        whisky.flavorType.stringValue(),
                        whisky.savedPath
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
                            whisky.savedPath
                    ))
                    .from(whisky)
                    .where(whisky.saleRank.ne(0L))
                    .orderBy(whisky.saleRank.asc())
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
                           whisky.savedPath
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
                            whisky.savedPath
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
                            whisky.savedPath
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
                            whisky.savedPath
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
    }
}
