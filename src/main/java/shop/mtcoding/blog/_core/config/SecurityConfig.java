package shop.mtcoding.blog._core.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;


@Configuration //컨퍼넌트 스캔.
public class SecurityConfig {

    @Bean // 시큐리티 컨텍스트 홀더에 값이 생김.
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();

    }


    //보안을 위한 필터
    @Bean  // 현재는 모든 걸 무효화.
    SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.csrf(c -> c.disable());

        // 람다식으로 만들어짐
        http.authorizeHttpRequests(a -> {  // "/board/**" /board/ 뒤에 모든 주소는 인증이 필요함 //anyRequest().permitAll() 앞의 주소가 아닌 주소는 허용해줌
            a.requestMatchers(RegexRequestMatcher.regexMatcher("/board/\\d+")).permitAll()// /board/숫자는 제외하기 위해
                    .requestMatchers("/user/**","/board/**").authenticated().anyRequest().permitAll(); // 인증이 되지 않으면 못들어가는 페이지

        });



        http.formLogin(f -> {  // 로그인 페이지로 리다이렉션
            f.loginPage("/loginForm").loginProcessingUrl("/login").defaultSuccessUrl("/").failureUrl("/loginForm"); //
        } );


        return http.build(); // 아무 것도 없는걸 등록함. 하나씩 막아야됨.

    }
}
