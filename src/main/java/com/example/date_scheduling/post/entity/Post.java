package com.example.date_scheduling.post.entity;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Setter @Getter @ToString
//@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor   // 전체 필드 초기화 생성자
// 역할 : 하나의 게시물 데이터의 집합 객체
// DB에 그대로 들어갈 객체
public class Post {

    private String postId;      // 게시물을 식별하는 번호
    private String userId;      // 게시물 등록한 회원 식별자
    private String title;       // 게시물 제목
    private String content;     // 게시물 내용텍스트)
    private String image;       // 사진
    private Date regDate;       // 게시물 등록 시간

    private String cID;

    public Post() {
        this.postId = UUID.randomUUID().toString();
    }

    // 유저가 게시물 등록할 때 직접 넣어야 할 데이터
    public Post (String title, String content, String image, String cID) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.cID = cID;
    }

}
