package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.JobWorkSpaceTypeService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.SuccessDataResult;
import hrms.hrmsproject.dataAccess.abstracts.JobWorkSpaceTypeDao;
import hrms.hrmsproject.entities.concretes.JobWorkSpaceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JobWorkSpaceTypeManager implements JobWorkSpaceTypeService {
    private JobWorkSpaceTypeDao jobStyleDao;

    @Autowired
    public JobWorkSpaceTypeManager(JobWorkSpaceTypeDao jobStyleDao) {
        this.jobStyleDao = jobStyleDao;
    }

    @Override
    public DataResult<List<JobWorkSpaceType>> getAll() {
        return new SuccessDataResult<List<JobWorkSpaceType>>(this.jobStyleDao.findAll());
    }
}
