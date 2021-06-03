package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.Language;

import java.util.List;

public interface LanguageService {

    Result add(Language language);

    DataResult<List<Language>> getAll();

    DataResult<Language> getById(int id);
}
