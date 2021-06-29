package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.entities.concretes.CurrentEmployer;

public interface CurrentEmployerService {


    DataResult<CurrentEmployer> getByEmployerId(int id);
}
