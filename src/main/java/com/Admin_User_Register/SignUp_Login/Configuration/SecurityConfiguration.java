package com.Admin_User_Register.SignUp_Login.Configuration;

import com.Admin_User_Register.SignUp_Login.Service.CustomUser.CustomHandleSuccess;
import com.Admin_User_Register.SignUp_Login.Service.CustomUser.CustomUserDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private CustomHandleSuccess customHandleSuccess;
    @Autowired
    private CustomUserDetailImpl customUserDetailImpl;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DefaultSecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(c -> c.disable())
               .authorizeHttpRequests(request -> request.requestMatchers("/adminPage")
                       .hasAuthority("ADMIN").requestMatchers("/userPage").hasAuthority("USER")
                       .requestMatchers("/register", "/CSS/**").permitAll()
                       .anyRequest()
                       .authenticated())
               .formLogin(f -> f.loginPage("/login").loginProcessingUrl("/login")
                       .successHandler(customHandleSuccess).permitAll())
               .logout(l -> l.invalidateHttpSession(true).clearAuthentication(true)
                       .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                       .logoutSuccessUrl("/logout?logout").permitAll());
          return httpSecurity.build();

    }

    @Bean
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailImpl).passwordEncoder(passwordEncoder());
    }

}
