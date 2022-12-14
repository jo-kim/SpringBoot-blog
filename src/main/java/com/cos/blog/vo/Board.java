package com.cos.blog.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto Increment
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content;  // 섬머노트 라이브러리 <html> 태그가 섞여서 디자인됨


    private int count; // 조회수

    @ManyToOne(fetch = FetchType.EAGER) // 연관관계를 지어줌 ( Many = board, One = user ) 한명의 user는 여러개의 게시글을 쓸수 있음음
    @JoinColumn(name = "userId")
    private User user; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

    @JsonIgnoreProperties("{board}")
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // mappedBy 연관관계의 주인이 아니다. (FK가 아니므로) DB에 컬럼을 만들지 마세요
    @OrderBy("id desc")
    private List<Reply> replys; // 하나의 개시글은 여러개의 댓글을 가짐

    @CreationTimestamp
    private Timestamp createDate;
}
