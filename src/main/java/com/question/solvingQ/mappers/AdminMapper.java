package com.question.solvingQ.mappers;

import com.question.solvingQ.dto.ModifyRequest;
import com.question.solvingQ.dto.NoticeRegistRequest;
import com.question.solvingQ.dto.RegistRequest;
import com.question.solvingQ.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AdminMapper {
    public List<User> selectAllUser();
    public void insertUser(RegistRequest registRequest);
    public void updateUser(ModifyRequest modifyRequest);
    public void deleteUser(String loginId);
}
