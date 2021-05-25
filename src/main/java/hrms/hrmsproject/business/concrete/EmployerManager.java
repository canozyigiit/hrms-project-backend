package hrms.hrmsproject.business.concrete;


import hrms.hrmsproject.business.abstracts.EmployerService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.business.BusinessRules;
import hrms.hrmsproject.core.utilities.results.*;
import hrms.hrmsproject.dataAccess.abstracts.EmployerDao;
import hrms.hrmsproject.entities.concretes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmployerManager implements EmployerService {
    private EmployerDao employerDao;

    @Autowired
    public EmployerManager(EmployerDao employerDao) {
        this.employerDao = employerDao;
    }

    @Override
    public Result add(Employer employer) {
        Result result = BusinessRules.Run(checkIfEmployerDomain(employer), checkIfEmployerEmailExists(employer),
                employerEmailValid(employer.getEmail(), employer.getCompanyName()));
        if (result != null) {
            return result;
        }
        this.employerDao.save(employer);
        return new SuccessResult(Messages.employerAdded);

    }

    @Override
    public DataResult<List<Employer>> getAll() {
        return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(), Messages.employerGetAll);
    }

    @Override
    public DataResult<Employer> getById(int id) {
        return new SuccessDataResult<Employer>(this.employerDao.findById(id).orElse(null), Messages.employerGet);
    }

    @Override
    public Result delete(Employer employer) {
        this.employerDao.delete(employer);
        return new SuccessResult(Messages.employerDeleted);

    }

    private Result checkIfEmployerEmailExists(Employer employer) {
        var result = employerDao.findAllByEmail(employer.getEmail()).stream().count() != 0;
        if (result) {
            return new ErrorResult(Messages.employerEmailExists);
        }
        return new SuccessResult();
    }

    private Result checkIfEmployerDomain(Employer employer) {
        if (employer.getEmail() == null && employer.getCompanyName() ==
                null && employer.getPassword() == null && employer.getWebSite() == null && employer.getEmail() == null && employer.getPassword() == null) {
            return new ErrorResult(Messages.employerDomainCheck);
        }
        return new SuccessResult();
    }

    private Result employerEmailValid(String email, String companyName) {
        Pattern validEmail =
                Pattern.compile("^[A-Z0-9._%+-]+@[" + companyName + "]+\\.[A-Z]{2,6}$",
                        Pattern.CASE_INSENSITIVE);

        Matcher matcher = validEmail.matcher(email);
        if (!matcher.matches()) {
            return new ErrorResult(Messages.errorEmployerEmail);
        }

        return new SuccessResult();
    }
}
