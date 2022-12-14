package site.shkrr.whiskychuchu.app.rank.whisky.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.shkrr.whiskychuchu.app.rank.whisky.repository.dto.WhiskyMainRankDto;

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
                        whisky.savedName
                ))
                .from(whisky)
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
                            whisky.savedName
                    ))
                    .from(whisky)
                    .orderBy(whisky.saleRank.asc())
                    .fetch();
        }
        if(field.equals("perprice")){
           return queryFactory.select(Projections.constructor(WhiskyMainRankDto.class,
                            whisky.id,
                            whisky.price,
                            whisky.perPrice,
                            whisky.name,
                            whisky.savedName
                    ))
                    .from(whisky)
                    .orderBy(whisky.perPrice.asc())
                    .fetch();
        }
    return null;
    }
}
