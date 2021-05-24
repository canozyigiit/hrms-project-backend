package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.Result;

public interface MailService {
    Result send(String email);
}
