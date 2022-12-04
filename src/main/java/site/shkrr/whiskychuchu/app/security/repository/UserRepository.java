package site.shkrr.whiskychuchu.app.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.shkrr.whiskychuchu.app.security.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);
}
