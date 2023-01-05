package com.example.date_scheduling.mydatecourse.dto;

import com.example.date_scheduling.mydatecourse.entity.MyDateCourse;
import com.example.date_scheduling.post.entity.Post;
import com.example.date_scheduling.user.entity.UserEntity;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MyCourseDto {

//    private String username;
    private String courseId;
    private String postId;
    private Date meetingDate;
    private Date regDate;

    // MyDateCourse에서 MyCourseDto가 필요한 필드를 빼오는 생성자
    public MyCourseDto(MyDateCourse myDateCourse) {
//        this.username = myDateCourse.getUsername();
        this.courseId = myDateCourse.getCourseId();
        this.postId = myDateCourse.getPostId();
        this.meetingDate = myDateCourse.getMeetingDate();
        this.regDate = myDateCourse.getRegDate();
    }
}
