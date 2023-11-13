package com.question.solvingQ.login.service;

import com.question.solvingQ.login.PrincipalDetails;
import com.question.solvingQ.mappers.UserMapper;
import com.question.solvingQ.user.User;
import com.question.solvingQ.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getLoginUserByLoginId(username);
        if (user == null){
            throw new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.");
        }

        return new PrincipalDetails(user);
    }
}
