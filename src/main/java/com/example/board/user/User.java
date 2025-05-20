package com.example.board.user;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "user_tb")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(unique = true, length = 15, nullable = false)
    private String userName;
    
    @Column(nullable = false)
    private String password;
    
    @Column(length = 15, nullable = false)
    private String nickName;

    @Column(nullable = true)
    private LocalDateTime joinDate; // 회원가입 날짜

    @Column(nullable = true)
    private LocalDateTime createdAt; // DB에 등록되는 시간


    @Builder
    public User(Integer id, String userName, String password, String nickName, LocalDateTime joinDate,
            LocalDateTime createdAt) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.nickName = nickName;
        this.joinDate = joinDate;
        this.createdAt = createdAt;
    }

    
    

   
    
}
