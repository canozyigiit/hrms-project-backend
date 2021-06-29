package hrms.hrmsproject.dataAccess.abstracts;


import hrms.hrmsproject.entities.concretes.SystemPersonnel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SystemPersonnelDao extends JpaRepository<SystemPersonnel,Integer> {
    List<SystemPersonnel> findAllByEmail(String email);
}
