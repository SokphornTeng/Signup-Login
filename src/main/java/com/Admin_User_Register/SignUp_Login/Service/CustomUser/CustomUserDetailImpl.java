package com.Admin_User_Register.SignUp_Login.Service.CustomUser;

import com.Admin_User_Register.SignUp_Login.Model.Entity.User;
import com.Admin_User_Register.SignUp_Login.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User newUser = this.userRepo.findByEmail(username);
            if (newUser == null) {
               throw new UsernameNotFoundException("This User Not Found");
            }

        return new CustomUserDetail(newUser);
    }

}
