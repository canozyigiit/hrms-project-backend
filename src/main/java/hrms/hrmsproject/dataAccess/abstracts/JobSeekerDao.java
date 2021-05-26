package hrms.hrmsproject.dataAccess.abstracts;



import hrms.hrmsproject.entities.concretes.Employer;
import hrms.hrmsproject.entities.concretes.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobSeekerDao extends JpaRepository<JobSeeker,Integer> {
    List<JobSeeker> findAllByEmail(String email);
    List<JobSeeker> findAllByNationalityId(String nationalityId);

}
