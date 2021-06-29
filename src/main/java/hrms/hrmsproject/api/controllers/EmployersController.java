package hrms.hrmsproject.api.controllers;


import hrms.hrmsproject.business.abstracts.EmployerService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/employers")
@CrossOrigin
public class EmployersController {
    private EmployerService employerService;

    @Autowired
    public EmployersController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @PostMapping("/update")
    public ResponseEntity<?>  update(@Valid @RequestBody Employer employer){
        return ResponseEntity.ok(this.employerService.update(employer));
    }
    @GetMapping("/getall")
    public DataResult<List<Employer>> getAll() {
        return this.employerService.getAll();
    }

    @PostMapping("/changeEmail")
    public Result changeEmail(@RequestParam int id, @RequestParam String email, @RequestParam String password) {
        return this.employerService.changeEmail(id, email, password);
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


    @PutMapping("/addImageEmployer")
    public Result addImageEmployer(@RequestBody MultipartFile file, @RequestParam int employerId) {
        return this.employerService.addImageEmployer(file, employerId);

    }
    @GetMapping("getByisConfirmedFalse")
    public DataResult<List<Employer>> getByisConfirmedFalse(){
        return this.employerService.getByisConfirmedFalse();
    }

    @GetMapping("/getByisUpdatedTrue")
    public DataResult<List<Employer>> getByisUpdatedTrue(){
        return this.employerService.getByisUpdatedTrue();
    }

}
