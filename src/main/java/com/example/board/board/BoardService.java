package com.example.board.board;

import java.util.List;

import org.h2.engine.Session;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.board.board.BoardResponse.BoardDetailDTO;
import com.example.board.board.BoardResponse.BoardSaveDTO;
import com.example.board.user.User;
import com.example.board.user.UserRepository;
import com.example.common.SessionUser;
import com.example.common.error.Exception400;
import com.example.common.error.Exception401;
import com.example.common.error.Exception403;
import com.example.common.error.Exception404;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    
   
    public List<BoardResponse.BoardListDTO> boardListDTO() {
    return boardRepository.findByBoardList();

    }
    
    @Transactional
    public BoardDetailDTO boardDetail(int id, SessionUser sessionUser){
           
            Board board  =  boardRepository.findByBoardWithUser(id)
                   .orElseThrow(() -> new  EmptyResultDataAccessException(1));
        
            return new BoardResponse.BoardDetailDTO(board, sessionUser);
    }

    @Transactional
    public BoardSaveDTO boardSave(SessionUser sessionUser, BoardRequest.SaveDTO reqDto) {
        User user = userRepository.findById(sessionUser.getId())
                    .orElseThrow(() -> new Exception401("존재하지 않는 계정입니다."));
        Board board = Board.builder()
                    .title(reqDto.getTitle())
                    .content(reqDto.getContent())
                    .user(user)
                    .build();

        boardRepository.save(board);

        return new BoardResponse.BoardSaveDTO(board.getId(), board.getTitle(), board.getContent());
    }

    @Transactional
    public BoardResponse.BoardUpdateDTO boardUpdate(int id, SessionUser sessionUser, BoardRequest.UpdateDTO reqDTO){
            
            Board board = boardRepository.findById(id)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));
   
            if(sessionUser.getId() != board.getUser().getId() ){
                new Exception403("게시글을 수정할 권한이 없습니다.");
            }
   
                 
            board.setTitle(reqDTO.getTitle());
            board.setContent(reqDTO.getContent());
                


            return new BoardResponse.BoardUpdateDTO(board.getId(), board.getTitle(), board.getContent());
    }


    public Board boardFindById(int boardId){
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));
        return board;
    }

    @Transactional
    public void boardDelete(int boardId, SessionUser sessionUser){
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        if (sessionUser.getId() != board.getUser().getId()) {

            new Exception403("게시글을 삭제할 권한이 없습니다.");
        }

        boardRepository.deleteById(boardId);
    }
}