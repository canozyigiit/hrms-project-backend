package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.*;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.entities.User;
import hrms.hrmsproject.core.utilities.business.BusinessRules;
import hrms.hrmsproject.core.utilities.results.*;
import hrms.hrmsproject.entities.concretes.Employer;
import hrms.hrmsproject.entities.concretes.JobSeeker;
import hrms.hrmsproject.entities.dtos.authDtos.AuthDto;
import hrms.hrmsproject.entities.dtos.authDtos.RegisterEmployerDto;
import hrms.hrmsproject.entities.dtos.authDtos.RegisterJobSeekerDto;
import hrms.hrmsproject.entities.dtos.authDtos.UserLoginDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthManager implements AuthService {
    private ModelMapper modelMapper;
    private EmployerService employerService;
    private JobSeekerService jobSeekerService;
    private UserService userService;
    private MailService mailService;

    @Autowired
    public AuthManager(EmployerService employerService, JobSeekerService jobSeekerService,
                       UserService userService, MailService mailService, ModelMapper modelMapper) {
        this.employerService = employerService;
        this.jobSeekerService = jobSeekerService;
        this.userService = userService;
        this.mailService = mailService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Result registerForEmployer(RegisterEmployerDto registerEmployerDto) {
        Result result = BusinessRules.Run(checkPasswordConfirm(registerEmployerDto.getPassword(), registerEmployerDto.getPasswordConfirm()),
                checkNullFields(registerEmployerDto), this.UserExists(registerEmployerDto.getEmail()));
        if (result != null) {
            return result;
        }
        Employer employer = modelMapper.map(registerEmployerDto, Employer.class);
        mailService.send(employer.getEmail());
        employerService.add(employer);
        return new SuccessResult(Messages.userRegistered);
    }

    @Override
    public Result registerForJobSeeker(RegisterJobSeekerDto registerJobSeekerDto) {
        Result result = BusinessRules.Run(checkPasswordConfirm(registerJobSeekerDto.getPassword(), registerJobSeekerDto.getPasswordConfirm()),
                this.UserExists(registerJobSeekerDto.getEmail()),checkNullFields(registerJobSeekerDto));
        JobSeeker jobSeeker = modelMapper.map(registerJobSeekerDto, JobSeeker.class);
        mailService.send(jobSeeker.getEmail());
        jobSeekerService.add(jobSeeker);
        return new SuccessResult(Messages.userRegistered);
    }


    @Override
    public Result Login(UserLoginDto userLoginDto) {
        var userToCheck = userService.getByEmail(userLoginDto.getEmail());
        if (userToCheck.getData() == null) {
            return new ErrorDataResult<User>(Messages.userNotFound);
        }
        //Düzenlenecek if(Şifre kontrolü){}
        return new SuccessDataResult<User>(userToCheck.getData(), Messages.successFullLogin);
    }


    @Override
    public Result UserExists(String email) {
        var result = userService.getByEmail(email);
        if (result.getData() != null) {
            return new ErrorResult(Messages.userAlreadyExists);

        }
        return new SuccessResult();

    }

    private Result checkPasswordConfirm(String password, String passwordConfirm) {

        if (!password.equals(passwordConfirm)) {
            return new ErrorResult(Messages.passwordConfirmError);
        }

        return new SuccessResult();
    }

    private Result checkNullFields(AuthDto authDto) {

        if (authDto.getPassword() == null || authDto.getPasswordConfirm() == null) {
            return new ErrorResult(Messages.authFieldsError);
        }
        return new SuccessResult();
    }

}

