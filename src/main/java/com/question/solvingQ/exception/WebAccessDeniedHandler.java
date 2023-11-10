package com.question.solvingQ.exception;

import com.question.solvingQ.login.PrincipalDetails;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class WebAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex)
            throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());

        if (ex instanceof AccessDeniedException){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null &&
                    ((PrincipalDetails) authentication.getPrincipal()).getUser().getRole().name().equals("USER")){
                request.setAttribute("msg", "접근권한이 없는 사용자입니다.");
                request.setAttribute("nextPage", "/security-login");
            } else {
                request.setAttribute("msg", "로그인 권한이 없는 아이디입니다.");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                SecurityContextHolder.clearContext();
            }
        } else {
            // Logger
        }
        request.getRequestDispatcher("accessDenied").forward(request, response);
    }
}
