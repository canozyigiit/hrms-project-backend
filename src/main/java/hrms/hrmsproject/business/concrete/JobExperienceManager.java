package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.JobExperienceService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.core.utilities.results.SuccessDataResult;
import hrms.hrmsproject.core.utilities.results.SuccessResult;
import hrms.hrmsproject.dataAccess.abstracts.JobExperienceDao;
import hrms.hrmsproject.entities.concretes.JobExperience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobExperienceManager implements JobExperienceService {
    private JobExperienceDao jobExperienceDao;

    @Autowired
    public JobExperienceManager(JobExperienceDao jobExperienceDao) {
        this.jobExperienceDao = jobExperienceDao;
    }

    @Override
    public Result add(JobExperience jobExperience) {
        this.jobExperienceDao.save(jobExperience);
        return new SuccessResult(Messages.jobExperienceAdded);
    }

    @Override
    public DataResult<List<JobExperience>> getAll() {
        return new SuccessDataResult<List<JobExperience>>(this.jobExperienceDao.findAll(),Messages.jobExperienceListed);
    }

    @Override
    public DataResult<JobExperience> getById(int id) {
        return new SuccessDataResult<JobExperience>(this.jobExperienceDao.findById(id).orElse(null),Messages.jobExperienceGet);
    }

    @Override
    public DataResult<List<JobExperience>> getByOrderByEndedDateDesc() {
        return new SuccessDataResult<List<JobExperience>>(this.jobExperienceDao.getByOrderByEndedDateDesc());
    }
}
