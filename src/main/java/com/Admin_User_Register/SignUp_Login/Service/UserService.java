package com.Admin_User_Register.SignUp_Login.Service;

import com.Admin_User_Register.SignUp_Login.Model.DTO.UserDto;
import com.Admin_User_Register.SignUp_Login.Model.Entity.User;

public interface UserService {

      User saveUser(UserDto userDto);

}
