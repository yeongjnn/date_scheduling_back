package com.example.date_scheduling.user.dto;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    private String username; //회원의 닉네임
    private String loginId; //회원가입시 입력하는 id
    private String email;
    private String password;
}
