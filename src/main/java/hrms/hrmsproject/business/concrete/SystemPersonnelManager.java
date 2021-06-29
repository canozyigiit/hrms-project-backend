package hrms.hrmsproject.business.concrete;


import hrms.hrmsproject.business.abstracts.SystemPersonnelService;
import hrms.hrmsproject.business.abstracts.ValidateService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.business.BusinessRules;
import hrms.hrmsproject.core.utilities.results.*;
import hrms.hrmsproject.dataAccess.abstracts.SystemPersonnelDao;
import hrms.hrmsproject.entities.concretes.SystemPersonnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemPersonnelManager implements SystemPersonnelService {
    private SystemPersonnelDao systemPersonnelDao;
    private ValidateService validateService;

    @Autowired
    public SystemPersonnelManager(SystemPersonnelDao systemPersonnelDao,
                                  ValidateService validateService) {
        this.systemPersonnelDao = systemPersonnelDao;
        this.validateService = validateService;
    }

    @Override
    public Result add(SystemPersonnel systemPersonnel) {

        this.systemPersonnelDao.save(systemPersonnel);
        validateService.verifyData(systemPersonnel.getId());
        return new SuccessResult(Messages.systemPersonnelAdded);
    }

    @Override
    public Result update(SystemPersonnel systemPersonnel) {
        SystemPersonnel sp = systemPersonnelDao.findById(systemPersonnel.getId()).orElse(null);
        if (sp != null){
            sp.setFirstName(systemPersonnel.getFirstName());
            sp.setLastName(systemPersonnel.getLastName());
            sp.setEmail(systemPersonnel.getEmail());
            systemPersonnelDao.save(sp);
            return new SuccessResult("Bilgiler Güncellendi");
        }
//        if (firstName != null) {
//            systemPersonnel.setFirstName(firstName);
//            systemPersonnelDao.save(systemPersonnel);
//            return new SuccessResult("İsim güncellendi");
//        }
//        if (lastName != null) {
//            systemPersonnel.setLastName(lastName);
//            systemPersonnelDao.save(systemPersonnel);
//            return new SuccessResult("Soyisim güncellendi");
//        }
        return new ErrorResult("Bilgiler Güncellenemedi!");
    }



    @Override
    public DataResult<List<SystemPersonnel>> getAll() {
        return new SuccessDataResult<List<SystemPersonnel>>(this.systemPersonnelDao.findAll(), Messages.systemPersonnelGetAll);
    }

    @Override
    public DataResult<SystemPersonnel> getById(int id) {
        return new SuccessDataResult<SystemPersonnel>(this.systemPersonnelDao.findById(id).orElse(null), Messages.systemPersonnelGet);
    }

    @Override
    public Result delete(SystemPersonnel systemPersonnel) {
        this.systemPersonnelDao.delete(systemPersonnel);
        return new SuccessResult(Messages.systemPersonnelDeleted);
    }

    @Override
    public Result changeEmail(int id, String email, String password) {
        SystemPersonnel systemPersonnel = this.systemPersonnelDao.findById(id).orElse(null);
        if (systemPersonnel == null) {
            return new ErrorResult("Sistem Personeli bulunamadı");
        }
        var result = BusinessRules.Run(passwordCheck(id, password),
                checkIfEmailExists(email));
        if (result != null) {
            return result;
        }
        systemPersonnel.setVerify(false);
        systemPersonnel.setEmail(email);
        systemPersonnelDao.save(systemPersonnel);
        validateService.verifyData(systemPersonnel.getId());
        return new SuccessResult("Email Güncellendi. Yeni emailinizi doğrulamayı unutmayın.");

    }


    private Result passwordCheck(int id, String password) {
        SystemPersonnel systemPersonnel = this.systemPersonnelDao.findById(id).orElse(null);
        if (!systemPersonnel.getPassword().equals(password)) {
            System.out.println(systemPersonnel.getPassword());
            return new ErrorResult("Şifre Yanlış");
        }
        return new SuccessResult();
    }


    private Result checkIfEmailExists(String email) {
        var result = systemPersonnelDao.findAllByEmail(email).stream().count() != 0;
        if (result) {
            return new ErrorResult("Bu emaile sahip kullanıcı zaten mevcut");
        }
        return new SuccessResult();
    }

}
