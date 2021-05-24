package hrms.hrmsproject.business.concrete;


import hrms.hrmsproject.business.abstracts.JobPositionService;
import hrms.hrmsproject.business.constans.Messages;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.core.utilities.results.SuccessDataResult;
import hrms.hrmsproject.core.utilities.results.SuccessResult;
import hrms.hrmsproject.dataAccess.abstracts.JobPositionDao;
import hrms.hrmsproject.entities.concretes.JobPositon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPositionManager implements JobPositionService {
    private JobPositionDao jobPositionDao;

    @Autowired
    public JobPositionManager(JobPositionDao jobPositionDao) {
        this.jobPositionDao = jobPositionDao;
    }

    @Override
    public Result add(JobPositon jobPositon) {
        jobPositionDao.save(jobPositon);
        return new SuccessResult(Messages.jobPositionAdded);
    }

    @Override
    public DataResult<List<JobPositon>> getAll() {
        return new SuccessDataResult<List<JobPositon>>(this.jobPositionDao.findAll(), Messages.jobPositionGetAll);
    }

    @Override
    public DataResult<JobPositon> getById(int id) {
        return new SuccessDataResult<JobPositon>(this.jobPositionDao.findById(id).orElse(null), Messages.jobPositionGet);
    }

    @Override
    public Result delete(JobPositon jobPositon) {
        jobPositionDao.delete(jobPositon);
        return new SuccessResult(Messages.jobPositionDeleted);
    }

}
