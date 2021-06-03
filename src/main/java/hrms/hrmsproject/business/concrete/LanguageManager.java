package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.LanguageService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.core.utilities.results.SuccessDataResult;
import hrms.hrmsproject.core.utilities.results.SuccessResult;
import hrms.hrmsproject.dataAccess.abstracts.LanguageDao;
import hrms.hrmsproject.entities.concretes.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageManager implements LanguageService {
    private LanguageDao languageDao;

    @Autowired
    public LanguageManager(LanguageDao languageDao) {
        this.languageDao = languageDao;
    }

    @Override
    public Result add(Language language) {
        this.languageDao.save(language);
        return new SuccessResult(Messages.languageAdded);
    }

    @Override
    public DataResult<List<Language>> getAll() {
        return new SuccessDataResult<List<Language>>(this.languageDao.findAll(),Messages.languageListed);
    }

    @Override
    public DataResult<Language> getById(int id) {
        return new SuccessDataResult<Language>(this.languageDao.findById(id).orElse(null),Messages.languageGet);
    }
}
