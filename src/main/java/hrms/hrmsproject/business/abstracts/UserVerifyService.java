package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.Result;

public interface UserVerifyService {

    Result verifyUser(String userVerifyCode, int userId);
}
