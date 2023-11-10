package com.question.solvingQ.user;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String loginId;
    private String password;
    private String nickname;
    private UserRole role;
}
