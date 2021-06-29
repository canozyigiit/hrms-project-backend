package hrms.hrmsproject.api.controllers;


import hrms.hrmsproject.business.abstracts.SystemPersonnelService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.SystemPersonnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/systempersonnels")
@CrossOrigin
public class SystemPersonnelsController {
    private SystemPersonnelService systemPersonnelService;

    @Autowired
    public SystemPersonnelsController(SystemPersonnelService systemPersonnelService) {
        this.systemPersonnelService = systemPersonnelService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody SystemPersonnel systemPersonnel) {
        return ResponseEntity.ok(this.systemPersonnelService.add(systemPersonnel));
    }

    @GetMapping("/getall")
    public DataResult<List<SystemPersonnel>> getAll() {
        return this.systemPersonnelService.getAll();
    }

    @GetMapping("/getbyid/{id}")
    public DataResult<SystemPersonnel> getById(@PathVariable int id) {
        return this.systemPersonnelService.getById(id);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody SystemPersonnel systemPersonnel) {
        return this.systemPersonnelService.delete(systemPersonnel);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody  SystemPersonnel systemPersonnel){
        return ResponseEntity.ok(this.systemPersonnelService.update( systemPersonnel));
    }

    @PostMapping("/changeEmail")
    public  ResponseEntity<?> changeEmail(@RequestParam int id, String email, String password){
        return ResponseEntity.ok(this.systemPersonnelService.changeEmail(id,email,password));
    }


}
