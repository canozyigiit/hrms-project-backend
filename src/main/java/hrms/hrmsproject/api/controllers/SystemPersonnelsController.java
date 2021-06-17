package hrms.hrmsproject.api.controllers;


import hrms.hrmsproject.business.abstracts.SystemPersonnelService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.ErrorDataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.SystemPersonnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


}
