package Process_LoginSingup.LoginSignProcess.Controller;

import Process_LoginSingup.LoginSignProcess.DTO.InfoDTO;
import Process_LoginSingup.LoginSignProcess.Model.Entity.Info;
import Process_LoginSingup.LoginSignProcess.Repo.InforRepo;
import Process_LoginSingup.LoginSignProcess.Service.serviceInfo;
//import Process_LoginSingup.LoginSignProcess.Service.serviceInfoImplement;
import Process_LoginSingup.LoginSignProcess.Service.serviceInfoImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class infoController {

    @Autowired
    private serviceInfo _serviceInfo;
    private serviceInfoImplement _serviceInfoImplement;
    @Autowired
    private UserDetailsService userDetailsService;


    public infoController(serviceInfo _serviceInfo) {
        this._serviceInfo = _serviceInfo;
    }

    @GetMapping("/register")
    public String registerForm(@ModelAttribute("objectInfo") InfoDTO dtoInfo){
        return "SignUp";
    }

    @PostMapping("/register")
    public String registerInfo(@ModelAttribute("objectInfo") InfoDTO dtoInfo, Model model){
        model.addAttribute("showInfo", "Your registe is successfully!");
        this._serviceInfo.saveInfo(dtoInfo);
        return "SignUp";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "Login";
    }
    @GetMapping("/adminPage")
    public String adminRole(Model model, Principal principal){
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(principal.getName());
       model.addAttribute("userInfo", userDetails);
       return "Admin";
    }
    @GetMapping("/userPage")
    public String userRole(Model model, Principal principal){
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("userInfo", userDetails);
        return "User";
    }

}
