package hrms.hrmsproject.api.controllers;

import hrms.hrmsproject.business.abstracts.ConfirmEmployerService;
import hrms.hrmsproject.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/confirmEmployer")
@CrossOrigin
public class ConfirmEmployersController {
    private ConfirmEmployerService confirmEmployerService;

    public ConfirmEmployersController(ConfirmEmployerService confirmEmployerService) {
        this.confirmEmployerService = confirmEmployerService;
    }

    @PutMapping("/{companyName}")
    public Result update(@PathVariable("companyName") String companyName,int systemPersonnelId) {

        return confirmEmployerService.confirmEmployer(companyName,systemPersonnelId);
    }
}
