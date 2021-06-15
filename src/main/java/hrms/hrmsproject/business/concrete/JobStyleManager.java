package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.JobStyleService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.SuccessDataResult;
import hrms.hrmsproject.dataAccess.abstracts.JopStyleDao;
import hrms.hrmsproject.entities.concretes.JobStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JobStyleManager implements JobStyleService {
    private JopStyleDao jobStyleDao;

    @Autowired
    public JobStyleManager(JopStyleDao jobStyleDao) {
        this.jobStyleDao = jobStyleDao;
    }

    @Override
    public DataResult<List<JobStyle>> getAll() {
        return new SuccessDataResult<List<JobStyle>>(this.jobStyleDao.findAll());
    }
}
