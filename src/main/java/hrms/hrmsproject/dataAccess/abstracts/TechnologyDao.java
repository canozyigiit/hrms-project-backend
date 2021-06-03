package hrms.hrmsproject.dataAccess.abstracts;

import hrms.hrmsproject.entities.concretes.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnologyDao extends JpaRepository<Technology,Integer> {
}
