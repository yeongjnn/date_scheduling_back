package com.example.date_scheduling.mydatecourse.service;

import com.example.date_scheduling.mydatecourse.dto.FindAllCourseDto;
import com.example.date_scheduling.mydatecourse.dto.MyCourseDto;
import com.example.date_scheduling.mydatecourse.entity.MyDateCourse;
import com.example.date_scheduling.mydatecourse.repository.MyDateCourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MyCourseService {

    private final MyDateCourseRepository repository;

    public FindAllCourseDto findAllServ() {
        return new FindAllCourseDto(repository.findAll());
    }

    // 새로운 데이트 코스 등록
    public FindAllCourseDto createServ(final MyDateCourse newCourse) {

        if (newCourse == null) {
            log.warn("newCourse cannot be null!");
            throw new RuntimeException("newCourse cannot be null!");
        }

        boolean flag = repository.register(newCourse);
        if (flag) log.info("새로운 데이트 코스 [courseId : {}]이 저장되었습니다.", newCourse.getCourseId());

        return flag ? findAllServ() : null;
    }
    
    // 데이트 코스 개별 조회
    public MyCourseDto findOneServ(String courseId) {

        MyDateCourse myDateCourse = repository.findOne(courseId);
        log.info("findOneServ return data - {}", myDateCourse);

        return myDateCourse != null ? new MyCourseDto() : null;
    }

    // 데이트 코스 삭제
    public FindAllCourseDto deleteServ(String courseId) {

        boolean flag = repository.remove(courseId);

        // 삭제 실패한 경우
        if(!flag) {
            log.warn("delete fail! not found courseId [{}]", courseId);
            throw new RuntimeException("delete fail!");
        }
        return findAllServ();
    }

    // 데이트 코스 수정
    public FindAllCourseDto update(MyDateCourse dateCourse) {

        boolean flag = repository.modify(dateCourse);
        return flag ? findAllServ() : new FindAllCourseDto();
    }

}
