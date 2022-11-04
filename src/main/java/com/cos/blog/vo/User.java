package com.cos.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

// ORM -> Java(뿐아니라 다른언어들도) Object-> 테이블로 매핑해주는 기술
@Entity // User 클래스가 MySQL 에 테이블이 생성된다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert  // insert시 null 인 필드를 제외시켜준다.
public class User {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id; // auto_increment

    @Column(nullable = false, length = 100, unique = true)
    private String username; // 아이디

    @Column(nullable = false, length = 100) // 해쉬(비밀번호 암호화) 예쩡
    private String password;

    @Column(nullable = false,length = 50)
    private String email;

//    @ColumnDefault("user")
    // DB에는 RoleType이라는게 없으므로 어노테이션 추가해줘야함
    @Enumerated(EnumType.STRING)
    private RoleType role; // Enum (어떤 데이터의 도메인(범위가 정해졌다) 을 만들어줄 수 있음) // Admin , User, Manager

    private String oauth; // kakao, google,

    @CreationTimestamp // 시간이 자동으로 입력됨
    private Timestamp createDate;

}
