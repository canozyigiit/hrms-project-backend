package hrms.hrmsproject.dataAccess.abstracts;

import hrms.hrmsproject.entities.concretes.JobStyle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JopStyleDao extends JpaRepository<JobStyle,Integer> {
}
