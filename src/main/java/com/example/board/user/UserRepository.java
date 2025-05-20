package com.example.board.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.board.user.UserInterface.LoginProjection;
import com.example.board.user.UserInterface.UserNameProjection;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    
    @Query(value = """
        Select u.user_name AS userName  
        from user_tb u
        where u.user_name = :userName       
        """, nativeQuery = true)
    Optional<UserNameProjection> findByUserName(@Param("userName") String userName);

       
    @Query(value = """
        Select u.user_name AS userName,
               u.password AS password,
               u.id AS id
        from user_tb u
        where u.user_name = :userName and u.password = :password      
        """, nativeQuery = true)
    Optional<LoginProjection> findByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);
}
