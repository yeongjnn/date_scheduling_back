package com.example.date_scheduling.post.dto;

import com.example.date_scheduling.post.entity.Post;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
// 전체 게시물 목록 보여줄 때 필요한 객체
public class FindAllPostDto {

    private int count;  // 전체 게시물 갯수
    private List<PostDto> posts;

    public FindAllPostDto(List<Post> postList) {
        this.count = postList.size();
        this.convertDtoList(postList);
    }

    // List<Post>를 받으면 List<PostDto>로 변환하는 메서드
    public void convertDtoList(List<Post> postList) {
        List<PostDto> dtos = new ArrayList<>();

        for (Post post : postList) {
            dtos.add(new PostDto((post)));
        }
        this.posts = dtos;
    }
}
