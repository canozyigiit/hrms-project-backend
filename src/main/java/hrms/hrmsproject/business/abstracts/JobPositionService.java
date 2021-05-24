package hrms.hrmsproject.business.abstracts;


import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.JobPositon;

import java.util.List;

public interface JobPositionService {
    Result add(JobPositon jobPositon);

    DataResult<List<JobPositon>> getAll();

    DataResult<JobPositon> getById(int id);

    Result delete(JobPositon jobPositon);

}
