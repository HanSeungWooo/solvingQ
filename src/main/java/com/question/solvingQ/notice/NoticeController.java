package com.question.solvingQ.notice;

import com.question.solvingQ.dto.NoticeRegistRequest;
import com.question.solvingQ.user.User;
import com.question.solvingQ.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/security-login/notice")
public class NoticeController {
    private final UserService userService;
    private final NoticeService noticeService;

    @GetMapping({"", "/"})
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

        List<Notice> list = noticeService.searchNoticeList();

        model.addAttribute("list", list);
        model.addAttribute("totalCnt", list.size());

        return "notice";
    }
}
