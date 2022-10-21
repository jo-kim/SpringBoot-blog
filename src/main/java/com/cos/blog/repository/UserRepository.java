package com.cos.blog.repository;

import com.cos.blog.vo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

// 자동으로 빈 등록이 됨 (@Repository) 생략 가능
public interface UserRepository extends JpaRepository<User,Integer> {  // User 테이블의 PK 는 integer

    // JPA Naming 전략
    // SELECT * FROM user WHERE username = ?1 And password ?2
//    User findByUsernameAndPassword(String username, String password);

//    @Query(value="SELECT * FROM user WHERE username = ?1 And password ?2",nativeQuery = true)
//    User login(String username, String password);

    // SELECT * FROM user WHERE username =  1?;
    Optional<User> findByUsername(String username);
}
