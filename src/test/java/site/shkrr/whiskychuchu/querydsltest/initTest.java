package site.shkrr.whiskychuchu.querydsltest;

import com.querydsl.jpa.impl.JPAQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import site.shkrr.whiskychuchu.rank.domain.whisky.QWhisky;
import site.shkrr.whiskychuchu.rank.domain.whisky.Whisky;

import javax.persistence.EntityManager;
import java.util.List;
@Slf4j
@SpringBootTest
@Transactional
@Disabled
public class initTest {
    @Autowired
    EntityManager entityManager;

    @DisplayName("JPQL 로 조니로 시작되는 위스키를 가성비 순으로 3개만 추출하기")
    @Test
    void t1(){
      List<Whisky> whiskyList  =entityManager.createQuery("select w from Whisky w where w.name LIKE '%조니%' Order by w.perPrice asc ").setMaxResults(3).getResultList();
      log.info("JPQL=>");
      for(Whisky whisky:whiskyList){
          log.info(whisky.getName());
      }
    }

    @DisplayName("QueryDsl 로 조니로 시작되는 위스키를 가성비 순으로 3개만 추출하기")
    @Test
    void t2(){
        JPAQuery<Whisky> query=new JPAQuery<>(entityManager);
        QWhisky qWhisky=new QWhisky("w");
        List<Whisky> whiskyList=query.from(qWhisky)
                .where(qWhisky.name.contains("조니"))
                .orderBy(qWhisky.perPrice.asc())
                .limit(3).fetch();
        log.info("QueryDsl=>");
        for(Whisky whisky:whiskyList){
            log.info(whisky.getName());
        }

    }
}
