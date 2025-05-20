package com.example.board.user;

import com.example.board.user.UserInterface.LoginProjection;

import lombok.Data;
import lombok.NoArgsConstructor;

public class UserResponse {

    @NoArgsConstructor
    @Data
    public static class JoinDTO {
        private String userName;
        private String password;
        private String nickName;


        public JoinDTO(String userName, String password, String nickName) {
            this.userName = userName;
            this.password = password;
            this.nickName = nickName;
        }
    }

    @NoArgsConstructor
    @Data
    public static class loginDTO {
        private Integer userId;
        private String userName;
        private String password;


        public loginDTO(LoginProjection user) {
            this.userId = user.getId();
            this.userName = user.getUserName();
            this.password = user.getPassword();
        }

        
    }
}
