package com.question.solvingQ.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequiredArgsConstructor
@RequestMapping("/security-login")
public class UserController {
    private final UserService userService;

    @GetMapping("/notice")
    public String noticePage(Model model, Authentication auth){
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");

        User writer = userService.getLoginUserByLoginId(auth.getName());
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");

        model.addAttribute("writerId", writer.getLoginId());
        model.addAttribute("writerNickname", writer.getNickname());
        model.addAttribute("regDate", dateFormat.format(now));
        model.addAttribute("regDt", dtFormat.format(now));

        return "notice";
    }
}
