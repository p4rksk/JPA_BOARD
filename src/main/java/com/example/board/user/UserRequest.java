package com.example.board.user;

import lombok.Data;
import lombok.NoArgsConstructor;

public class UserRequest {

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
        private String userName;
        private String password;
        
        public loginDTO(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        
    }
}
