package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.LanguageService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.dtoConverter.DtoConverterService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.core.utilities.results.SuccessDataResult;
import hrms.hrmsproject.core.utilities.results.SuccessResult;
import hrms.hrmsproject.dataAccess.abstracts.LanguageDao;
import hrms.hrmsproject.entities.concretes.Language;
import hrms.hrmsproject.entities.dtos.languageDtos.LanguageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageManager implements LanguageService {
    private LanguageDao languageDao;
    private DtoConverterService dtoConverterService;

    @Autowired
    public LanguageManager(LanguageDao languageDao,DtoConverterService dtoConverterService) {
        this.languageDao = languageDao;
        this.dtoConverterService = dtoConverterService;
    }

    @Override
    public Result add(LanguageDto languageDto) {
        this.languageDao.save((Language) this.dtoConverterService.dtoClassConverter(languageDto,Language.class));
        return new SuccessResult(Messages.languageAdded);
    }

    @Override
    public DataResult<List<LanguageDto>> getAll() {
        return new SuccessDataResult<List<LanguageDto>>(this.dtoConverterService.dtoConverter(languageDao.findAll(),LanguageDto.class),Messages.languageListed);
    }

    @Override
    public DataResult<Language> getById(int id) {
        return new SuccessDataResult<Language>(this.languageDao.findById(id).orElse(null),Messages.languageGet);
    }
}
