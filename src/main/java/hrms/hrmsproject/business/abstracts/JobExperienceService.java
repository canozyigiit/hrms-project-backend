package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.JobExperience;
import hrms.hrmsproject.entities.dtos.jobExperienceDto.JobExperienceDto;

import java.time.LocalDate;
import java.util.List;

public interface JobExperienceService {

    Result add(JobExperienceDto jobExperienceDto);
    Result update(int id, String companyName, LocalDate endedDate,String position,LocalDate startedDate);

    DataResult<List<JobExperienceDto>> getAll();

    DataResult<JobExperience> getById(int id);
    Result delete (int id);

    DataResult<List<JobExperienceDto>> findAllByResumeIdOrderByEndedDateDesc(int id);
}
