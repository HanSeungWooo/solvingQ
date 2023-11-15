package com.question.solvingQ.login.controller;

import com.question.solvingQ.dto.LoginRequest;
import com.question.solvingQ.user.User;
import com.question.solvingQ.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequiredArgsConstructor
@RequestMapping("/security-login")
public class SecurityLoginController {
    private final UserService userService;

    @GetMapping(value = {"", "/"})
    public String home(Model model, Authentication auth){
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");

        if (auth != null){
            User loginUser = userService.getLoginUserByLoginId(auth.getName());
            if (loginUser != null){
                model.addAttribute("nickname", loginUser.getNickname());
            }
        }

        return "home";
    }

    @GetMapping("/login")
    public String loginPage(Model model, Authentication auth){
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");

        if (auth != null){
            User loginUser = userService.getLoginUserByLoginId(auth.getName());
            if (loginUser != null){
                model.addAttribute("nickname", loginUser.getNickname());
                return "home";
            }
        }

        model.addAttribute("loginRequest", new LoginRequest());

        return "login";
    }

    @GetMapping("/info")
    public String userInfo(Model model, Authentication auth){
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");

        User loginUser = userService.getLoginUserByLoginId(auth.getName());

        if (loginUser == null){
            return "redirect:/security-login/login";
        }

        model.addAttribute("user", loginUser);

        return "info";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(){

        return "accessDenied";
    }
}
