package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.Result;

public interface ConfirmEmployerService {


    Result confirmUser(String companyName,int systemPersonnelId);
}
