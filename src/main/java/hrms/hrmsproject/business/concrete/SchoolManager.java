package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.SchoolService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.business.BusinessRules;
import hrms.hrmsproject.core.utilities.dtoConverter.DtoConverterService;
import hrms.hrmsproject.core.utilities.results.*;
import hrms.hrmsproject.dataAccess.abstracts.SchoolDao;
import hrms.hrmsproject.entities.concretes.School;
import hrms.hrmsproject.entities.dtos.schoolDtos.SchoolDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolManager implements SchoolService {
    private SchoolDao schoolDao;
    private DtoConverterService dtoConverterService;

    @Autowired
    public SchoolManager(SchoolDao schoolDao,DtoConverterService dtoConverterService) {
        this.schoolDao = schoolDao;
        this.dtoConverterService = dtoConverterService;
    }

    @Override
    public Result add(SchoolDto schoolDto) {
        Result result = BusinessRules.Run(dateCheck(schoolDto));
        if (result != null) {
            return result;
        }
        this.schoolDao.save((School) dtoConverterService.dtoClassConverter(schoolDto, School.class));
        return new SuccessResult(Messages.schoolAdded);
    }

    @Override
    public DataResult<List<SchoolDto>> getAll() {
        return new SuccessDataResult<List<SchoolDto>>(this.dtoConverterService.dtoConverter(schoolDao.findAll(),SchoolDto.class) ,Messages.schoolListed);
    }

    @Override
    public DataResult<School> getById(int id) {
        return new SuccessDataResult<School>( this.schoolDao.findById(id).orElse(null),Messages.schoolGet);
    }

    @Override
    public DataResult<List<SchoolDto>> findAllByResumeIdOrderByEndedDateDesc(int id) {
        return new SuccessDataResult<List<SchoolDto>>(dtoConverterService.dtoConverter(schoolDao.findAllByResumeIdOrderByEndedDateDesc(id), SchoolDto.class));

    }

    private Result dateCheck(SchoolDto schoolDto){
        if(schoolDto.getEndedDate().isBefore(schoolDto.getStartedDate())){
            return new ErrorResult("Başlangıç tarihi bitiş tarihinden önce olamaz");
        }
        return new SuccessResult();
    }
}
