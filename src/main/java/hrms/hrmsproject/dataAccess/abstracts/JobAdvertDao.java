package hrms.hrmsproject.dataAccess.abstracts;

import hrms.hrmsproject.entities.concretes.JobAdvert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobAdvertDao extends JpaRepository<JobAdvert, Integer> {

    boolean existsById(int id);
    List<JobAdvert> getByisOpenTrue();//Sistemdeki tüm aktif iş ilanları listele

    List<JobAdvert> getByisOpenTrueAndCity_Id(int id);//Şehirde ki aktif  ilanları getir

    List<JobAdvert> getByisConfirmedTrue();//Onaylanmış ilanları getir

    List<JobAdvert> getByisOpenTrueAndEmployer_Id(int id);//Sistemde bir firmaya ait tüm aktif iş ilanları listele

    //@Query("Select new hrms.hrmsproject.entities.dtos.jobAdvertDtos.JobAdvertDto(j.openPositionCount, j.createdDate, j.deadLine,e.companyName) From Employer e Inner Join e.jobAdverts j")
    List<JobAdvert> getByisOpenTrueOrderByCreatedDateDesc();//Sistemdeki tüm aktif iş ilanları tarihe göre listele
}
