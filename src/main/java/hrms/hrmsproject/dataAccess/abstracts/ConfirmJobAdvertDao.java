package hrms.hrmsproject.dataAccess.abstracts;

import hrms.hrmsproject.entities.concretes.ConfirmJobAdvert;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ConfirmJobAdvertDao extends JpaRepository<ConfirmJobAdvert,Integer> {

    ConfirmJobAdvert getByJobAdvert_Id(int id);
    boolean existsByJobAdvert_Id(int id);
}
