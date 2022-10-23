package com.cos.blog.repository;

import com.cos.blog.vo.Board;
import com.cos.blog.vo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board,Integer> {

}
