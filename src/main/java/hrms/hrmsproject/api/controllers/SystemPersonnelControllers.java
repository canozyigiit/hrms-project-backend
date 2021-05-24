package hrms.hrmsproject.api.controllers;


import hrms.hrmsproject.business.abstracts.SystemPersonnelService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.SystemPersonnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/systempersonnels")
public class SystemPersonnelControllers {
    private SystemPersonnelService systemPersonnelService;

    @Autowired
    public SystemPersonnelControllers(SystemPersonnelService systemPersonnelService) {
        this.systemPersonnelService = systemPersonnelService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody SystemPersonnel systemPersonnel) {
        return this.systemPersonnelService.add(systemPersonnel);
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
