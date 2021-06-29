package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.SchoolService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.business.BusinessRules;
import hrms.hrmsproject.core.utilities.dtoConverter.DtoConverterService;
import hrms.hrmsproject.core.utilities.results.*;
import hrms.hrmsproject.dataAccess.abstracts.GraduateDao;
import hrms.hrmsproject.dataAccess.abstracts.ResumeDao;
import hrms.hrmsproject.dataAccess.abstracts.SchoolDao;
import hrms.hrmsproject.entities.concretes.Graduate;
import hrms.hrmsproject.entities.concretes.Resume;
import hrms.hrmsproject.entities.concretes.School;
import hrms.hrmsproject.entities.dtos.schoolDtos.SchoolDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolManager implements SchoolService {
    private SchoolDao schoolDao;
    private GraduateDao graduateDao;
    private DtoConverterService dtoConverterService;

    private ResumeDao resumeDao;
    @Autowired
    public SchoolManager(SchoolDao schoolDao,DtoConverterService dtoConverterService,
                         GraduateDao graduateDao,
                         ResumeDao resumeDao) {
        this.schoolDao = schoolDao;
        this.dtoConverterService = dtoConverterService;
        this.resumeDao = resumeDao;
        this.graduateDao = graduateDao;
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
    public Result delete(int id) {
        this.schoolDao.deleteById(id);
        return new SuccessResult("Okul silindi");
    }

    @Override
    public Result update(SchoolDto schoolDto) {
        School school = this.schoolDao.findById(schoolDto.getId()).orElse(null);
        Graduate graduate = this.graduateDao.findById(schoolDto.getId()).orElse(null);
        Resume resume = this.resumeDao.findById(schoolDto.getResumeId()).orElse(null);
        if (school !=null){
            school.setSchoolName(schoolDto.getSchoolName());
            school.setResume(resume);
            school.setSchoolDepartment(schoolDto.getSchoolDepartment());
            school.setGraduate(graduate);
            school.setStartedDate(schoolDto.getStartedDate());
            school.setEndedDate(schoolDto.getEndedDate());
            schoolDao.save(school);
            return new SuccessResult("Bilgiler Güncellendi");
        }

        return new ErrorResult("Bilgiler Güncellenemedi");
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
