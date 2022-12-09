package site.shkrr.whiskychuchu.app.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import site.shkrr.whiskychuchu.app.security.entity.User;
import site.shkrr.whiskychuchu.app.security.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserLoginService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User findUser=userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("로그인 유저를 찾을수 없습니다."));
        return findUser;
    }

}
