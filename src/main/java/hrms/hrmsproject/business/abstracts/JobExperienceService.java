package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.JobExperience;

import java.util.List;

public interface JobExperienceService {

    Result add(JobExperience jobExperience);

    DataResult<List<JobExperience>> getAll();

    DataResult<JobExperience> getById(int id);

    DataResult<List<JobExperience>> getByOrderByEndedDateDesc();
}
