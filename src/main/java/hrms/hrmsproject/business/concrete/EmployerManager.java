package hrms.hrmsproject.business.concrete;


import com.fasterxml.jackson.databind.ObjectMapper;
import hrms.hrmsproject.business.abstracts.EmployerService;
import hrms.hrmsproject.business.abstracts.ValidateService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.business.BusinessRules;
import hrms.hrmsproject.core.utilities.results.*;
import hrms.hrmsproject.core.utilities.uploads.FileService;
import hrms.hrmsproject.dataAccess.abstracts.CurrentEmployerDao;
import hrms.hrmsproject.dataAccess.abstracts.EmployerDao;
import hrms.hrmsproject.entities.concretes.CurrentEmployer;
import hrms.hrmsproject.entities.concretes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmployerManager implements EmployerService {
    private EmployerDao employerDao;
    private ValidateService validateService;
    private FileService fileService;
    private ObjectMapper objectMapper;
    private CurrentEmployerDao currentEmployerDao;

    @Autowired
    public EmployerManager(EmployerDao employerDao,
                           ValidateService validateService,
                           FileService fileService,
                           ObjectMapper objectMapper,
                           CurrentEmployerDao currentEmployerDao) {
        this.employerDao = employerDao;
        this.validateService = validateService;
        this.objectMapper = objectMapper;
        this.currentEmployerDao = currentEmployerDao;
        this.fileService = fileService;
    }

    //TODO:Düzenlenecek(json)
    @Override
    public Result update(Employer employer) {
        Employer emp = this.employerDao.findById(employer.getId()).orElse(null);
        CurrentEmployer currentEmployer = currentEmployerDao.getByEmployerId(employer.getId());
        LocalDate date = LocalDate.now();
        if (currentEmployer != null) {

            currentEmployer.setEmployerId(employer.getId());
            currentEmployer.setEmail(employer.getEmail());
            currentEmployer.setPhone(employer.getPhone());
            currentEmployer.setCity(employer.getCity());
            currentEmployer.setPhoto(employer.getPhoto());
            currentEmployer.setWebSite(employer.getWebSite());
            currentEmployer.setSince(employer.getSince());
            currentEmployer.setTeamSize(employer.getTeamSize());
            currentEmployer.setCompanyName(employer.getCompanyName());
            this.currentEmployerDao.save(currentEmployer);
            emp.setUpdated(true);
            emp.setUpdatedDate(date);
            this.employerDao.save(emp);
            return new SuccessResult("Güncelleme isteği sistem tarafından incelenip onaylanacaktır.");
        } else {
            CurrentEmployer newCurrentEmployer = new CurrentEmployer();
            newCurrentEmployer.setEmployerId(employer.getId());
            newCurrentEmployer.setEmail(employer.getEmail());
            newCurrentEmployer.setPhone(employer.getPhone());
            newCurrentEmployer.setCity(employer.getCity());
            newCurrentEmployer.setPhoto(employer.getPhoto());
            newCurrentEmployer.setWebSite(employer.getWebSite());
            newCurrentEmployer.setSince(employer.getSince());
            newCurrentEmployer.setTeamSize(employer.getTeamSize());
            newCurrentEmployer.setCompanyName(employer.getCompanyName());
            emp.setUpdated(true);
            emp.setUpdatedDate(date);
            this.employerDao.save(emp);
            this.currentEmployerDao.save(newCurrentEmployer);
            return new SuccessResult("Güncelleme isteği sistem tarafından incelenip onaylanacaktır.");
        }
    }

    @Override
    public DataResult<List<Employer>> getByisConfirmedFalse() {
        return new SuccessDataResult<List<Employer>>(this.employerDao.getByisConfirmedFalse());
    }

    @Override
    public Result add(Employer employer) {
        Result result = BusinessRules.Run(checkIfEmployerEmailExists(employer.getEmail()),
                checkEmailIsCompatibleWithDomain(employer.getEmail(), employer.getWebSite()));
        if (result != null) {
            return result;
        }
        this.employerDao.save(employer);
        this.validateService.verifyData(employer.getId());
        return new SuccessResult(Messages.employerAdded);

    }

    @Override
    public Result changeEmail(int id, String email, String password) {
        Employer emp = this.employerDao.findById(id).orElse(null);
        if (emp == null) {
            return new ErrorResult("İş Veren bulunamadı");
        }
        var result = BusinessRules.Run(passwordCheck(id, password),
                checkIfEmployerEmailExists(email), checkIfEmployerEmail(email, emp.getWebSite()));
        if (result != null) {
            return result;
        }
        emp.setVerify(false);
        emp.setUpdated(true);
        emp.setEmail(email);
        employerDao.save(emp);
        validateService.verifyData(emp.getId());
        return new SuccessResult("Email Güncellendi. Yeni emailinizi doğrulamayı unutmayın.");

    }


    @Override
    public Result addImageEmployer(MultipartFile file, int employerId) {
        Map<String, String> uploader = (Map<String, String>) fileService.save(file).getData();
        String imageUrl = uploader.get("url");
        Employer employer = employerDao.getOne(employerId);
        employer.setPhoto(imageUrl);
        employerDao.save(employer);
        return new SuccessResult("Image added");
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

    @Override
    public DataResult<List<Employer>> getByisUpdatedTrue() {
        return new SuccessDataResult<List<Employer>>(this.employerDao.getByisUpdatedTrue());
    }

    //************************************************************************************************************

    private Result checkIfEmployerEmailExists(String email) {
        var result = employerDao.findAllByEmail(email).stream().count() != 0;
        if (result) {
            return new ErrorResult(Messages.employerEmailExists);
        }
        return new SuccessResult();
    }//Böyle email daha önce kullanılmış mı ?


    private Result checkIfEmployerEmail(String email, String webSite) {
        Pattern validEmail =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                        Pattern.CASE_INSENSITIVE);

        Matcher matcher = validEmail.matcher(email);

        String[] isEmailCompatible = email.split("@", 2);//İkiye bölüyor  öncesi@sonrası
        String webSiten = webSite.substring(4);//www. den sonrası

        if (!matcher.matches()) {
            return new ErrorResult(Messages.errorEmployerEmailValid);
        } else if (!isEmailCompatible[1].equals(webSiten)) {
            return new ErrorResult(Messages.errorEmployerEmailNotCorporate);
        }

        return new SuccessResult();
    }

    private Result checkEmailIsCompatibleWithDomain(String email, String employerWebSite) {

        String[] isEmailCompatible = email.split("@", 2);//İkiye bölüyor  öncesi@sonrası
        String webSite = employerWebSite.substring(4);//www. den sonrası

        if (!isEmailCompatible[1].equals(webSite)) {
            return new ErrorResult(Messages.errorEmployerEmailNotCorporate);
        }

        return new SuccessResult();
    }//Kurumsal eposta mı?

    private Result passwordCheck(int id, String password) {
        Employer employer = this.employerDao.findById(id).orElse(null);
        if (!employer.getPassword().equals(password)) {
            return new ErrorResult("Şifre Yanlış");
        }
        return new SuccessResult();
    }
}
