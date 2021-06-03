package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.TechnologyService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.core.utilities.results.SuccessDataResult;
import hrms.hrmsproject.core.utilities.results.SuccessResult;
import hrms.hrmsproject.dataAccess.abstracts.TechnologyDao;
import hrms.hrmsproject.entities.concretes.Technology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyManager implements TechnologyService {
    private TechnologyDao technologyDao;

    @Autowired
    public TechnologyManager(TechnologyDao technologyDao) {
        this.technologyDao = technologyDao;
    }

    @Override
    public Result add(Technology technology) {
        this.technologyDao.save(technology);
        return new SuccessResult(Messages.technologyAdded);
    }

    @Override
    public DataResult<List<Technology>> getAll() {
        return new SuccessDataResult<List<Technology>>(this.technologyDao.findAll(),Messages.technologyListed);
    }

    @Override
    public DataResult<Technology> getById(int id) {
        return new SuccessDataResult<Technology>(this.technologyDao.findById(id).orElse(null));
    }
}
