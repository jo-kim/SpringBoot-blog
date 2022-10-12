package com.cos.blog.test;

import lombok.*;

//@Getter
//@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    private int id;
    private String username;
    private String password;
    private String email;
}
