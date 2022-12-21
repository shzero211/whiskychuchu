package site.shkrr.whiskychuchu.app.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.shkrr.whiskychuchu.app.security.entity.User;
import site.shkrr.whiskychuchu.app.security.repository.UserRepository;

import javax.persistence.EntityNotFoundException;

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
