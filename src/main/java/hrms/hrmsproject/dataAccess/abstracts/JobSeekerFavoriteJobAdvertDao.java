package hrms.hrmsproject.dataAccess.abstracts;

import hrms.hrmsproject.entities.concretes.JobSeekerFavoriteJobAdvert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobSeekerFavoriteJobAdvertDao extends JpaRepository<JobSeekerFavoriteJobAdvert,Integer> {

    List<JobSeekerFavoriteJobAdvert> getByJobSeeker_Id(int id);
}
