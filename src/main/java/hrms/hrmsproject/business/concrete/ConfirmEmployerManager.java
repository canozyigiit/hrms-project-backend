package hrms.hrmsproject.business.concrete;

import com.fasterxml.jackson.databind.ObjectMapper;
import hrms.hrmsproject.business.abstracts.ConfirmEmployerService;
import hrms.hrmsproject.core.utilities.results.ErrorResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.core.utilities.results.SuccessResult;
import hrms.hrmsproject.dataAccess.abstracts.ConfirmEmployerDao;
import hrms.hrmsproject.dataAccess.abstracts.CurrentEmployerDao;
import hrms.hrmsproject.dataAccess.abstracts.EmployerDao;
import hrms.hrmsproject.dataAccess.abstracts.SystemPersonnelDao;
import hrms.hrmsproject.entities.concretes.ConfirmEmployer;
import hrms.hrmsproject.entities.concretes.CurrentEmployer;
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
    private ObjectMapper objectMapper;
    private CurrentEmployerDao currentEmployerDao;

    @Autowired
    public ConfirmEmployerManager(ConfirmEmployerDao confirmEmployerDao,
                                  EmployerDao employerDao,
                                  SystemPersonnelDao systemPersonnelDao,


                                  CurrentEmployerDao currentEmployerDao,
                                  ObjectMapper objectMapper) {
        this.confirmEmployerDao = confirmEmployerDao;
        this.employerDao = employerDao;
        this.systemPersonnelDao = systemPersonnelDao;
        this.objectMapper = objectMapper;
        this.currentEmployerDao = currentEmployerDao;
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
        LocalDate date = (LocalDate.now());
        confirmEmployer.setConfirmedDate(date);
        confirmEmployer.setEmployer(employer);
        confirmEmployer.setSystemPersonnel(systemPersonnel);
        confirmEmployerDao.save(confirmEmployer);
        return new SuccessResult("Doğrulama Başarılı");
    }

//    //TODO:Düzenlenecek(json)
//    @Override
//    public Result confirmUpdateEmployer(String companyName, int systemPersonnelId) {
//        if (!employerDao.existsByCompanyName(companyName)) {
//            return new ErrorResult("Şirket bulunamadı");
//        }
//        ConfirmEmployer confirmEmployer = new ConfirmEmployer();
//        Employer employer = employerDao.getByCompanyName(companyName);
//        CurrentEmployer currentEmployer = currentEmployerDao.getByEmployerId(employer.getId());
//        SystemPersonnel systemPersonnel = systemPersonnelDao.getOne(systemPersonnelId);
//        System.out.println(currentEmployer.getCity().getId());
//        LocalDate date = LocalDate.now();
//
//        employer.setEmail(currentEmployer.getEmail());
//        employer.setPhone(currentEmployer.getPhone());
//        employer.setCity(currentEmployer.getCity());
//        employer.setPhoto(currentEmployer.getPhoto());
//        employer.setWebSite(currentEmployer.getWebSite());
//        employer.setSince(currentEmployer.getSince());
//        employer.setTeamSize(currentEmployer.getTeamSize());
//        employer.setCompanyName(currentEmployer.getCompanyName());
//        employer.setUpdated(false);
//
//        confirmEmployer.setConfirmed(true);
//        confirmEmployer.setConfirmedDate(date);
//        confirmEmployer.setEmployer(employer);
//        confirmEmployer.setSystemPersonnel(systemPersonnel);
//        confirmEmployerDao.save(confirmEmployer);
//        employerDao.save(employer);
//        return new SuccessResult("Güncelleme onaylandı");
//    }

    @Override
    public Result confirmUpdateEmployer(String companyName, int systemPersonnelId) {

        if (!employerDao.existsByCompanyName(companyName)) {
            return new ErrorResult("Şirket bulunamadı");
        }
        Employer oldEmployer = this.employerDao.getByCompanyName(companyName);
        ConfirmEmployer confirmEmployer = new ConfirmEmployer();
        SystemPersonnel systemPersonnel = systemPersonnelDao.getOne(systemPersonnelId);
        LocalDate date = LocalDate.now();
        CurrentEmployer currentEmployer = this.currentEmployerDao.getByEmployerId(oldEmployer.getId());
        Employer employer = objectMapper.convertValue(currentEmployer.getData(), Employer.class);

        employer.setUpdated(false);
        employerDao.save(employer);

        confirmEmployer.setConfirmed(true);
        confirmEmployer.setConfirmedDate(date);
        confirmEmployer.setEmployer(employer);
        confirmEmployer.setSystemPersonnel(systemPersonnel);
        confirmEmployerDao.save(confirmEmployer);

        return new SuccessResult("Güncelleme onaylandı");
    }
}
