package hrms.hrmsproject.dataAccess.abstracts;

import hrms.hrmsproject.entities.concretes.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeDao extends  JpaRepository<Resume,Integer> {

    Resume getByJobSeekerId(int id);
}
