package hrms.hrmsproject.dataAccess.abstracts;


import hrms.hrmsproject.entities.concretes.JobPositon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPositionDao extends JpaRepository<JobPositon,Integer> {
}
