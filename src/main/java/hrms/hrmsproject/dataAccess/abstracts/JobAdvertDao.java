package hrms.hrmsproject.dataAccess.abstracts;

import hrms.hrmsproject.entities.concretes.JobAdvert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobAdvertDao extends JpaRepository<JobAdvert, Integer> {

    @Query("From JobAdvert where isOpen = true")
    List<JobAdvert> getAllOpenJobAdvertList();//Sistemdeki tüm aktif iş ilanları listele

    List<JobAdvert> getByisOpenTrueAndEmployer_Id(int id);//Sistemde bir firmaya ait tüm aktif iş ilanları listele

    List<JobAdvert> getByisOpenTrueOrderByCreatedDateDesc();//Sistemdeki tüm aktif iş ilanları tarihe göre listele
}
