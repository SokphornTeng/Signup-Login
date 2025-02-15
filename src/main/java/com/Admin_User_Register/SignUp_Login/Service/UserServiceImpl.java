package com.Admin_User_Register.SignUp_Login.Service;

import com.Admin_User_Register.SignUp_Login.Model.DTO.UserDto;
import com.Admin_User_Register.SignUp_Login.Model.Entity.User;
import com.Admin_User_Register.SignUp_Login.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(UserDto userDto) {

        User newUser = new User(
                userDto.getFullName(),
                userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getRole());
           return this.userRepo.save(newUser);
    }

}
