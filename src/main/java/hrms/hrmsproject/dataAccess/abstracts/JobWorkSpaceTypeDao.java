package hrms.hrmsproject.dataAccess.abstracts;

import hrms.hrmsproject.entities.concretes.JobWorkSpaceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobWorkSpaceTypeDao extends JpaRepository<JobWorkSpaceType,Integer> {
}
