package com.example.date_scheduling.mydatecourse.api;

import com.example.date_scheduling.mydatecourse.dto.FindAllCourseDto;
import com.example.date_scheduling.mydatecourse.dto.MyCourseDto;
import com.example.date_scheduling.mydatecourse.entity.MyDateCourse;
import com.example.date_scheduling.mydatecourse.service.MyCourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@Slf4j  // 로깅을 위해
@RequestMapping("/api/mycourses")
@RequiredArgsConstructor
@CrossOrigin    // 다른 서버의 요청 허용
public class MyCourseApiController {

    private final MyCourseService service;

    // User 의 데이트 코스 목록 전체 조회 요청
    @GetMapping
    // 토큰 인증 필요
    // 인증된 사용자의 리스트만 조회됨
    public ResponseEntity<?> myCourseList(@AuthenticationPrincipal String username) {
        log.info("/api/mycourses GET request!");
        return ResponseEntity.ok().body(service.findAllServ(username));
    }

    // 데이트 코스 개별 조회 요청 (courseId 기준)
    @GetMapping("/{courseId}")
    public ResponseEntity<?> findOneCourse(@PathVariable String courseId) {
        log.info("/api/mycourses GET request!", courseId);

        // 해당 날짜에 데이트 코스가 없을 경우
        if (courseId == null) return ResponseEntity.badRequest().build();

        MyCourseDto dto = service.findOneServ(courseId);

        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(dto);
    }

    // 데이트 코스 등록 요청
    @PostMapping
    public ResponseEntity<?> registerCourse(@AuthenticationPrincipal String username, @RequestBody MyDateCourse newCourse) {

        newCourse.setUsername(username);

        log.info("/api/mycourses POST request! - {}", newCourse);

        try {
            FindAllCourseDto dto = service.createServ(newCourse);

            if (dto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 데이트 코스 수정 요청
    @PutMapping
    public ResponseEntity<?> updateCourse(@AuthenticationPrincipal String username, @RequestBody MyDateCourse myDateCourse) {

        myDateCourse.setUsername(username);

        log.info("/api/mycourses PUT request!", myDateCourse);

        try {
            FindAllCourseDto dtos = service.update(myDateCourse);
            return ResponseEntity.ok().body(dtos);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 데이트 코스 삭제 요청
    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse (@AuthenticationPrincipal String username, @PathVariable String courseId, @RequestBody MyDateCourse myDateCourse) {

        myDateCourse.setUsername(username);

        log. info("/api/mycourses/{} DELETE request!", courseId);

        try {
            FindAllCourseDto dtos = service.deleteServ(courseId);
            return ResponseEntity.ok().body(dtos);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
