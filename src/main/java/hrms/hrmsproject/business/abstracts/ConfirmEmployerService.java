package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.Result;

public interface ConfirmEmployerService {


    Result confirmEmployer(String companyName, int systemPersonnelId);
    Result confirmUpdateEmployer(String companyName, int systemPersonnelId);

}
