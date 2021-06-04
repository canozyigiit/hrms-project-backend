package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.SchoolService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.dtoConverter.DtoConverterService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.core.utilities.results.SuccessDataResult;
import hrms.hrmsproject.core.utilities.results.SuccessResult;
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
}
