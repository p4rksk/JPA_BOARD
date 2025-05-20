package com.example.common;

import com.example.board.user.User;

import lombok.Builder;
import lombok.Data;

@Data
public class SessionUser {
    private Integer id;
    private String userName;
    private String nickName;

    public SessionUser(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.nickName = user.getNickName();
    }

    @Builder
    public SessionUser(Integer id, String userName, String nickName) {
        this.id = id;
        this.userName = userName;
        this.nickName = nickName;
    }
    
}
