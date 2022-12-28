package com.example.date_scheduling.post.dto;

import com.example.date_scheduling.post.entity.Category;
import com.example.date_scheduling.post.entity.Post;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestPostDto {
    private Category category;
    private Post post;
}

