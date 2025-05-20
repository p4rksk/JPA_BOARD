package com.example.board.board;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{
    
     @Query("SELECT new com.example.board.board.BoardResponse$BoardListDTO(b.id,b.title) " +
       "FROM Board b")
    List<BoardResponse.BoardListDTO> findByBoardList();

      @Query("""
        SELECT b FROM Board b
        JOIN FETCH b.user
        WHERE b.id = :id
         """)
    Optional<Board> findByBoardWithUser(@Param("id") int id);
}
