package com.question.solvingQ.dto;

import com.question.solvingQ.user.User;
import com.question.solvingQ.user.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class RegistRequest {
    @NotBlank(message = "로그인 아이디가 비어있습니다.")
    private String loginId;
    @NotBlank(message = "비밀번호가 비어있습니다.")
    private String password;
    @NotBlank(message = "비밀번호 재확인이 비어있습니다.")
    private String passwordCheck;
    @NotBlank(message = "닉네임이 비어있습니다.")
    private String nickname;
    @NotBlank(message = "권한이 비어있습니다.")
    private String role;

    public User toEntity(){
        return User.builder()
                .loginId(this.loginId)
                .password(this.password)
                .nickname(this.nickname)
                .role(UserRole.valueOf(this.role))
                .build();
    }

    public User toEntity(String encodedPassword){
        return User.builder()
                .loginId(this.loginId)
                .password(encodedPassword)
                .nickname(this.nickname)
                .role(UserRole.valueOf(this.role))
                .build();
    }
}
