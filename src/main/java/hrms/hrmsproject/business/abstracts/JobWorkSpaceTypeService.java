package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.entities.concretes.JobWorkSpaceType;

import java.util.List;

public interface JobWorkSpaceTypeService {
    DataResult<List<JobWorkSpaceType>> getAll();
}
