package hrms.hrmsproject.api.controllers;

import hrms.hrmsproject.business.abstracts.GraduateService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.Graduate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/graduate")
public class GraduatesControllers {
    private GraduateService graduateService;

    @Autowired
    public GraduatesControllers(GraduateService graduateService) {
        this.graduateService = graduateService;
    }

    @PostMapping("/add")
    public Result add(@Valid @RequestBody Graduate graduate){
        return this.graduateService.add(graduate);
    }

    @GetMapping("/getall")
    public DataResult<List<Graduate>> getAll(){
        return this.graduateService.getAll();
    }
}
