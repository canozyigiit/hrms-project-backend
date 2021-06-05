package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.core.utilities.adapters.MernisService;
import hrms.hrmsproject.business.abstracts.JobSeekerService;
import hrms.hrmsproject.business.abstracts.ValidateService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.business.BusinessRules;
import hrms.hrmsproject.core.utilities.results.*;
import hrms.hrmsproject.core.utilities.uploads.FileService;
import hrms.hrmsproject.dataAccess.abstracts.JobSeekerDao;
import hrms.hrmsproject.entities.concretes.JobSeeker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class JobSeekerManager implements JobSeekerService {
    private JobSeekerDao jobSeekerDao;
    private MernisService mernisService;
    private ValidateService<JobSeeker> validateService;
    private FileService fileService;

    @Autowired
    public JobSeekerManager(JobSeekerDao jobSeekerDao,MernisService mernisService,
                            FileService fileService,
                            ValidateService<JobSeeker> validateService) {
        this.jobSeekerDao = jobSeekerDao;
        this.mernisService = mernisService;
        this.fileService = fileService;
        this.validateService = validateService;
    }

    @Override
    public Result add(JobSeeker jobSeeker) {
        Result result = BusinessRules.Run(
                checkIfJobSeekerEmailExists(jobSeeker),
                checkIfJobSeekerNationalIdExists(jobSeeker),
                nationalIdLengthControl(jobSeeker));
        if (result != null) {
            return result;
        }
        else if (!mernisService.checkIfRealPerson(jobSeeker)) {
            return new ErrorResult(Messages.notRealPerson);
        }
        this.jobSeekerDao.save(jobSeeker);
        this.validateService.verifyData(jobSeeker);
        return new SuccessResult(Messages.jobSekeerAdded);
    }

    @Override
    public DataResult<List<JobSeeker>> getAll() {
        return new SuccessDataResult<List<JobSeeker>>(this.jobSeekerDao.findAll(), Messages.jobSekeerGetAll);
    }

    @Override
    public DataResult<JobSeeker> getById(int id) {
        return new SuccessDataResult<JobSeeker>(this.jobSeekerDao.findById(id).orElse(null), Messages.jobSekeerGet);
    }

    @Override
    public Result validateJobSeeker(int id) {
        return validateService.verifyData(this.jobSeekerDao.getById(id));
    }

    @Override
    public Result delete(JobSeeker jobSeeker) {
        this.jobSeekerDao.delete(jobSeeker);
        return new SuccessResult(Messages.jobSekeerDeleted);
    }

    @Override
    public Result addImageJobSeeker(MultipartFile file, int jobSeekerId) {
        Map<String, String> uploader = (Map<String, String>)fileService.save(file).getData();
        String imageUrl= uploader.get("url");
        JobSeeker jobSeeker = jobSeekerDao.getOne(jobSeekerId);
        jobSeeker.setPhoto(imageUrl);
        jobSeekerDao.save(jobSeeker);
        return new SuccessResult("Image added");
    }

    //************************************************************************************************************


    private Result checkIfJobSeekerEmailExists(JobSeeker jobSeeker) {
        var result = jobSeekerDao.findAllByEmail(jobSeeker.getEmail()).stream().count() != 0;
        if (result) {
            return new ErrorResult(Messages.jobSeekerEmailExists);
        }
        return new SuccessResult();
    }//Bu emailde başka kullanıcı var mı ?

    private Result checkIfJobSeekerNationalIdExists(JobSeeker jobSeeker) {
        var result = jobSeekerDao.findAllByNationalityId(jobSeeker.getNationalityId()).stream().count() != 0;
        if (result) {
            return new ErrorResult(Messages.jobSeekerNationalIdExists);
        }
        return new SuccessResult();
    }//Bu tc de başka kullanıcı var mı?



    private Result nationalIdLengthControl(JobSeeker jobSeeker) {

        if (jobSeeker.getNationalityId().length() != 11) {
            return new ErrorResult(Messages.nationalIdLengthError);
        }
        return new SuccessResult();
    }//11 karakter olmalı Tc

    private boolean checkIfRealPerson(JobSeeker jobSeeker) {
        if (!mernisService.checkIfRealPerson(jobSeeker))
        {
            return false;
        }
        return true;
    }
}
