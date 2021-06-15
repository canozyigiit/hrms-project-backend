package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.Result;


public interface ConfirmJobAdvertService {
    Result confirmJobAdvert(int jobAdvertId, int systemPersonnelId);
}
