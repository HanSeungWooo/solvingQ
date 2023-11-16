package com.question.solvingQ.notice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
    private int noticeNo;
    private String title;
    private String content;
    private String showYn;
    private String writerId;
    private String regDate;
}
