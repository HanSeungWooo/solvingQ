package com.question.solvingQ.notice;

import com.question.solvingQ.dto.NoticeRegistRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/security-login/api/notice")
public class NoticeRestController {
    private final NoticeService noticeService;

    /**
     * 공지사항 등록
     * @param noticeRegistRequest
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping("/regist")
    public ResponseEntity<?> noticeRegist(
            @Valid @RequestBody NoticeRegistRequest noticeRegistRequest,
            Model model) throws Exception {
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");

        noticeService.registNotice(noticeRegistRequest);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
