package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.JobAdvert;
import hrms.hrmsproject.entities.dtos.jobAdvertDtos.JobAdvertAddDto;
import hrms.hrmsproject.entities.dtos.jobAdvertDtos.JobAdvertDto;

import java.util.List;

public interface JobAdvertService {
    Result add(JobAdvertAddDto jobAdvertAddDto);
    Result update(JobAdvert jobAdvert);
    Result delete(int id);
    Result changeOpenToClose(int id);
    DataResult<JobAdvertDto> getById(int id);
    DataResult<List<JobAdvertDto>> getAll();
    DataResult<List<JobAdvertDto>> getAllisOpenTrueAndCity_Id(int id);
    //DataResult<List<JobAdvertDto>> getAllDto();
    DataResult<List<JobAdvertDto>> getByisConfirmedTrue();
    DataResult<List<JobAdvertDto>>getByisOpenTrueOrderByCreatedDateDesc();
    DataResult<List<JobAdvertDto>> getAllOpenJobAdvertList();
    DataResult<List<JobAdvertDto>> getByisOpenTrueAndEmployer_Id(int id);
}
