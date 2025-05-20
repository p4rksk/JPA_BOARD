package com.example.board.user;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.board.user.UserInterface.LoginProjection;
import com.example.board.user.UserInterface.UserNameProjection;
import com.example.common.error.Exception400;
import com.example.common.error.Exception401;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User join(UserRequest.JoinDTO reqDTO){
        Optional<UserNameProjection> exUser = userRepository.findByUserName(reqDTO.getUserName());

        if(exUser.isPresent()) {
            throw new Exception400("동일한 아이디가 존재합니다.");
        }


        User user = userRepository.save(User.builder()
            .userName(reqDTO.getUserName())
            .password(reqDTO.getPassword())
            .nickName(reqDTO.getNickName())
            .joinDate(LocalDateTime.now())
            .createdAt(LocalDateTime.now())
            .build()
        );

        return user;
    }

    @Transactional
    public UserResponse.loginDTO login(UserRequest.loginDTO reqDTO) {
        try {
            LoginProjection exUser = userRepository.findByUserNameAndPassword(reqDTO.getUserName(), reqDTO.getPassword())
                    .orElseThrow(() -> new  EmptyResultDataAccessException(1));
        
              return new UserResponse.loginDTO(exUser);
        } catch (Exception e) {
           throw new Exception401("아이디 혹은 비밀번호가 유효하지 않습니다.");
        }
      
    }
}
