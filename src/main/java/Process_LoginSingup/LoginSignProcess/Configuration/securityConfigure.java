package Process_LoginSingup.LoginSignProcess.Configuration;

import Process_LoginSingup.LoginSignProcess.Service.customInfo.customSuccessHandle;
import Process_LoginSingup.LoginSignProcess.Service.customInfo.customerInfoDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class securityConfigure {

    @Autowired
    private customerInfoDetailService customerInfoDetailService;
    @Autowired
    private customSuccessHandle _customSuccessHandle;

    @Bean
  public static PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
  }

 @Autowired
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerInfoDetailService).passwordEncoder(passwordEncoder());
  }

  @Bean
 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http.csrf(x -> x.disable())

              .authorizeHttpRequests(req -> req.requestMatchers("/adminPage")
                      .hasAuthority("ADMIN").requestMatchers("userPage").
                      hasAuthority("USER")
                      .anyRequest().authenticated())

              .formLogin(f ->f.loginPage("/login").loginProcessingUrl("/login").successHandler(_customSuccessHandle).permitAll())

              .logout(l -> l.invalidateHttpSession(true).clearAuthentication(true)
                      .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                      .logoutSuccessUrl("/logout?logout").permitAll()
              );
      return http.build();
  }

}
