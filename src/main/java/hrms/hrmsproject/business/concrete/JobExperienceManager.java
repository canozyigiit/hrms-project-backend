package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.JobExperienceService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.business.BusinessRules;
import hrms.hrmsproject.core.utilities.dtoConverter.DtoConverterService;
import hrms.hrmsproject.core.utilities.results.*;
import hrms.hrmsproject.dataAccess.abstracts.JobExperienceDao;
import hrms.hrmsproject.dataAccess.abstracts.ResumeDao;
import hrms.hrmsproject.entities.concretes.JobExperience;
import hrms.hrmsproject.entities.concretes.Resume;
import hrms.hrmsproject.entities.dtos.jobExperienceDto.JobExperienceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobExperienceManager implements JobExperienceService {
    private JobExperienceDao jobExperienceDao;
    private DtoConverterService dtoConverterService;
    private ResumeDao resumeDao;

    @Autowired
    public JobExperienceManager(JobExperienceDao jobExperienceDao, ResumeDao resumeDao,DtoConverterService dtoConverterService) {
        this.jobExperienceDao = jobExperienceDao;
        this.dtoConverterService = dtoConverterService;
        this.resumeDao = resumeDao;
    }

    @Override
    public Result add(JobExperienceDto jobExperienceDto) {
        Result result = BusinessRules.Run(dateCheck(jobExperienceDto));
        if (result != null) {
            return result;
        }
        this.jobExperienceDao.save((JobExperience) this.dtoConverterService.dtoClassConverter(jobExperienceDto, JobExperience.class));
        return new SuccessResult(Messages.jobExperienceAdded);
    }

    @Override
    public Result update(JobExperienceDto jobExperienceDto) {
        JobExperience jobExperience = this.jobExperienceDao.findById(jobExperienceDto.getId()).orElse(null);
        Resume resume = this.resumeDao.findById(jobExperienceDto.getResumeId()).orElse(null);
        if (jobExperience !=null){
            jobExperience.setCompanyName(jobExperienceDto.getCompanyName());
            jobExperience.setPosition(jobExperienceDto.getPosition());
            jobExperience.setStartedDate(jobExperienceDto.getStartedDate());
            jobExperience.setEndedDate(jobExperienceDto.getEndedDate());
            jobExperience.setResume(resume);
            this.jobExperienceDao.save(jobExperience);
            return new SuccessResult("Bilgiler güncellendi");
        }
        return new ErrorResult("Bilgiler Güncellenemedi");

    }

    @Override
    public DataResult<List<JobExperienceDto>> getAll() {
        return new SuccessDataResult<List<JobExperienceDto>>(this.dtoConverterService.dtoConverter(this.jobExperienceDao.findAll(), JobExperienceDto.class), Messages.jobExperienceListed);
    }

    @Override
    public DataResult<JobExperience> getById(int id) {
        return new SuccessDataResult<JobExperience>(this.jobExperienceDao.findById(id).orElse(null), Messages.jobExperienceGet);
    }

    @Override
    public Result delete(int id) {
        this.jobExperienceDao.deleteById(id);
        return new SuccessResult("İş deneyimi silindi");
    }

    @Override
    public DataResult<List<JobExperienceDto>> findAllByResumeIdOrderByEndedDateDesc(int id) {
        return new SuccessDataResult<List<JobExperienceDto>>(this.dtoConverterService.dtoConverter(this.jobExperienceDao.findAllByResumeIdOrderByEndedDateDesc(id), JobExperienceDto.class));
    }

    private Result dateCheck(JobExperienceDto jobExperienceDto) {
        if (jobExperienceDto.getEndedDate().isBefore(jobExperienceDto.getStartedDate())) {
            return new ErrorResult("Başlangıç tarihi bitiş tarihinden önce olamaz");
        }
        return new SuccessResult();
    }
}
