package Process_LoginSingup.LoginSignProcess.Service.customInfo;

import Process_LoginSingup.LoginSignProcess.Model.Entity.Info;
import Process_LoginSingup.LoginSignProcess.Repo.InforRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class customerInfoDetailService implements UserDetailsService {

    @Autowired
    private InforRepo _InforRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Info newInfo = this._InforRepo.findByEmail(username);
        if(newInfo == null ){
           throw new UsernameNotFoundException("User Not Fount");
        }
        return new customUserDetail(newInfo);
    }
}
