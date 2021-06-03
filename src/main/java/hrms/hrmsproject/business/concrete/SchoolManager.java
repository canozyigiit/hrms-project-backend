package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.SchoolService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.core.utilities.results.SuccessDataResult;
import hrms.hrmsproject.core.utilities.results.SuccessResult;
import hrms.hrmsproject.dataAccess.abstracts.SchoolDao;
import hrms.hrmsproject.entities.concretes.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolManager implements SchoolService {
    private SchoolDao schoolDao;

    @Autowired
    public SchoolManager(SchoolDao schoolDao) {
        this.schoolDao = schoolDao;
    }

    @Override
    public Result add(School school) {
        this.schoolDao.save(school);
        return new SuccessResult(Messages.schoolAdded);
    }

    @Override
    public DataResult<List<School>> getAll() {
        return new SuccessDataResult<List<School>>(this.schoolDao.findAll(),Messages.schoolListed);
    }

    @Override
    public DataResult<School> getById(int id) {
        return new SuccessDataResult<School>(this.schoolDao.findById(id).orElse(null),Messages.schoolGet);
    }
}
