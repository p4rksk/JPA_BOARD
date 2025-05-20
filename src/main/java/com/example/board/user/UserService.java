package com.example.board.user;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.common.error.Exception400;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User join(UserRequest.JoinDTO reqDTO){
        Optional<User> exUser = userRepository.findByUserName(reqDTO.getUserName());

        if(exUser.isPresent()) {
            throw new Exception400("동일한 아이디가 존재합니다.");
        }

        User user = userRepository.save(User.builder()
            .userName(reqDTO.getUserName())
            .password(reqDTO.getPassword())
            .nickName(reqDTO.getNickName())
            .build()
        );

        return user;
    }
}
