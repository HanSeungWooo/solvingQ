package com.question.solvingQ.admin;

import com.question.solvingQ.dto.ModifyRequest;
import com.question.solvingQ.dto.NoticeRegistRequest;
import com.question.solvingQ.dto.RegistRequest;
import com.question.solvingQ.mappers.AdminMapper;
import com.question.solvingQ.mappers.UserMapper;
import com.question.solvingQ.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {
    private final AdminMapper adminMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * 모든 계정 조회
     */
    public List<User> getUserList(){
        return adminMapper.selectAllUser();
    }
    /**
     * 사용자 등록 기능 1
     * 화면에서 JoinRequest(loginId, password, nickname, role)을 입력받아 User로 변환 후 저장
     * loginId, nickname 중복 체크는 Controller에서 진행 => 에러 메세지 출력을 위해
     */
    public void regist(RegistRequest req) {
        adminMapper.insertUser(req);
    }

    /**
     * 사용자 등록 기능 2
     * 화면에서 RegistRequest(loginId, password, nickname, role)을 입력받아 User로 변환 후 저장
     * 사용자 등록 1과는 달리 비밀번호를 암호화해서 저장
     * loginId, nickname 중복 체크는 Controller에서 진행 => 에러 메세지 출력을 위해
     */
    public void regist2(RegistRequest req) {
        req.setPassword(passwordEncoder.encode(req.getPassword()));
        adminMapper.insertUser(req);
    }

    /**
     * 사용자 수정 기능
     * 화면에서 ModifyRequest(loginId, password, nickname, role)을 입력받아 User로 변환 후 저장
     * 이미 암호화된 password를 가져오므로 password를 암호화하지 않음
     * loginId, nickname 중복 체크는 Controller에서 진행 => 에러 메세지 출력을 위해
     */
    public void modify(ModifyRequest req) {
        adminMapper.updateUser(req);
    }

    /**
     * 사용자 삭제 기능
     */
    public void delete(String loginId){
        adminMapper.deleteUser(loginId);
    }
}
