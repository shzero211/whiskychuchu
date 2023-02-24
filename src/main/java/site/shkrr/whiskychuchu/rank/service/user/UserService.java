package site.shkrr.whiskychuchu.rank.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.shkrr.whiskychuchu.rank.domain.user.User;
import site.shkrr.whiskychuchu.rank.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void save(User user){
        userRepository.save(user);
    }
    public User findUserByUserName(String userName){
        return userRepository.findByUsername(userName).orElse(null);
    }
}
