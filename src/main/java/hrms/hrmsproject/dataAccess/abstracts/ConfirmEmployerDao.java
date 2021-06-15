package hrms.hrmsproject.dataAccess.abstracts;

import hrms.hrmsproject.entities.concretes.ConfirmEmployer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmEmployerDao extends JpaRepository<ConfirmEmployer,Integer> {

    ConfirmEmployer getByEmployer_Id(int id);
    boolean existsByEmployer_Id(int id);

}
