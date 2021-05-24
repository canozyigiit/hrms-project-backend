package hrms.hrmsproject.dataAccess.abstracts;


import hrms.hrmsproject.entities.concretes.SystemPersonnel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemPersonnelDao extends JpaRepository<SystemPersonnel,Integer> {
}
