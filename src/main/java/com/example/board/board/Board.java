package com.example.board.board;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CascadeType;

import com.example.board.reply.Reply;
import com.example.board.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "board_tb")
@NoArgsConstructor
public class Board {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "board", cascade = jakarta.persistence.CascadeType.REMOVE)
    private List<Reply> reply;

    @Column(nullable = true)
    private LocalDateTime wrtieDate;

    @Column(nullable = true)
    private LocalDateTime createdAt;

    @Column(nullable = true)
    private LocalDateTime editedAt;

    @Column(nullable = true)
    private LocalDateTime updatedAt;

    @Builder
    public Board(Integer id, String title, String content, User user, List<Reply> reply, LocalDateTime wrtieDate,
            LocalDateTime createdAt, LocalDateTime editedAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.reply = reply;
        this.wrtieDate = wrtieDate;
        this.createdAt = createdAt;
        this.editedAt = editedAt;
        this.updatedAt = updatedAt;
    }

    
}
