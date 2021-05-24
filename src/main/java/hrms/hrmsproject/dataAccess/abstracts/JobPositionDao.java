package hrms.hrmsproject.dataAccess.abstracts;


import hrms.hrmsproject.entities.concretes.Employer;
import hrms.hrmsproject.entities.concretes.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobPositionDao extends JpaRepository<JobPosition,Integer> {
    List<JobPosition> findAllByName(String name);
}
