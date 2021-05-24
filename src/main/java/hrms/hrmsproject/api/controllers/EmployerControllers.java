package hrms.hrmsproject.api.controllers;


import hrms.hrmsproject.business.abstracts.EmployerService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employers")
public class EmployerControllers {
    private EmployerService employerService;

    @Autowired
    public EmployerControllers(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping("/getall")
    public DataResult<List<Employer>> getAll() {
        return this.employerService.getAll();
    }

    @PostMapping("/add")
    public Result add(@RequestBody Employer employer) {
        return this.employerService.add(employer);
    }

    @GetMapping("getbyid/{id}")
    public DataResult<Employer> getById(@PathVariable int id) {
        return this.employerService.getById(id);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Employer employer) {
        return this.employerService.delete(employer);
    }

}
