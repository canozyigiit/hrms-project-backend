package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.*;
import hrms.hrmsproject.business.constans.Messages;
import hrms.hrmsproject.core.entities.User;
import hrms.hrmsproject.core.utilities.results.*;
import hrms.hrmsproject.entities.concretes.Employer;
import hrms.hrmsproject.entities.concretes.JobSeeker;
import hrms.hrmsproject.entities.dtos.authDtos.RegisterEmployerDto;
import hrms.hrmsproject.entities.dtos.authDtos.RegisterJobSeekerDto;
import hrms.hrmsproject.entities.dtos.authDtos.UserLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthManager implements AuthService {
    private EmployerService employerService;
    private JobSeekerService jobSeekerService;
    private UserService userService;
    private MailService mailService;

    @Autowired
    public AuthManager(EmployerService employerService, JobSeekerService jobSeekerService,
                       UserService userService, MailService mailService) {
        this.employerService = employerService;
        this.jobSeekerService = jobSeekerService;
        this.userService = userService;
        this.mailService = mailService;
    }

    @Override
    public Result registerForEmployer(RegisterEmployerDto registerEmployerDto) {
        var employer = new Employer(
                0,
                registerEmployerDto.getEmail(),
                registerEmployerDto.getPassword(),
                registerEmployerDto.getCompanyName(),
                registerEmployerDto.getWebsite(),
                registerEmployerDto.getPhone()
        );
        mailService.send(employer.getEmail());
        employerService.add(employer);
        return new SuccessResult(Messages.userRegistered);
    }

    @Override
    public Result registerForJobSeeker(RegisterJobSeekerDto registerJobSeekerDto) {
        var jobSeeker = new JobSeeker(
                0,
                registerJobSeekerDto.getEmail(),
                registerJobSeekerDto.getPassword(),
                registerJobSeekerDto.getFirstName(),
                registerJobSeekerDto.getLastName(),
                registerJobSeekerDto.getNationalityId(),
                registerJobSeekerDto.getDateOfBirth()
        );
        mailService.send(jobSeeker.getEmail());
        jobSeekerService.add(jobSeeker);
        return new SuccessResult(Messages.userRegistered);
    }

    @Override
    public Result Login(UserLoginDto userLoginDto) {
        var userToCheck = userService.getByEmail(userLoginDto.getEmail());
        if (userToCheck.getData() == null) {
            return new ErrorDataResult<User>(Messages.UserNotFound);
        }
        //Düzenlenecek if(Şifre kontrolü){}
        return new SuccessDataResult<User>(userToCheck.getData(), Messages.SuccessfulLogin);
    }


    @Override
    public Result UserExists(String email) {
        var result = userService.getByEmail(email);
        if (result.getData() == null) {
            return new SuccessResult();
        }
        return new ErrorResult(Messages.userAlreadyExists);

    }

}

