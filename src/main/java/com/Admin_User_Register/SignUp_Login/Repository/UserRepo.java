package com.Admin_User_Register.SignUp_Login.Repository;

import com.Admin_User_Register.SignUp_Login.Model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
