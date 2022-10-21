package com.cos.blog.test;

import com.cos.blog.repository.UserRepository;
import com.cos.blog.vo.RoleType;
import com.cos.blog.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

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

    // {id} 주소로 파라미터를 전달 받을 수 있음
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        // user/4를 찾으면 내가 데이터베이스에서 못찾아오게되면 user가 null 이 될 것 아냐?
        // 그럼 return null이 되자나,,
        // Optional로 너의 User 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return 해!

        // 람다식식
//       User user = userRepository.findById(id).orElseThrow(()->{
//            return new IllegalArgumentException("해당 사용자가 없습니다.");
//        });

        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 사용자가 없습니다.");
            }
        });
        // 요청 : 웹브라우저
        // user 객체 : 자바 오브젝트
        // 변환 ( 웹브라우저가 이해할 수 있는 데이터) - > JSON
        // 스프링부트 = MessageConverter 가 응답시에 자동 작동
        // 만약에 자바 오브젝트를 리턴하게되면 MessageConverter가 Jackson라이브러리를 호출해서
        // user 오브젝트를 json으로 변환해서 브라우저에게 던져준다.
        return user;
    }

    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    // 한페이지당 2건의 데이터를 리턴받아 볼 예정
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return users;
    }

    // save 함수는 id를 전달하지 않으면 insert 해준다.
    // save 함수는 id를 전달하고, 해당 id에 대한 데이터가 있으면 update 해주고
    // save 함수는 id를 전달하고, 해당 id에 대한 데이터가 없으면 insert 해준다.

    @Transactional // 함수 종료시에 자동 커밋이 됨
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {  // Json 데이터를 요청 => Java Object(MessageConverter의 Jackson 라이브러리가) 변환해서 받아줌
        System.out.println("id = " + id);
        System.out.println("user.getPassword() = " + user.getPassword());
        System.out.println("user.getEmail() = " + user.getEmail());

        User users = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });
        users.setPassword(user.getPassword());
        users.setEmail(user.getEmail());

//        userRepository.save(users);

        // 더티 체킹
        return user;
    }

    @DeleteMapping("/dummy/user/{id}")
    public String deleteUser(@PathVariable int id){

        try {
            userRepository.deleteById(id);

        } catch (Exception e) {
            return "삭제에 실패하였습니다.";
        }

        return "삭제되었습니다. id : "+ id;
    }
}
