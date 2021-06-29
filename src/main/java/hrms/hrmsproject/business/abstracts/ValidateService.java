package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.entities.User;
import hrms.hrmsproject.core.utilities.results.Result;

public interface ValidateService  {
    Result verifyData(int userId);
    Result verifyUser(User user);
}