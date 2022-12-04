package site.shkrr.whiskychuchu.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig  {
    //예전과 다르게 SecurityFilterChain 을 Bean 으로 등록해서 설정해준다.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**","/js/**","/fonts/**","/images/**").permitAll()// static 폴더의 정적 리소스들이 들어있는 디렉토리 허용(이거안하면 화면에 적용이 안됨)
                .antMatchers("/admin/login").anonymous()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/admin/login")//로그인 페이지 URL 지정
                .loginProcessingUrl("/admin/login")//로그인 인증처리 필터를 호출하는 URL 지정
                .usernameParameter("username")//html 에서 받아올 username 파라미터 지정
                .passwordParameter("password")//html 에서 받아올 password 파라미터 지정
                .defaultSuccessUrl("/admin/main")
                .failureUrl("/admin/login")
        ;
        return http.build();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
