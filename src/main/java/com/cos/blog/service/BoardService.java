package com.cos.blog.service;

import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.UserRepository;
import com.cos.blog.vo.Board;
import com.cos.blog.vo.RoleType;
import com.cos.blog.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 스프링이 컴포넌트 스캔을 통해서 Bean 에 등록을해줌 . IOC를 해준다 - >메모리에 띄어줌
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

  @Transactional
    public void write(Board board, User user){

      board.setCount(0);
      board.setUser(user);
      boardRepository.save(board);
  }
}
