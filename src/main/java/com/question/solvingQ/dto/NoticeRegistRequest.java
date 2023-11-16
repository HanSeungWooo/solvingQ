package com.question.solvingQ.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
public class NoticeRegistRequest {
    @NotBlank
    private String title;
    private String content;
    @NotBlank
    private String showYn;
    @NotBlank
    private String writerId;
    @NotBlank
    private String regDate;
}
