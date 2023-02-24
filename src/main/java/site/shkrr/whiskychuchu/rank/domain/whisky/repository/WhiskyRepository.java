package site.shkrr.whiskychuchu.rank.domain.whisky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.shkrr.whiskychuchu.rank.domain.whisky.Whisky;

import java.util.Optional;

public interface WhiskyRepository extends JpaRepository<Whisky,Long>,WhiskyCustomRepository {
    Optional<Whisky> findByName(String name);
}
