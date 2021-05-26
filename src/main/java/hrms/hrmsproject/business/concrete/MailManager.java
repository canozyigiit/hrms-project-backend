package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.MailService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.core.utilities.results.SuccessResult;
import org.springframework.stereotype.Service;

@Service
public class MailManager implements MailService {
    @Override
    public Result send(String email) {
        return new SuccessResult(Messages.mailVerification + email);
    }
}
