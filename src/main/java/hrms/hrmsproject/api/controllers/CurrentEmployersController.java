package hrms.hrmsproject.api.controllers;


import hrms.hrmsproject.business.abstracts.CurrentEmployerService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.entities.concretes.CurrentEmployer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currentEmployer")
@CrossOrigin
public class CurrentEmployersController {
    private CurrentEmployerService currentEmployerService;

    @Autowired
    public CurrentEmployersController(CurrentEmployerService currentEmployerService) {
        this.currentEmployerService = currentEmployerService;
    }

    @GetMapping("/getByEmployerId")
    public DataResult<CurrentEmployer> getByEmployerId(int id){
        return this.currentEmployerService.getByEmployerId(id);
    }
}
