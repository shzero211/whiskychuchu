package site.shkrr.whiskychuchu.app.rank.whisky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.shkrr.whiskychuchu.app.rank.whisky.entity.Whisky;

import java.util.Optional;

public interface WhiskyRepository extends JpaRepository<Whisky,Long>,WhiskyCustomRepository {
    Optional<Whisky> findByName(String name);
}
