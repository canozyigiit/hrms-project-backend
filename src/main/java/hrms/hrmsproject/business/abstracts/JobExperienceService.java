package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.JobExperience;
import hrms.hrmsproject.entities.dtos.jobExperienceDto.JobExperienceDto;

import java.util.List;

public interface JobExperienceService {

    Result add(JobExperienceDto jobExperienceDto);
    Result update(JobExperienceDto jobExperienceDto);

    DataResult<List<JobExperienceDto>> getAll();

    DataResult<JobExperience> getById(int id);
    Result delete (int id);

    DataResult<List<JobExperienceDto>> findAllByResumeIdOrderByEndedDateDesc(int id);
}
