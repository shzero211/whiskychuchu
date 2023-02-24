package site.shkrr.whiskychuchu.rank.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.shkrr.whiskychuchu.rank.domain.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);
}
