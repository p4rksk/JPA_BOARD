package com.example.board.board;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.board.board.BoardResponse.BoardDetailDTO;
import com.example.board.board.BoardResponse.BoardSaveDTO;
import com.example.board.user.User;
import com.example.board.user.UserRepository;
import com.example.common.SessionUser;
import com.example.common.error.Exception401;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    
    @Transactional
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

        return new BoardResponse.BoardSaveDTO();
    }
}