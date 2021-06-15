package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.entities.concretes.JobStyle;

import java.util.List;

public interface JobStyleService {
    DataResult<List<JobStyle>> getAll();
}
