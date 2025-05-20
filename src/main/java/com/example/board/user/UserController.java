package com.example.board.user;

import java.net.http.HttpRequest;
import java.util.Optional;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.common.util.ApiUtil;





@RequiredArgsConstructor
@Controller
public class UserController {
    private UserService userService;
    private UserRepository userRepository;

   @GetMapping("/joinForm")
   public String joinForm() {
       
       return "/user/joinForm";
   }
   
   @PostMapping("/join")
   public String join(HttpRequest req,UserRequest.JoinDTO reqDTO) {
        User user = userService.join(reqDTO);
       return "/loginForm";
   }
   
   @GetMapping("/api/username-same-check")
   public @ResponseBody ApiUtil<?> userNameCheck(@RequestParam("userName") String userName) {
    System.out.println("1");
    Optional<User> user = userRepository.findByUserName(userName);
    System.out.println("2");

    if (user.isPresent()) {
        System.out.println("3");
        return new ApiUtil<>(true);
        
    } else {
        System.out.println("4");
        return new ApiUtil<>(false);
    }
    
   }
   
    
}
