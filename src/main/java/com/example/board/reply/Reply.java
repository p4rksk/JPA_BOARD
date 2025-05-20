package com.example.board.reply;

import java.time.LocalDateTime;

import com.example.board.board.Board;
import com.example.board.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name= "reply_tb")
@NoArgsConstructor
public class Reply {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = true)
    private LocalDateTime commentAt;

    @Column(nullable = true)
    private LocalDateTime createdAt;
    
    @Column(nullable = true)
    private LocalDateTime modifiedAt;

    @Column(nullable = true)
    private LocalDateTime updatedAt;

    @Builder
    public Reply(Integer id, Board board, User user, String comment, LocalDateTime commentAt, LocalDateTime createdAt,
            LocalDateTime modifiedAt, LocalDateTime updatedAt) {
        this.id = id;
        this.board = board;
        this.user = user;
        this.comment = comment;
        this.commentAt = commentAt;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.updatedAt = updatedAt;
    }

   
    
    
    
    
    
}
