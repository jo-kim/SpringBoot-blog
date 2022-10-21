package com.cos.blog.service;

import com.cos.blog.repository.UserRepository;
import com.cos.blog.vo.RoleType;
import com.cos.blog.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @Transactional(readOnly = true) // Select 할때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 ( 정합성 )
//    public User login(User user) {
//        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//    }
}
