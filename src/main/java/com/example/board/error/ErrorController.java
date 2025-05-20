package com.example.board.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/error")
@Controller
public class ErrorController {
    
    @GetMapping("/")
    public String getMethodName() {
        return new String("error");
    }
    
}
