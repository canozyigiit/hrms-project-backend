package hrms.hrmsproject.business.concrete;


import hrms.hrmsproject.business.abstracts.JobPositionService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.business.BusinessRules;
import hrms.hrmsproject.core.utilities.results.*;
import hrms.hrmsproject.dataAccess.abstracts.JobPositionDao;
import hrms.hrmsproject.entities.concretes.JobPosition;
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
    public Result add(JobPosition jobPosition) {
        Result result = BusinessRules.Run(checkIfJobPositonNameExists(jobPosition),checkIfJobPositionNameLength(jobPosition));
        if (result != null) {
            return result;
        }
        jobPositionDao.save(jobPosition);
        return new SuccessResult(Messages.jobPositionAdded);
    }

    @Override
    public DataResult<List<JobPosition>> getAll() {
        return new SuccessDataResult<List<JobPosition>>(this.jobPositionDao.findAll(), Messages.jobPositionGetAll);
    }

    @Override
    public DataResult<JobPosition> getById(int id) {
        return new SuccessDataResult<JobPosition>(this.jobPositionDao.findById(id).orElse(null), Messages.jobPositionGet);
    }

    @Override
    public Result delete(JobPosition jobPosition) {
        jobPositionDao.delete(jobPosition);
        return new SuccessResult(Messages.jobPositionDeleted);
    }


    //************************************************************************************************************
    
    private Result checkIfJobPositonNameExists(JobPosition jobPosition) {
        var result = jobPositionDao.findAllByName(jobPosition.getName()).stream().count()!=0;
        if (result) {
            return new ErrorResult(Messages.jobPositionExists);
        }
        return new SuccessResult();
    }//Bu isimde başka iş pozisyonu var mı?
    
    private Result checkIfJobPositionNameLength(JobPosition jobPosition){
        if (jobPosition.getName().length()<3){
            return new ErrorResult(Messages.jobPositionNameLengthError);
        }
        return new SuccessResult();
    }//Pozisyon ismi 3 karakterden uzun olsun(test amaçlı)

}
