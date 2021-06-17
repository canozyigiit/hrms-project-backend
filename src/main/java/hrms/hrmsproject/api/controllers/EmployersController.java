package hrms.hrmsproject.api.controllers;


import hrms.hrmsproject.business.abstracts.EmployerService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.ErrorDataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/employers")
@CrossOrigin
public class EmployersController {
    private EmployerService employerService;

    @Autowired
    public EmployersController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping("/getall")
    public DataResult<List<Employer>> getAll() {
        return this.employerService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody Employer employer) {
        return ResponseEntity.ok(this.employerService.add(employer));
    }

    @GetMapping("getbyid/{id}")
    public DataResult<Employer> getById(@PathVariable int id) {
        return this.employerService.getById(id);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody Employer employer) {
        return this.employerService.delete(employer);
    }

    @PostMapping("/validateEmployer/{id}")
    public Result validateEmployer(@PathVariable int id){
        return this.employerService.validateEmployer(id);
    }

    @PutMapping("/addImageEmployer")
    public Result addImageEmployer(@RequestBody MultipartFile file, @RequestParam int employerId) {
        return this.employerService.addImageEmployer(file, employerId);

    }


}
