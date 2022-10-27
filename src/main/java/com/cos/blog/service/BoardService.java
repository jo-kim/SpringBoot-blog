package com.cos.blog.service;

import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.UserRepository;
import com.cos.blog.vo.Board;
import com.cos.blog.vo.RoleType;
import com.cos.blog.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 스프링이 컴포넌트 스캔을 통해서 Bean 에 등록을해줌 . IOC를 해준다 - >메모리에 띄어줌
@Service
@RequiredArgsConstructor
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

  @Transactional
    public void write(Board board, User user){

      board.setCount(0);
      board.setUser(user);
      boardRepository.save(board);
  }

    @Transactional(readOnly = true)
    public Page<Board> boardList(Pageable pageable) {

        return boardRepository.findAll(pageable);
    }
    @Transactional(readOnly = true)
    public Board boardDetail(int id) {
      return boardRepository.findById(id)
              .orElseThrow(()->{
                  return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
              });
    }

    @Transactional
    public void deleteBoard(int id) {
             boardRepository.deleteById(id);
    }

    @Transactional
    public void updateBoard(int id, Board requestBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
                }); // 영속화 완료
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        // 해당 함수로 종료시( Service가 종료될 때) 트랜잭션이 종료됨. 이때 더티 체킹 - 자동업데이트가 됨. db flush
    }
}
