package hrms.hrmsproject.dataAccess.abstracts;

import hrms.hrmsproject.entities.concretes.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageDao extends JpaRepository<Language,Integer> {
}
