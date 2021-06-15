package hrms.hrmsproject.dataAccess.abstracts;

import hrms.hrmsproject.entities.concretes.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolDao extends JpaRepository<School,Integer> {

    List<School> findAllByResumeIdOrderByEndedDateDesc(int id);
}
