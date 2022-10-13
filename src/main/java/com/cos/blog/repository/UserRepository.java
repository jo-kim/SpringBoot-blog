package com.cos.blog.repository;

import com.cos.blog.vo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// 자동으로 빈 등록이 됨 (@Repository) 생략 가능
public interface UserRepository extends JpaRepository<User,Integer> {  // User 테이블의 PK 는 integer


    @Override
    List<User> findAll();
}
