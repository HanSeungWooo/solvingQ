package com.question.solvingQ.user;

import com.question.solvingQ.dto.LoginRequest;
import com.question.solvingQ.dto.ModifyRequest;
import com.question.solvingQ.dto.RegistRequest;
import com.question.solvingQ.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    // Spring Security를 활용한 로그인 구현 시 사용
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * loginId 중복 체크
     * 사용자 등록 기능 구현 시 사용
     * 중복되면 true return
     */
    public boolean checkLoginIdDuplicate(String loginId) {
        return userMapper.selectUserByLoginId(loginId) != null;
    }

    /**
     * nickname 중복 체크
     * 사용자 등록 기능 구현 시 사용
     * 중복되면 true return
     */
    public boolean checkNicknameDuplicate(String nickname) {
        return userMapper.selectUserByNickname(nickname) != null;
    }

    /**
     * 사용자 등록 기능 1
     * 화면에서 JoinRequest(loginId, password, nickname, role)을 입력받아 User로 변환 후 저장
     * loginId, nickname 중복 체크는 Controller에서 진행 => 에러 메세지 출력을 위해
     */
    public void regist(RegistRequest req) {
        userMapper.insertUser(req);
    }

    /**
     * 사용자 등록 기능 2
     * 화면에서 RegistRequest(loginId, password, nickname, role)을 입력받아 User로 변환 후 저장
     * 사용자 등록 1과는 달리 비밀번호를 암호화해서 저장
     * loginId, nickname 중복 체크는 Controller에서 진행 => 에러 메세지 출력을 위해
     */
    public void regist2(RegistRequest req) {
        req.setPassword(passwordEncoder.encode(req.getPassword()));
        userMapper.insertUser(req);
    }

    /**
     *  로그인 기능
     *  화면에서 LoginRequest(loginId, password)을 입력받아 loginId와 password가 일치하면 User return
     *  loginId가 존재하지 않거나 password가 일치하지 않으면 null return
     */
    public User login(LoginRequest req) {
        User user = userMapper.selectUserByLoginId(req.getLoginId());

        // loginId와 일치하는 User가 없으면 null return
        if(user == null) {
            return null;
        }

        // 찾아온 User의 password와 입력된 password가 다르면 null return
        if(!user.getPassword().equals(req.getPassword())) {
            return null;
        }

        return user;
    }

    /**
     * loginId(String)를 입력받아 User을 return 해주는 기능
     * 인증, 인가 시 사용
     * loginId가 null이거나(로그인 X) userId로 찾아온 User가 없으면 null return
     * loginId로 찾아온 User가 존재하면 User return
     */
    public User getLoginUserByLoginId(String loginId) {
        if(loginId == null) return null;

        User user = userMapper.selectUserByLoginId(loginId);

        return user;
    }

    /**
     * 모든 계정 조회
     */
    public List<User> getUserList(){
        return userMapper.selectAllUser();
    }

    /**
     * 사용자 수정 기능
     * 화면에서 ModifyRequest(loginId, password, nickname, role)을 입력받아 User로 변환 후 저장
     * 이미 암호화된 password를 가져오므로 password를 암호화하지 않음
     * loginId, nickname 중복 체크는 Controller에서 진행 => 에러 메세지 출력을 위해
     */
    public void modify(ModifyRequest req) {
        userMapper.updateUser(req);
    }

}
