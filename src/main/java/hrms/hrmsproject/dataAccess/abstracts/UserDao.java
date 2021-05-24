package hrms.hrmsproject.dataAccess.abstracts;


import hrms.hrmsproject.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {
}
