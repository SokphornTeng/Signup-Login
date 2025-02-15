package com.Admin_User_Register.SignUp_Login.Service.CustomUser;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CustomHandleSuccess implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        var authourities = authentication.getAuthorities();
        var role = authourities.stream().map(r -> r.getAuthority()).findFirst();
        if(role.orElse("").equals("ADMIN")) {
            response.sendRedirect("/adminPage");
        } else if(role.orElse("").equals("USER")) {
            response.sendRedirect("/userPage");
        } else {
            response.sendRedirect("/error");
        }

    }
}
