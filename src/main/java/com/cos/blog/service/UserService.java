package com.cos.blog.service;

import com.cos.blog.repository.UserRepository;
import com.cos.blog.vo.RoleType;
import com.cos.blog.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 스프링이 컴포넌트 스캔을 통해서 Bean 에 등록을해줌 . IOC를 해준다 - >메모리에 띄어줌
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;


    // 전체가 하나의 트랜잭션으로 묶는 어노테이션
    @Transactional
    public void join(User user) {
        String rawPw = user.getPassword();  // 1234 원문
        String encPw = encoder.encode(rawPw);
        user.setPassword(encPw);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

    @Transactional
    public void update(User user) {
        // 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User오브젝트를 수정
        // select를 해서 User 오브젝트를 DB로부터 가져오는 이유는 영속화를 하기위해서!!
        // 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려줌

        User persistence = userRepository.findById(user.getId()).orElseThrow(()->{
            return new IllegalArgumentException("회원 찾기 실패");
        });

        // validate 체크 oAuth값이 없으면 수정 가능
        if (persistence.getOauth() == null || persistence.getOauth().equals("")) {
            String rawPw = user.getPassword();
            String enPw = encoder.encode(rawPw);

            persistence.setPassword(enPw);
            persistence.setEmail(user.getEmail());
        }


        // 회원수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit 이 자동으로됨
        // 커밋이 자동으로 된다는건
        //  ㄴ 영속화된 persistence 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌
    }

    @Transactional(readOnly = true)
    public User findUser(String username) {
        User user = userRepository.findByUsername(username).orElseGet(()-> {
            return new User();
        });
        return user;
    }

//    @Transactional(readOnly = true) // Select 할때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 ( 정합성 )
//    public User login(User user) {
//        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//    }
}
