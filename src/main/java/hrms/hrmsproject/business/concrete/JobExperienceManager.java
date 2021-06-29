package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.JobExperienceService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.business.BusinessRules;
import hrms.hrmsproject.core.utilities.dtoConverter.DtoConverterService;
import hrms.hrmsproject.core.utilities.results.*;
import hrms.hrmsproject.dataAccess.abstracts.JobExperienceDao;
import hrms.hrmsproject.entities.concretes.JobExperience;
import hrms.hrmsproject.entities.dtos.jobExperienceDto.JobExperienceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JobExperienceManager implements JobExperienceService {
    private JobExperienceDao jobExperienceDao;
    private DtoConverterService dtoConverterService;

    @Autowired
    public JobExperienceManager(JobExperienceDao jobExperienceDao, DtoConverterService dtoConverterService) {
        this.jobExperienceDao = jobExperienceDao;
        this.dtoConverterService = dtoConverterService;
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
    public Result update(int id, String companyName, LocalDate endedDate, String position, LocalDate startedDate) {
        JobExperience jobExperience = this.jobExperienceDao.findById(id).orElse(null);
        if (companyName != null) {
            jobExperience.setCompanyName(companyName);
            this.jobExperienceDao.save(jobExperience);
            return new SuccessResult("Şirket Adı Güncellendi");
        }
        if (endedDate != null) {
            jobExperience.setEndedDate(endedDate);
            this.jobExperienceDao.save(jobExperience);
            return new SuccessResult("Bitiş Tarihi Güncellendi");
        }
        if (position != null) {
            jobExperience.setPosition(position);
            this.jobExperienceDao.save(jobExperience);
            return new SuccessResult("Pozisyon Güncellendi");
        }
        if (startedDate != null) {
            jobExperience.setStartedDate(startedDate);
            this.jobExperienceDao.save(jobExperience);
            return new SuccessResult("Başlama Tarihi Güncellendi");
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
