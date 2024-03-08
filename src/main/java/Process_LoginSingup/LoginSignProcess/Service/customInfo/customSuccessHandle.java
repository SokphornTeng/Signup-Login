package Process_LoginSingup.LoginSignProcess.Service.customInfo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class customSuccessHandle implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        var authorities = authentication.getAuthorities();
        var role = authorities.stream().map(a -> a.getAuthority()).findFirst();
        if(role.orElse("").equals("ADMIN")){
            response.sendRedirect("/adminPage");
        }else if(role.orElse("").equals("USER")){
            response.sendRedirect("/userPage");
        }else {
            response.sendRedirect("/error");
        }

    }
}
