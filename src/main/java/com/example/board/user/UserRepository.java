package com.example.board.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer>{
    
    Optional<User> findByUserName(@Param("userName") String userName);
}
