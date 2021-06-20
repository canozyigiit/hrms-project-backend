package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.ConfirmJobAdvertService;
import hrms.hrmsproject.core.utilities.results.ErrorResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.core.utilities.results.SuccessResult;
import hrms.hrmsproject.dataAccess.abstracts.ConfirmJobAdvertDao;
import hrms.hrmsproject.dataAccess.abstracts.JobAdvertDao;
import hrms.hrmsproject.dataAccess.abstracts.SystemPersonnelDao;
import hrms.hrmsproject.entities.concretes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ConfirmJobAdvertManager implements ConfirmJobAdvertService {
    private ConfirmJobAdvertDao confirmJobAdvertDao;
    private JobAdvertDao jobAdvertDao;
    private SystemPersonnelDao systemPersonnelDao;

    @Autowired
    public ConfirmJobAdvertManager(ConfirmJobAdvertDao confirmJobAdvertDao, JobAdvertDao jobAdvertDao, SystemPersonnelDao systemPersonnelDao) {
        this.confirmJobAdvertDao = confirmJobAdvertDao;
        this.jobAdvertDao = jobAdvertDao;
        this.systemPersonnelDao = systemPersonnelDao;
    }

    @Override
    public Result confirmJobAdvert(int jobAdvertId, int systemPersonnelId) {

        if (!jobAdvertDao.existsById(jobAdvertId)) {
            return new ErrorResult("İlan Bulunamadı");
        }

        ConfirmJobAdvert confirmJobAdvert = new ConfirmJobAdvert();
        JobAdvert jobAdvert = jobAdvertDao.getOne(jobAdvertId);
        SystemPersonnel systemPersonnel = systemPersonnelDao.getOne(systemPersonnelId);
        jobAdvert.setConfirmed(true);
        jobAdvertDao.save(jobAdvert);
        confirmJobAdvert.setConfirmed(true);
        LocalDate date= (LocalDate.now());
        confirmJobAdvert.setConfirmedDate(date);
        confirmJobAdvert.setJobAdvert(jobAdvert);
        confirmJobAdvert.setSystemPersonnel(systemPersonnel);
        confirmJobAdvertDao.save(confirmJobAdvert);
        return new SuccessResult("Doğrulama Başarılı");
    }
}
