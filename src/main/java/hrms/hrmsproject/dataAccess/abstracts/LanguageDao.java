package hrms.hrmsproject.dataAccess.abstracts;

import hrms.hrmsproject.entities.concretes.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageDao extends JpaRepository<Language,Integer> {

    List<Language> findAllByResumeId(int id);

    List<Language> getByResumeId(int id);
}
