package hrms.hrmsproject.business.abstracts;


import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.JobPosition;

import java.util.List;

public interface JobPositionService {
    Result add(JobPosition jobPosition);

    DataResult<List<JobPosition>> getAll();

    DataResult<JobPosition> getById(int id);

    Result delete(JobPosition jobPosition);

}
