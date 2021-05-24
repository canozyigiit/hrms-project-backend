package hrms.hrmsproject.dataAccess.abstracts;


import hrms.hrmsproject.entities.concretes.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployerDao extends JpaRepository<Employer,Integer> {
    List<Employer> findAllByEmail(String email);
}
