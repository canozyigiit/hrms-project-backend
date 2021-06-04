package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.JobExperienceService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.dtoConverter.DtoConverterService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.core.utilities.results.SuccessDataResult;
import hrms.hrmsproject.core.utilities.results.SuccessResult;
import hrms.hrmsproject.dataAccess.abstracts.JobExperienceDao;
import hrms.hrmsproject.entities.concretes.JobExperience;
import hrms.hrmsproject.entities.dtos.jobExperienceDto.JobExperienceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobExperienceManager implements JobExperienceService {
    private JobExperienceDao jobExperienceDao;
    private DtoConverterService dtoConverterService;

    @Autowired
    public JobExperienceManager(JobExperienceDao jobExperienceDao,DtoConverterService dtoConverterService) {
        this.jobExperienceDao = jobExperienceDao;
        this.dtoConverterService = dtoConverterService;
    }

    @Override
    public Result add(JobExperienceDto jobExperienceDto) {
        this.jobExperienceDao.save((JobExperience) this.dtoConverterService.dtoClassConverter(jobExperienceDto,JobExperience.class));
        return new SuccessResult(Messages.jobExperienceAdded);
    }

    @Override
    public DataResult<List<JobExperienceDto>> getAll() {
        return new SuccessDataResult<List<JobExperienceDto>>(this.dtoConverterService.dtoConverter(this.jobExperienceDao.findAll(),JobExperienceDto.class),Messages.jobExperienceListed);
    }

    @Override
    public DataResult<JobExperience> getById(int id) {
        return new SuccessDataResult<JobExperience>(this.jobExperienceDao.findById(id).orElse(null),Messages.jobExperienceGet);
    }

    @Override
    public DataResult<List<JobExperienceDto>> getByOrderByEndedDateDesc() {
        return new SuccessDataResult<List<JobExperienceDto>>(this.dtoConverterService.dtoConverter(this.jobExperienceDao.getByOrderByEndedDateDesc(),JobExperienceDto.class));
    }
}