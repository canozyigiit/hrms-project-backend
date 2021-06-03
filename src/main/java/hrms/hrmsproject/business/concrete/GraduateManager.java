package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.GraduateService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.core.utilities.results.SuccessDataResult;
import hrms.hrmsproject.core.utilities.results.SuccessResult;
import hrms.hrmsproject.dataAccess.abstracts.GraduateDao;
import hrms.hrmsproject.entities.concretes.Graduate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraduateManager implements GraduateService {
    private GraduateDao graduateDao;

    @Autowired
    public GraduateManager(GraduateDao graduateDao) {
        this.graduateDao = graduateDao;
    }

    @Override
    public Result add(Graduate graduate) {
        this.graduateDao.save(graduate);
        return new SuccessResult(Messages.graduateAdded);
    }

    @Override
    public DataResult<List<Graduate>> getAll() {
        return new SuccessDataResult<List<Graduate>>(this.graduateDao.findAll(),Messages.graduateListed);
    }

    @Override
    public DataResult<Graduate> getById(int id) {
        return new SuccessDataResult<Graduate>(this.graduateDao.findById(id).orElse(null),Messages.graduateGet);
    }
}
