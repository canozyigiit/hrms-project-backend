package hrms.hrmsproject.dataAccess.abstracts;

import hrms.hrmsproject.entities.concretes.CurrentEmployer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentEmployerDao extends JpaRepository<CurrentEmployer,Integer> {
    CurrentEmployer getByEmployerId(int id);
}
