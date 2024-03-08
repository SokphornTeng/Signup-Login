package Process_LoginSingup.LoginSignProcess.Service;

import Process_LoginSingup.LoginSignProcess.DTO.InfoDTO;
import Process_LoginSingup.LoginSignProcess.Model.Entity.Info;
import Process_LoginSingup.LoginSignProcess.Repo.InforRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class serviceInfoImplement implements serviceInfo{

    @Autowired
    private InforRepo _InforRepo;


    private PasswordEncoder passwordEncoder;

    public serviceInfoImplement(InforRepo inforRepo, PasswordEncoder passwordEncoder) {
        this._InforRepo = inforRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Info saveInfo(InfoDTO dtoInfo) {
        Info newInfo = new Info(dtoInfo.getFullName(), dtoInfo.getEmail(), passwordEncoder.encode(dtoInfo.getPassword()), dtoInfo.getRole());
        return this._InforRepo.save(newInfo);
    }

}
