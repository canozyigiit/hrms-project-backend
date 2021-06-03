package hrms.hrmsproject.dataAccess.abstracts;

import hrms.hrmsproject.entities.concretes.City;
import hrms.hrmsproject.entities.concretes.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityDao extends JpaRepository<City,Integer> {
    List<City> findAllByName(String name);
}
