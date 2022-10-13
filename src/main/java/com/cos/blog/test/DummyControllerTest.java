package com.cos.blog.test;

import com.cos.blog.repository.UserRepository;
import com.cos.blog.vo.RoleType;
import com.cos.blog.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyControllerTest {

    @Autowired // DummyControllerTest가 메모리에 뜰때 자동적으로 UserRepository도 메모리에 뜨게됨 == > 의존성 주입 (DI)
    private UserRepository userRepository;

    // http의 body에 username, password, email 데이터를 가지고 요청
    @PostMapping("/dummy/join")
    public String join(User user) {
        System.out.println("username = " + user.getUsername());
        System.out.println("password = " + user.getPassword());
        System.out.println("email = " + user.getEmail());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }
}
