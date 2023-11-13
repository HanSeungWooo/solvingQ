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

    @GetMapping("/")
    public String adminPage(Model model){
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");

        return "admin";
    }

    @PostMapping("/list")
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

    @PostMapping("/regist")
    public String regist(@Valid @ModelAttribute RegistRequest registRequest, BindingResult bindingResult, Model model){
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");

        // loginId 중복 체크
        if (userService.checkLoginIdDuplicate(registRequest.getLoginId())){
            bindingResult.addError(new FieldError("registRequest", "loginId", "로그인 아이디가 중복됩니다."));
        }
        // nickname 중복 체크
        if(userService.checkNicknameDuplicate(registRequest.getNickname())) {
            bindingResult.addError(new FieldError("registRequest", "nickname", "닉네임이 중복됩니다."));
        }
        // password와 passwordCheck가 같은지 체크
        if(!registRequest.getPassword().equals(registRequest.getPasswordCheck())){
            bindingResult.addError(new FieldError("registRequest", "passwordCheck", "비밀번호가 일치하지 않습니다."));
        }

        if (bindingResult.hasErrors()){
            return "regist";
        }

        adminService.regist2(registRequest);
        return "redirect:/security-login";
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

    @PostMapping("/modify/{id}")
    public String modify(
            @PathVariable(value = "id") String loginId,
            @Valid @ModelAttribute ModifyRequest modifyRequest,
            BindingResult bindingResult,
            Model model){
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");

        // nickname 중복 체크
        if(userService.checkNicknameDuplicate(modifyRequest.getNickname())) {
            bindingResult.addError(new FieldError("registRequest", "nickname", "닉네임이 중복됩니다."));
        }

        if (bindingResult.hasErrors()){
            model.addAttribute("modifyRequest", modifyRequest);
            return "modify";
        }

        adminService.modify(modifyRequest);
        return "list";
    }

//    @GetMapping("/history")
//    public String search(Model model){
//        model.addAttribute("loginType", "security-login");
//        model.addAttribute("pageName", "Security 로그인");
//
//        return "history";
//    }
}
