package hrms.hrmsproject.dataAccess.abstracts;

import hrms.hrmsproject.entities.concretes.Graduate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraduateDao extends JpaRepository<Graduate,Integer> {
}
