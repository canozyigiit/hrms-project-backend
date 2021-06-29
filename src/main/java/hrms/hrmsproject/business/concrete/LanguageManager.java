package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.LanguageService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.dtoConverter.DtoConverterService;
import hrms.hrmsproject.core.utilities.results.*;
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
    public LanguageManager(LanguageDao languageDao, DtoConverterService dtoConverterService) {
        this.languageDao = languageDao;
        this.dtoConverterService = dtoConverterService;
    }

    @Override
    public Result add(LanguageDto languageDto) {
        this.languageDao.save((Language) this.dtoConverterService.dtoClassConverter(languageDto, Language.class));
        return new SuccessResult(Messages.languageAdded);
    }

    @Override
    public DataResult<List<LanguageDto>> getAll() {
        return new SuccessDataResult<List<LanguageDto>>(this.dtoConverterService.dtoConverter(languageDao.findAll(), LanguageDto.class), Messages.languageListed);
    }

    @Override
    public DataResult<Language> getById(int id) {
        return new SuccessDataResult<Language>(this.languageDao.findById(id).orElse(null), Messages.languageGet);
    }

    @Override
    public Result update(Language language) {
        Language lang = this.languageDao.findById(language.getId()).orElse(null);
        // System.out.println(language.toString());
        // Integer level =  Integer.valueOf(langLevel);
        if (language != null) {
            lang.setLanguageLevel(language.getLanguageLevel());
            lang.setLanguageName(language.getLanguageName());
            lang.setResume(language.getResume());
            this.languageDao.save(language);
            return new SuccessResult("Bilgiler Güncellendi");
        }
        return new ErrorResult("Bilgiler güncellenemedi");
    }

    @Override
    public Result delete(int id) {
        this.languageDao.deleteById(id);
        return new SuccessResult("Yabancı dil silindi");
    }

    @Override
    public DataResult<List<LanguageDto>> getByResumeId(int id) {
        return new SuccessDataResult<List<LanguageDto>>(this.dtoConverterService.dtoConverter(this.languageDao.getByResumeId(id), LanguageDto.class));
    }


}
