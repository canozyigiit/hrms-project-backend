package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.JobTypeService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.SuccessDataResult;
import hrms.hrmsproject.dataAccess.abstracts.JobTypeDao;
import hrms.hrmsproject.entities.concretes.JobType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JobTypeManager implements JobTypeService {
    @Autowired
    private JobTypeDao jobTypeDao;

    public JobTypeManager(JobTypeDao jobTypeDao) {
        this.jobTypeDao = jobTypeDao;
    }

    @Override
    public DataResult<List<JobType>> getAll() {
        return new SuccessDataResult<List<JobType>>(this.jobTypeDao.findAll()) ;
    }
}
