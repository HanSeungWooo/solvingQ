package com.question.solvingQ.notice;

import com.question.solvingQ.dto.NoticeRegistRequest;
import com.question.solvingQ.mappers.NoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeMapper noticeMapper;

    /**
     * 공지사항 등록
     */
    public void registNotice(NoticeRegistRequest noticeRegistRequest){
        noticeMapper.insertNotice(noticeRegistRequest);
    }

    /**
     * 공지사항 조회
     */
    public List<Notice> searchNoticeList(){
        return noticeMapper.selectNoticeList();
    }
}
