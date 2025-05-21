package com.example.board.board;

import java.util.List;

import org.h2.engine.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.board.board.BoardResponse.BoardDetailDTO;
import com.example.common.SessionUser;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final HttpSession session;



    @GetMapping("/")
    public String main(Model model) {
        List<BoardResponse.BoardListDTO> boardList = boardService.boardListDTO();
        model.addAttribute("boardList", boardList);
        return "index";
    }
    
    @GetMapping("/board/{id}") 
    public String boardDetailForm(@PathVariable("id") int id,Model model) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        BoardResponse.BoardDetailDTO board = boardService.boardDetail(id, sessionUser);
        model.addAttribute("board", board);
        return "board/detailForm";
    }

    @GetMapping("/board/saveForm")
    public String boardSaveForm() {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        return "board/saveform";
    }

    @PostMapping("/board/save")
    public String boardSave(BoardRequest.SaveDTO reqDto, Model model) {       
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        boardService.boardSave(sessionUser, reqDto);

        return "redirect:/";
    }
    
    
   @GetMapping("/board/{id}/updateForm")
   public String boardUpdatedForm(@PathVariable("id") int id, Model model) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        Board board = boardService.boardFindById(id);
        model.addAttribute("board", board);
    
        return "board/updateForm";
   }
   
   @PostMapping("/board/{id}/update")
   public String update(@PathVariable("id") int id, BoardRequest.UpdateDTO reqDTO, Model model) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        boardService.boardUpdate(id, sessionUser, reqDTO);

       return "redirect:/";
   }
   
   @PostMapping("/board/{id}/delete")
   public String delete(@PathVariable("id")int id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        boardService.boardDelete(id, sessionUser);

       return "redirect:/";
   }
   
    
}
