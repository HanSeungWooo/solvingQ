package com.question.solvingQ.admin;

import com.question.solvingQ.dto.ModifyRequest;
import com.question.solvingQ.dto.RegistRequest;
import com.question.solvingQ.exception.*;
import com.question.solvingQ.user.User;
import com.question.solvingQ.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/security-login/api/admin")
public class AdminRestController {
    private final UserService userService;
    private final AdminService adminService;

    /**
     * 사용자 등록
     * @param registRequest
     * @param model
     * @return
     */
    @PostMapping("/regist")
    public ResponseEntity<?> regist(@Valid @RequestBody RegistRequest registRequest, Model model) throws Exception {
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");

        // loginId 중복 체크
        if (userService.checkLoginIdDuplicate(registRequest.getLoginId())){
            throw new ApiException(ApiExceptionType.CAN_NOT_INSERT, "로그인 아이디가 중복됩니다.");
        }
        // nickname 중복 체크
        if(userService.checkNicknameDuplicate(registRequest.getNickname())) {
            throw new ApiException(ApiExceptionType.CAN_NOT_INSERT, "닉네임이 중복됩니다.");
        }
        // password와 passwordCheck가 같은지 체크
        if(!registRequest.getPassword().equals(registRequest.getPasswordCheck())){
            throw new ApiException(ApiExceptionType.CAN_NOT_INSERT, "비밀번호 체크가 일치하지 않습니다.");
        }

        adminService.regist2(registRequest);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 사용자 정보 수정
     * @param loginId
     * @param modifyRequest
     * @param model
     * @return
     */
    @PostMapping("/modify/{id}")
    public ResponseEntity<?> modify(
            @PathVariable(value = "id") String loginId,
            @Valid @RequestBody ModifyRequest modifyRequest,
            Model model) throws Exception {
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");

        // nickname 중복 체크
        if(userService.checkNicknameDuplicate(modifyRequest.getNickname(), loginId)) {
            throw new ApiException(ApiExceptionType.CAN_NOT_UPDATE, "닉네임이 중복됩니다.");
        }

        adminService.modify(modifyRequest);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
