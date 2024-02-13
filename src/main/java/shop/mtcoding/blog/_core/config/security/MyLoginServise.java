package shop.mtcoding.blog._core.config.security;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.user.UserRepository;
// 때려지는 조건 post , /login, x-www-form-urlencoded 로 와야야됨, 키 값이 반드시 username,password 여야 함
//login
@RequiredArgsConstructor
@Service // 컨포넌트 스캔. UserDetailsService 를 @Service가 무력화. 내가 구혀한 loadUserByUsername 가 실행됨
public class MyLoginServise implements UserDetailsService {
    private final UserRepository userRepository ;
    private final HttpSession session;
    // UserDetails 를 만들어서 리턴해주면 됨.
    @Override   //username 만 넘어감.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername:"+username);
        User user = userRepository.findByUsername(username);

        if(user==null){
            System.out.println("user는 null");
            return null ;  //fail
        }else { //머스태치 접근을 위해 세션을 따로 만듬
            System.out.println("user를 찾음");
            session.setAttribute("sessionUser",user); // 머스태치용
            return new MyLoginUser(user) ;  // 리턴을 하는 이유는 세션에 저장하려고.
        }                               //세션에 넣기 직전에 getPassword 메서드를 호출함. 패스워드랑 유저 객체랑 비교해서 알아서 패스워드를 체크해줌
                                        //세션에 저장되는게 아니라 SecurityContextHolder 에 저장
    } //ioc 컨테이너에 UserDetailsService가 뜸/

}
