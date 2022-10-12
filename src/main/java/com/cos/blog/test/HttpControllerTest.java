package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

// 사용자가 요청을 할때 - >  HTML을 응답
// @Controller
// 사용자가 요청을 할때 - > 데이터를 응답해줌
@RestController
public class HttpControllerTest {

    private static final String TAG = "HttpControllerTest";

    @GetMapping("/http/lombok")
    public String lombokTest() {
        Member m = new Member(1,"abc","1234","email");
        System.out.println(TAG + "getter: " + m.getId());
        m.setId(5000);
        System.out.println(TAG +"setter: " + m.getId() );
        return "lomok test 완료";
    }
    @GetMapping("/http/get")
    public String getTest(Member m ) {
        return "get요청" + m.getId() + m.getUsername();
    }
    @PostMapping("/http/post")
    public String postTest(@RequestBody  Member m) {  // MessageConverter 가 일을함
        return "post요청" + m.getId()+"," + m.getUsername();
    }
    @PutMapping("/http/put")
    public String putTest() {
        return "put요청";
    }
    @DeleteMapping("http/delete")
    public String deleteTest() {
        return "delete요청";
    }
}
