package hrms.hrmsproject.dataAccess.abstracts;


import hrms.hrmsproject.entities.concretes.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerDao extends JpaRepository<Employer,Integer> {
}
