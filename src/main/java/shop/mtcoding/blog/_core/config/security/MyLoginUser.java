package shop.mtcoding.blog._core.config.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import shop.mtcoding.blog.user.User;

import java.util.Collection;

// 세션에 저장되는 오브젝트
//userDetails 타입을 만들기 위해 만듬
@Getter
@RequiredArgsConstructor
public class MyLoginUser implements UserDetails {

    private final User user ; //DB에서 패스워드 조회하기 위해 의존성주입하려고

    @Override
    public String getPassword() {
        return user.getPassword(); //DB의 비밀번호
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
