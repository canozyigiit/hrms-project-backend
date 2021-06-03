package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.Result;

public interface ValidateService <T> {
    Result verifyData(T data);
}