package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.JobAdvertService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.business.BusinessRules;
import hrms.hrmsproject.core.utilities.dtoConverter.DtoConverterService;
import hrms.hrmsproject.core.utilities.results.*;
import hrms.hrmsproject.dataAccess.abstracts.JobAdvertDao;
import hrms.hrmsproject.entities.concretes.JobAdvert;
import hrms.hrmsproject.entities.dtos.jobAdvertDtos.JobAdvertAddDto;
import hrms.hrmsproject.entities.dtos.jobAdvertDtos.JobAdvertDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobAdvertManager implements JobAdvertService {
    private JobAdvertDao jobAdvertDao;

    private DtoConverterService dtoConverterService;

    @Autowired
    public JobAdvertManager(JobAdvertDao jobAdvertDao,
                             DtoConverterService dtoConverterService) {
        this.jobAdvertDao = jobAdvertDao;
        this.dtoConverterService = dtoConverterService;
    }

    @Override
    public Result add(JobAdvertAddDto jobAdvertAddDto) {
        Result result = BusinessRules.Run(salaryControl(jobAdvertAddDto));
        if (result != null) {
            return result;
        }
        this.jobAdvertDao.save((JobAdvert) dtoConverterService.dtoClassConverter(jobAdvertAddDto,JobAdvert.class));
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
    public DataResult<JobAdvertDto> getById(int id) {
        return new SuccessDataResult<JobAdvertDto>((JobAdvertDto) this.dtoConverterService.dtoClassConverter(jobAdvertDao.findById(id).orElse(null),JobAdvertDto.class), Messages.JobAdvertGet);
    }

    @Override
    public DataResult<List<JobAdvertDto>> getAll() {
        return new SuccessDataResult<List<JobAdvertDto>>(this.dtoConverterService.dtoConverter(this.jobAdvertDao.findAll(),JobAdvertDto.class), Messages.JobAdvertListed);
    }

    @Override
    public DataResult<List<JobAdvertDto>> getByisConfirmedFalse() {
        return new SuccessDataResult<List<JobAdvertDto>>(this.dtoConverterService.dtoConverter(this.jobAdvertDao.getByisConfirmedFalse(),JobAdvertDto.class));
    }

    @Override
    public DataResult<List<JobAdvertDto>> getAll(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1,7);
        return new SuccessDataResult<List<JobAdvertDto>>(this.dtoConverterService.dtoConverter(this.jobAdvertDao.getByisOpenTrue(pageable),JobAdvertDto.class));
    }

    @Override
    public DataResult<List<JobAdvertDto>> getAllisOpenTrueAndCity_Id(int id) {
        return new SuccessDataResult<List<JobAdvertDto>>(this.dtoConverterService.dtoConverter(this.jobAdvertDao.getByisOpenTrueAndCity_Id(id),JobAdvertDto.class));
    }

    @Override
    public DataResult<List<JobAdvertDto>> getByisConfirmedTrue() {
        return new SuccessDataResult<List<JobAdvertDto>>(this.dtoConverterService.dtoConverter(this.jobAdvertDao.getByisConfirmedTrue(),JobAdvertDto.class));
    }


    @Override
    public DataResult<List<JobAdvertDto>> getByisOpenTrueOrderByCreatedDateDesc() {
        return new SuccessDataResult<List<JobAdvertDto>>(this.dtoConverterService.dtoConverter(jobAdvertDao.getByisOpenTrueOrderByCreatedDateDesc(),JobAdvertDto.class));
    }

    @Override
    public DataResult<List<JobAdvertDto>> getAllOpenJobAdvertList() {
        return new SuccessDataResult<List<JobAdvertDto>>(this.dtoConverterService.dtoConverter(this.jobAdvertDao.getByisOpenTrue(),JobAdvertDto.class), Messages.allActivePositionsListed);
    }

    @Override
    public DataResult<List<JobAdvertDto>> getByisOpenTrueAndEmployer_Id(int id) {
        return new SuccessDataResult<List<JobAdvertDto>>(this.dtoConverterService.dtoConverter(jobAdvertDao.getByisOpenTrueAndEmployer_Id(id),JobAdvertDto.class), Messages.allActivePositonsInCompany);
    }

    @Override
    public DataResult<List<JobAdvertDto>> getByisOpenTrueAndJobWorkSpaceType_Name(String name,int pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1,7);
        return new SuccessDataResult<List<JobAdvertDto>>(this.dtoConverterService.dtoConverter(jobAdvertDao.getByisOpenTrueAndJobWorkSpaceType_Name(name,pageable),JobAdvertDto.class));

    }

    @Override
    public DataResult<List<JobAdvertDto>> getByisOpenTrueAndJobType_Type(String type,int pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1,7);
        return new SuccessDataResult<List<JobAdvertDto>>(this.dtoConverterService.dtoConverter(jobAdvertDao.getByisOpenTrueAndJobType_Type(type,pageable),JobAdvertDto.class));

    }


//************************************************************************************************************

    private Result salaryControl(JobAdvertAddDto jobAdvertAddDto) {
        if (jobAdvertAddDto.getSalaryMin() > jobAdvertAddDto.getSalaryMax()) {
            return new ErrorResult(Messages.salaryError);
        } else if (jobAdvertAddDto.getSalaryMin() == jobAdvertAddDto.getSalaryMax()) {
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
