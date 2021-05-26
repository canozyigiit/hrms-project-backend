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

@Service
public class EmployerManager implements EmployerService {
    private EmployerDao employerDao;

    @Autowired
    public EmployerManager(EmployerDao employerDao) {
        this.employerDao = employerDao;
    }

    @Override
    public Result add(Employer employer) {
        Result result = BusinessRules.Run(checkIfEmployerFields(employer), checkIfEmployerEmailExists(employer),
                checkEmailIsCompatibleWithDomain(employer.getEmail(),employer.getCompanyName()));
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



    private Result checkIfEmployerFields(Employer employer) {
        if (employer.getEmail() == "" && employer.getCompanyName() == ""
                && employer.getPassword() == "" && employer.getWebSite() == "" && employer.getEmail() == "" && employer.getPassword() == "") {
            return new ErrorResult(Messages.employerFieldCheck);
        }
        return new SuccessResult();
    }

//    private Result employerEmailValid(String email, String companyName) {
//        Pattern validEmail =
//                Pattern.compile("^[A-Z0-9._%+-]+@[" + companyName + "]+\\.[A-Z]{2,6}$",
//                        Pattern.CASE_INSENSITIVE);
//
//        Matcher matcher = validEmail.matcher(email);
//        if (!matcher.matches()) {
//            return new ErrorResult(Messages.errorEmployerEmail);
//        }
//
//        return new SuccessResult();
//    }
    private Result checkEmailIsCompatibleWithDomain(String email, String companyName){

        String[] isEmailCompatible = email.split("@", 2);
        String webSite = companyName.substring(4);

        if (!isEmailCompatible[1].equals(webSite)){
            return new ErrorResult(Messages.errorEmployerEmail);
        }

        return new SuccessResult();
    }
}
