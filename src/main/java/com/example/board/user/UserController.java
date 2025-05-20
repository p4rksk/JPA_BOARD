package com.example.board.user;

import java.util.Optional;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.board.user.UserInterface.UserNameProjection;
import com.example.common.util.ApiUtil;

import jakarta.servlet.http.HttpSession;





@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final HttpSession session;

   @GetMapping("/joinForm")
   public String joinForm() {
       
       return "user/joinForm";
   }
   
   @PostMapping("/join")
   public String join(UserRequest.JoinDTO reqDTO) {
        User user = userService.join(reqDTO);
       return "redirect:user/loginForm";
   }
   
   @GetMapping("/api/username-same-check")
   @ResponseBody 
   public ApiUtil<?> userNameCheck(@RequestParam("userName") String userName) {
    Optional<UserNameProjection> user = userRepository.findByUserName(userName);

    if (user.isPresent() && user.get().getUserName().equals(userName)) {

        return new ApiUtil<>(false);
        
    } else {
        return new ApiUtil<>(true);
    }
    
   }

   @GetMapping("/loginForm")
   public String loginForm() {
       return "user/loginForm";
   }
   
   @PostMapping("/login")
   public String login(UserRequest.loginDTO reqDTO) {
    UserResponse.loginDTO repDTO  = userService.login(reqDTO);
    session.setAttribute("sessionUser", repDTO);
       return "redirect:/";
   }
   
}
