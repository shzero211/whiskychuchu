package site.shkrr.whiskychuchu.app.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import site.shkrr.whiskychuchu.app.security.entity.User;
import site.shkrr.whiskychuchu.app.security.entity.enums.Role;
import site.shkrr.whiskychuchu.app.security.repository.UserRepository;
import site.shkrr.whiskychuchu.app.security.service.UserService;

/*
* dev 환경에서  스프링 컨테이너 초기화 작업을 마치고
* 서비스에 필요한 초기화 코드를 실행해주는 기능을 함
* */
@Configuration
@Profile("dev")//application-dev.yml 설정에서 작동
public class DevInitRunner {
    @Bean
    public CommandLineRunner initData(UserService userService, PasswordEncoder passwordEncoder){
        return args -> {//익명 클래스 방식으로 run 메소드 구현
            User admin=User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .role(Role.ADMIN)
                    .build();
            userService.save(admin);
        };
    }
}
