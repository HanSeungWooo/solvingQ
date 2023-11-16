package com.question.solvingQ.mappers;

import com.question.solvingQ.dto.NoticeRegistRequest;
import com.question.solvingQ.notice.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface NoticeMapper {
    public void insertNotice(NoticeRegistRequest noticeRegistRequest);
    public List<Notice> selectNoticeList();
}
