package com.question.solvingQ.admin;

import com.question.solvingQ.dto.ModifyRequest;
import com.question.solvingQ.dto.RegistRequest;
import com.question.solvingQ.user.User;
import com.question.solvingQ.user.UserRole;
import com.question.solvingQ.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/security-login/admin")
public class AdminController {
    private final UserService userService;
    private final AdminService adminService;

    @GetMapping({"", "/"})
    public String adminPage(Model model){
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");

        return "admin";
    }

    @GetMapping("/list")
    public String userList(Model model, Authentication auth){
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");

        // 모든 사용자 목록 조회
        List<User> list = adminService.getUserList();
        model.addAttribute("userList", list);
        model.addAttribute("totalCnt", list.size());

        // 현재 로그인 사용자 조회
        User loginUser = userService.getLoginUserByLoginId(auth.getName());

        if (loginUser == null){
            return "redirect:/security-login/login";
        }

        model.addAttribute("loginUser", loginUser);

        return "userList";
    }

    @GetMapping("/regist")
    public String regist(Model model){
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");

        model.addAttribute("registRequest", new RegistRequest());
        model.addAttribute("roleOpts", UserRole.values());

        return "regist";
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable(value = "id") String loginId, Model model){
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");

        User targetUser = userService.getLoginUserByLoginId(loginId);

        if (targetUser != null){
            model.addAttribute("modifyRequest", new ModifyRequest(targetUser));
        }
        model.addAttribute("roleOpts", UserRole.values());

        return "modify";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(required = true) String loginId, Model model) {
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");

        adminService.delete(loginId);

        return "redirect:/security-login";
    }
}
