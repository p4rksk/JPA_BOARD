package com.example.common.config;



import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.common.SessionUser;
import com.example.common.error.Exception401;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle............");
        HttpSession session = request.getSession();
        SessionUser user = (SessionUser) session.getAttribute("sessionUser");

        if(user == null){
            throw new Exception401("로그인 하셔야 해요");
        }
        return true;
    }
}