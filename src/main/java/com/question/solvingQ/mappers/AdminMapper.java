package com.question.solvingQ.mappers;

import com.question.solvingQ.user.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    public User selectUserByLoginId(String loginId);
}
