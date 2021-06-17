package hrms.hrmsproject.dataAccess.abstracts;

import hrms.hrmsproject.entities.concretes.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeDao extends  JpaRepository<Resume,Integer> {

    List<Resume> findAllByJobSeekerId(int id);

    Resume findByJobSeekerId(int id);
}
