package com.example.date_scheduling.post.dto;

import com.example.date_scheduling.post.entity.Category;
import com.example.date_scheduling.post.entity.Post;
import lombok.*;

import java.util.List;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private int categoryCnt;
    private List<Category> categories;

    public CategoryDto(List<Category> categoryList) {
        this.categoryCnt = categoryList.size();
        this.categories = categoryList;
    }
}
