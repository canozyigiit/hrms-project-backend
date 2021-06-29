package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.CurrentEmployerService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.SuccessDataResult;
import hrms.hrmsproject.dataAccess.abstracts.CurrentEmployerDao;
import hrms.hrmsproject.entities.concretes.CurrentEmployer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrentEmployerManager implements CurrentEmployerService {
    private CurrentEmployerDao currentEmployerDao;

    @Autowired
    public CurrentEmployerManager(CurrentEmployerDao currentEmployerDao) {
        this.currentEmployerDao = currentEmployerDao;
    }

    @Override
    public DataResult<CurrentEmployer> getByEmployerId(int id) {
        return new SuccessDataResult<CurrentEmployer>(this.currentEmployerDao.getByEmployerId(id));
    }
}
