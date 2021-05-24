package hrms.hrmsproject.dataAccess.abstracts;



import hrms.hrmsproject.entities.concretes.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSeekerDao extends JpaRepository<JobSeeker,Integer> {
}
