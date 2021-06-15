package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.entities.concretes.JobType;

import java.util.List;

public interface JobTypeService {

    DataResult<List<JobType>> getAll();
}
