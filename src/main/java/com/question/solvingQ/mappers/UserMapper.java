package com.question.solvingQ.mappers;

import com.question.solvingQ.dto.ModifyRequest;
import com.question.solvingQ.dto.RegistRequest;
import com.question.solvingQ.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {
    public User selectUserByLoginId(String loginId);
    public User selectUserByNickname(String nickname);
    public User selectUserByNicknameNotInLoginId(String nickname, String loginId);
}
