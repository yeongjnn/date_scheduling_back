package com.example.date_scheduling.mydatecourse.repository;

import com.example.date_scheduling.mydatecourse.entity.MyDateCourse;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
// 나만의 데이트 코스를 CRUD 한다.
public interface MyDateCourseRepository {

    // 데이트 코스로 등록
    // 상세 게시물 페이지에서 등록
    boolean register(MyDateCourse dateCourse);


    ////// <마이 페이지> ///////
    // 사용자의 전체 데이트 코스 목록 조회 기능
    List<MyDateCourse> findAllMyCourse(String username);

    // 데이트 코스 조회 요청 (courseId 기준)
    MyDateCourse findOne(String courseId);

    // 데이트 코스 수정 기능
    boolean modify(MyDateCourse dateCourse);

    // 데이트 코스 삭제 기능
    boolean remove(String courseId);
}
