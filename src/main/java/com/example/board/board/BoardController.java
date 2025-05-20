package com.example.board.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/board")
public class BoardController {
    
    @GetMapping("/")
    public String main() {
        return "index";
    }
    
    @GetMapping("/{id}") 
    public String boardDetailForm(@PathVariable int id) {
        return "board/detailForm";
    }

    @GetMapping("/saveForm")
    public String boardSaveForm() {
        
        return "board/saveForm";
    }
    
   @GetMapping("/{id}/updateForm")
   public String boardUpdatedForm() {
       
        return "board/updateForm";
   }
   
    
}
