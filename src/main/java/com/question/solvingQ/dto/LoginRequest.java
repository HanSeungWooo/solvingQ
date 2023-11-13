package com.question.solvingQ.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {
    private String loginId;
    private String password;
    private boolean remember;
}
