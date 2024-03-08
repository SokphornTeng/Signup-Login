package Process_LoginSingup.LoginSignProcess.Repo;

import Process_LoginSingup.LoginSignProcess.Model.Entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InforRepo extends JpaRepository<Info, Long> {

    Info findByEmail(String email);

}
