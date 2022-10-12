package com.cos.blog.VO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id;

    @Column(nullable = false,length = 200)
    private String content;

    @ManyToOne // 여러개의 답변은 하나의 게시글에 존재할 수 있음
    @JoinColumn(name = "boardId")
    private Board board;

    @ManyToOne  // 여러개의 답변을 하나의 유저가 달 수 있음
    @JoinColumn(name="userId")
    private User user;

    @CreationTimestamp
    private Timestamp createDate;
}
