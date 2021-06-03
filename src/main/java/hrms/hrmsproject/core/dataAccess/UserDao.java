package hrms.hrmsproject.core.dataAccess;


import hrms.hrmsproject.core.entities.User;
import hrms.hrmsproject.entities.concretes.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User,Integer> {
    User findByEmail(String email);

}
