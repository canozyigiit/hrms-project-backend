package hrms.hrmsproject.business.concrete;


import hrms.hrmsproject.business.abstracts.SystemPersonnelService;
import hrms.hrmsproject.business.constans.Messages;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.core.utilities.results.SuccessDataResult;
import hrms.hrmsproject.core.utilities.results.SuccessResult;
import hrms.hrmsproject.dataAccess.abstracts.SystemPersonnelDao;
import hrms.hrmsproject.entities.concretes.SystemPersonnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemPersonnelManager implements SystemPersonnelService {
    private SystemPersonnelDao systemPersonnelDao;

    @Autowired
    public SystemPersonnelManager(SystemPersonnelDao systemPersonnelDao) {
        this.systemPersonnelDao = systemPersonnelDao;
    }

    @Override
    public Result add(SystemPersonnel systemPersonnel) {
        this.systemPersonnelDao.save(systemPersonnel);
        return new SuccessResult(Messages.systemPersonnelAdded);
    }

    @Override
    public DataResult<List<SystemPersonnel>> getAll() {
        return new SuccessDataResult<List<SystemPersonnel>>(this.systemPersonnelDao.findAll(), Messages.systemPersonnelGetAll);
    }

    @Override
    public DataResult<SystemPersonnel> getById(int id) {
        return new SuccessDataResult<SystemPersonnel>(this.systemPersonnelDao.findById(id).orElse(null), Messages.systemPersonnelGet);
    }

    @Override
    public Result delete(SystemPersonnel systemPersonnel) {
        this.systemPersonnelDao.delete(systemPersonnel);
        return new SuccessResult(Messages.systemPersonnelDeleted);
    }

}
