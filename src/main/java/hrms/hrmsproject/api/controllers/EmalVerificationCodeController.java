package hrms.hrmsproject.api.controllers;


import hrms.hrmsproject.business.abstracts.EmailVerificationCodeService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.entities.concretes.EmailVerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emalVerificationCodeController")
@CrossOrigin
public class EmalVerificationCodeController {

    private EmailVerificationCodeService emailVerificationCodeService;

    @Autowired
    public EmalVerificationCodeController(EmailVerificationCodeService emailVerificationCodeService) {
        this.emailVerificationCodeService = emailVerificationCodeService;
    }

    @GetMapping("/getbyid")
    public DataResult<EmailVerificationCode> getById(int userId){
        return this.emailVerificationCodeService.getByUserId(userId);
    }
}
