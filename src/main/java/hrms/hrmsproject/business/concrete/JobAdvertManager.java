package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.JobAdvertService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.business.BusinessRules;
import hrms.hrmsproject.core.utilities.results.*;
import hrms.hrmsproject.dataAccess.abstracts.JobAdvertDao;
import hrms.hrmsproject.entities.concretes.JobAdvert;
import hrms.hrmsproject.entities.dtos.jobAdvertDtos.JobAdvertDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobAdvertManager implements JobAdvertService {
    private JobAdvertDao jobAdvertDao;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public JobAdvertManager(JobAdvertDao jobAdvertDao) {
        this.jobAdvertDao = jobAdvertDao;
    }

    @Override
    public Result add(JobAdvert jobAdvert) {
        Result result = BusinessRules.Run(salaryControl(jobAdvert));
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

//    @Override
//    public DataResult<List<JobAdvertDto>> getAllDto() {
//        List<JobAdvertDto> jobAdvertDtos = new ArrayList<JobAdvertDto>();
//        this.jobAdvertDao.findAll().stream().forEach(adv -> {
//            JobAdvertDto jDto = modelMapper.map(adv, JobAdvertDto.class);
//            jDto.setCompanyName(adv.getEmployer().getCompanyName());
//           // jDto.setName(adv.getJobPosition().getName());
//            jobAdvertDtos.add(jDto);
//        });
//        return new SuccessDataResult<List<JobAdvertDto>>(jobAdvertDtos, "Veriler Listelendi");
//    }

    @Override
    public DataResult<List<JobAdvertDto>> getByisOpenTrueOrderByCreatedDateDesc() {
        return new SuccessDataResult<List<JobAdvertDto>>(this.dtoGenerator(jobAdvertDao.getByisOpenTrueOrderByCreatedDateDesc()));
    }

    @Override
    public DataResult<List<JobAdvertDto>> getAllOpenJobAdvertList() {
        return new SuccessDataResult<List<JobAdvertDto>>(this.dtoGenerator(this.jobAdvertDao.getByisOpenTrue()), Messages.allActivePositionsListed);
    }

    @Override
    public DataResult<List<JobAdvertDto>> getByisOpenTrueAndEmployer_Id(int id) {
        return new SuccessDataResult<List<JobAdvertDto>>(this.dtoGenerator(jobAdvertDao.getByisOpenTrueAndEmployer_Id(id)), Messages.allActivePositonsInCompany);
    }


//************************************************************************************************************

    private Result salaryControl(JobAdvert jobAdvert) {
        if (jobAdvert.getSalaryMin() > jobAdvert.getSalaryMax()) {
            return new ErrorResult(Messages.salaryError);
        } else if (jobAdvert.getSalaryMin() == jobAdvert.getSalaryMax()) {
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

    private List<JobAdvertDto> dtoGenerator(List<JobAdvert> jobAdvert) {
        List<JobAdvertDto> jobAdvertDtos = new ArrayList<JobAdvertDto>();
        jobAdvert.forEach(item -> {
            JobAdvertDto dto = modelMapper.map(item, JobAdvertDto.class);
            dto.setCompanyName(item.getEmployer().getCompanyName());
            dto.setName(item.getJobPosition().getName());
            jobAdvertDtos.add(dto);
        });
        return jobAdvertDtos;

    }

}
