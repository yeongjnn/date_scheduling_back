package com.example.date_scheduling.user.service;

import com.example.date_scheduling.user.entity.UserEntity;
import com.example.date_scheduling.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    //이메일 통해서 userentity 찾아내기
    public UserEntity getByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    //loginId 통해서 userEntity 찾아내기
    public UserEntity getByLoginId(String loginId){
        return userRepository.findUserByLoginId(loginId);
    }

    //userentity를 입력해서 회원가입하기
    public UserEntity createServ(final UserEntity userEntity){
        if (userEntity == null || userEntity.getEmail() == null){
            throw new RuntimeException("Invalid args");
        }
        //패스워드 인코딩해주기
        String rawPw = userEntity.getPassword();
        userEntity.setPassword(encoder.encode(rawPw));
        boolean flag = userRepository.register(userEntity);
        return flag ? getByEmail(userEntity.getEmail()) : null;
    }

    //로그인할 때 검증하기
    public UserEntity validateLogin(final String loginId, final String password){
        //회원가입을 했는가?
        UserEntity user = getByLoginId(loginId);
        if (user == null) throw new RuntimeException("가입된 회원이 아닙니다.");

        //패스워드가 일치하는가?
        if (!encoder.matches(password, user.getPassword())){
            throw new RuntimeException("비밀번호가 틀립니다.");
        }
        return user; //로그인 성공시 그 회원의 정보를 보여준다.
    }

    //이메일 중복 검증
    public boolean emailDuplicate(String email){
        return userRepository.existByEmail(email);
    }
    //로그인 아이디 중복 검증
    public boolean loginIdDuplicate(String loginId){
        return userRepository.existByLoginId(loginId);
    }
}

