package com.cos.blog.test;

import com.cos.blog.repository.BoardRepository;
import com.cos.blog.vo.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReplyControllerTest {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/test/board/{id}")
    public Board getBoard(@PathVariable int id) {
        return boardRepository.findById(id).get(); // jackson 라이브러리( 오브젝트를 json으로 리턴) = > 모델의 getter 를 호출
    }
}
