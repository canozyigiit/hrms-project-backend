package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.ConfirmEmployerService;
import hrms.hrmsproject.core.utilities.results.ErrorResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.core.utilities.results.SuccessResult;
import hrms.hrmsproject.dataAccess.abstracts.*;
import hrms.hrmsproject.entities.concretes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ConfirmEmployerManager implements ConfirmEmployerService {
    private ConfirmEmployerDao confirmEmployerDao;
    private EmployerDao employerDao;
    private SystemPersonnelDao systemPersonnelDao;
    private CurrentEmployerDao currentEmployerDao;
    private CityDao cityDao;

    @Autowired
    public ConfirmEmployerManager(ConfirmEmployerDao confirmEmployerDao,
                                  EmployerDao employerDao,
                                  SystemPersonnelDao systemPersonnelDao,
                                  CurrentEmployerDao currentEmployerDao,
                                  CityDao cityDao) {
        this.confirmEmployerDao = confirmEmployerDao;
        this.employerDao = employerDao;
        this.systemPersonnelDao = systemPersonnelDao;
        this.currentEmployerDao= currentEmployerDao;
        this.cityDao =  cityDao;
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

    //TODO:Düzenlenecek(json)
    @Override
    public Result confirmUpdateEmployer(String companyName, int systemPersonnelId) {
        if (!employerDao.existsByCompanyName(companyName)) {
            return new ErrorResult("Şirket bulunamadı");
        }
        ConfirmEmployer confirmEmployer = new ConfirmEmployer();
        Employer employer = employerDao.getByCompanyName(companyName);
        CurrentEmployer currentEmployer = currentEmployerDao.getByEmployerId(employer.getId());
        SystemPersonnel systemPersonnel = systemPersonnelDao.getOne(systemPersonnelId);
        System.out.println(currentEmployer.getCity().getId());
        LocalDate date = LocalDate.now();

        employer.setEmail(currentEmployer.getEmail());
        employer.setPhone(currentEmployer.getPhone());
        employer.setCity(currentEmployer.getCity());
        employer.setPhoto(currentEmployer.getPhoto());
        employer.setWebSite(currentEmployer.getWebSite());
        employer.setSince(currentEmployer.getSince());
        employer.setTeamSize(currentEmployer.getTeamSize());
        employer.setCompanyName(currentEmployer.getCompanyName());
        employer.setUpdated(false);

        confirmEmployer.setConfirmed(true);
        confirmEmployer.setConfirmedDate(date);
        confirmEmployer.setEmployer(employer);
        confirmEmployer.setSystemPersonnel(systemPersonnel);
        confirmEmployerDao.save(confirmEmployer);
        employerDao.save(employer);
       return new SuccessResult("Güncelleme onaylandı");
    }
}
