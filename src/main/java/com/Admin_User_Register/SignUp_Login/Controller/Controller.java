package com.Admin_User_Register.SignUp_Login.Controller;

import com.Admin_User_Register.SignUp_Login.Model.DTO.UserDto;
import com.Admin_User_Register.SignUp_Login.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class Controller {

    private final UserDetailsService userDetailsService;
    private final UserService userService;

    @Autowired
    public Controller(UserDetailsService userDetailsService, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerPage(@ModelAttribute("user") UserDto userDto) {
         return "Register";
    }

    @PostMapping("/register")
    public String saveRegister(@ModelAttribute("user") UserDto userDto, Model model) {
        this.userService.saveUser(userDto);
        model.addAttribute("message", "Register Successfully");
        return "Register";
    }

    @GetMapping("/login")
    public String login() {
        return "Login";
    }

    @GetMapping("/userPage")
    public String userPage(Model model, Principal principal){
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "User";
    }

    @GetMapping("/adminPage")
    public String adminPage(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "Admin";
    }

}
