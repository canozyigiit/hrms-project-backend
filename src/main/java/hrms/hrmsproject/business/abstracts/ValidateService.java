package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.Result;

public interface ValidateService  {
    Result verifyData(int userId);
}