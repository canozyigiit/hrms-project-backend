package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.ConfirmEmployerService;
import hrms.hrmsproject.core.utilities.results.ErrorResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.core.utilities.results.SuccessResult;
import hrms.hrmsproject.dataAccess.abstracts.ConfirmEmployerDao;
import hrms.hrmsproject.dataAccess.abstracts.EmployerDao;
import hrms.hrmsproject.dataAccess.abstracts.SystemPersonnelDao;
import hrms.hrmsproject.entities.concretes.ConfirmEmployer;
import hrms.hrmsproject.entities.concretes.Employer;
import hrms.hrmsproject.entities.concretes.SystemPersonnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ConfirmEmployerManager implements ConfirmEmployerService {
    private ConfirmEmployerDao confirmEmployerDao;
    private EmployerDao employerDao;
    private SystemPersonnelDao systemPersonnelDao;

    @Autowired
    public ConfirmEmployerManager(ConfirmEmployerDao confirmEmployerDao,EmployerDao employerDao,SystemPersonnelDao systemPersonnelDao) {
        this.confirmEmployerDao = confirmEmployerDao;
        this.employerDao = employerDao;
        this.systemPersonnelDao = systemPersonnelDao;
    }



    @Override
    public Result confirmEmployer(String companyName, int systemPersonnelId) {

        if (!employerDao.existsByCompanyName(companyName)) {
            return new ErrorResult("Şirket bulunamadı");
        }

//        if (employerDao.getByCompanyName(companyName).isConfirmed()) {
//            return new ErrorResult("Bu şirket zaten onaylanmış");
//        }

        ConfirmEmployer confirmEmployer = new ConfirmEmployer();
        Employer employer = employerDao.getByCompanyName(companyName);
        SystemPersonnel systemPersonnel = systemPersonnelDao.getOne(systemPersonnelId);
        employer.setConfirmed(true);
        employerDao.save(employer);
        confirmEmployer.setConfirmed(true);
        LocalDate date= (LocalDate.now());
        confirmEmployer.setConfirmedDate(date);
        confirmEmployer.setEmployer(employer);
        confirmEmployer.setSystemPersonnel(systemPersonnel);
        confirmEmployerDao.save(confirmEmployer);
        return new SuccessResult("Doğrulama Başarılı");
    }
}
