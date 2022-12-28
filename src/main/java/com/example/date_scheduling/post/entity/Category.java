package com.example.date_scheduling.post.entity;

import lombok.*;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private String cID; //카테고리를 구별
    private String area; //지역을 선택
    private String address; //첫번째 상세 구역을 선택


}
