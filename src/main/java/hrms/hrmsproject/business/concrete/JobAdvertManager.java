package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.JobAdvertService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.business.BusinessRules;
import hrms.hrmsproject.core.utilities.results.*;
import hrms.hrmsproject.dataAccess.abstracts.JobAdvertDao;
import hrms.hrmsproject.entities.concretes.JobAdvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobAdvertManager implements JobAdvertService {
    private JobAdvertDao jobAdvertDao;

    @Autowired
    public JobAdvertManager(JobAdvertDao jobAdvertDao) {
        this.jobAdvertDao = jobAdvertDao;
    }

    @Override
    public Result add(JobAdvert jobAdvert) {
        Result result = BusinessRules.Run(CheckIfNullField(jobAdvert),
                openPositionCountControl(jobAdvert), minSalaryEqualsMaxSalaryControl(jobAdvert), salaryControl(jobAdvert));
        if (result != null) {
            return result;
        }
        this.jobAdvertDao.save(jobAdvert);
        return new SuccessResult(Messages.JobAdvertAdded);
    }

    @Override
    public Result update(JobAdvert jobAdvert) {
        this.jobAdvertDao.save(jobAdvert);
        return new SuccessResult(Messages.JobAdvertUpdated);
    }

    @Override
    public Result delete(int id) {
        this.jobAdvertDao.deleteById(id);
        return new SuccessResult(Messages.JobAdvertDeleted);
    }

    @Override
    public Result changeOpenToClose(int id) {
        var jobAdvert = this.jobAdvertDao.getById(id);
        var result = BusinessRules.Run(checkJobPostIsExists(jobAdvert));
        if (result != null) {
            return result;
        }

        JobAdvert updateJobAdvert = jobAdvert;
        updateJobAdvert.setOpen(!updateJobAdvert.isOpen());
        this.jobAdvertDao.save(updateJobAdvert);
        return new SuccessResult(Messages.jobAdvertClosed);
    }

    @Override
    public DataResult<JobAdvert> getById(int id) {
        return new SuccessDataResult<JobAdvert>(this.jobAdvertDao.getById(id), Messages.JobAdvertGet);
    }

    @Override
    public DataResult<List<JobAdvert>> getAll() {
        return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.findAll(), Messages.JobAdvertListed);
    }

    @Override
    public DataResult<List<JobAdvert>> getByisOpenTrueOrderByCreatedDateDesc() {
        return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getByisOpenTrueOrderByCreatedDateDesc());
    }

    @Override
    public DataResult<List<JobAdvert>> getAllOpenJobAdvertList() {
        return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getAllOpenJobAdvertList(), Messages.allActivePositionsListed);
    }

    @Override
    public DataResult<List<JobAdvert>> getByisOpenTrueAndEmployer_Id(int id) {
        return new SuccessDataResult<List<JobAdvert>>(this.jobAdvertDao.getByisOpenTrueAndEmployer_Id(id), Messages.allActivePositonsInCompany);
    }


//************************************************************************************************************
    private Result CheckIfNullField(JobAdvert jobAdvert) {
        if (jobAdvert.getJobPosition() == null || jobAdvert.getDescription() == null || jobAdvert.getCity() == null
                || jobAdvert.getOpenPositionCount() == 0) {
            return new ErrorResult(Messages.jobAdvertFieldCheck);
        }
        return new SuccessResult();
    }

    private Result openPositionCountControl(JobAdvert jobAdvert) {
        if (jobAdvert.getOpenPositionCount() < 1) {
            return new ErrorResult(Messages.openPositionCountError);
        }
        return new SuccessResult();
    }

    private Result salaryControl(JobAdvert jobAdvert) {
        if (jobAdvert.getSalaryMin() > jobAdvert.getSalaryMax()) {
            return new ErrorResult(Messages.salaryError);
        }
        return new SuccessResult();
    }

    private Result minSalaryEqualsMaxSalaryControl(JobAdvert jobAdvert) {
        if (jobAdvert.getSalaryMin() == jobAdvert.getSalaryMax()) {
            return new ErrorResult(Messages.mixSalaryEqualsMaxSalary);
        }
        return new SuccessResult();
    }

    private Result checkJobPostIsExists(JobAdvert jobAdvert) {
        if (jobAdvert == null) {
            return new ErrorResult(Messages.jobAdvertNotFound);
        }
        return new SuccessResult();
    }

}
