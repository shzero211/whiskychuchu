package site.shkrr.whiskychuchu.rank.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import site.shkrr.whiskychuchu.rank.domain.user.User;
import site.shkrr.whiskychuchu.rank.domain.user.repository.UserRepository;

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
