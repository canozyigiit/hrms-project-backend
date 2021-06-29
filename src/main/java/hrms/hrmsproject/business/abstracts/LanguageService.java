package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.Language;
import hrms.hrmsproject.entities.dtos.languageDtos.LanguageDto;

import java.util.List;

public interface LanguageService {

    Result add(LanguageDto languageDto);

    DataResult<List<LanguageDto>> getAll();

    DataResult<Language> getById(int id);

    Result update(Language language);

    Result delete (int id);

    DataResult<List<LanguageDto>> getByResumeId(int id);
}
