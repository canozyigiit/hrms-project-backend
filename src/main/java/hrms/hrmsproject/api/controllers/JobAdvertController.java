package hrms.hrmsproject.api.controllers;

import hrms.hrmsproject.business.abstracts.JobAdvertService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.JobAdvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/jobadverts")
public class JobAdvertController {
    private JobAdvertService jobAdvertService;

    @Autowired
    public JobAdvertController(JobAdvertService jobAdvertService) {
        this.jobAdvertService = jobAdvertService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody JobAdvert jobAdvert) {
        return this.jobAdvertService.add(jobAdvert);
    }

    @GetMapping("/getall")
    public DataResult<List<JobAdvert>> getAll() {
        return this.jobAdvertService.getAll();
    }

    @PostMapping("/changeOpenToClose")
    public Result changeOpenToClose(int id) {
        return this.jobAdvertService.changeOpenToClose(id);
    }

    @GetMapping("/getAllOpenJobAdvertList")
    public DataResult<List<JobAdvert>> getAllOpenJobAdvertList() {
        return this.jobAdvertService.getAllOpenJobAdvertList();
    }
    @GetMapping("/getByisOpenTrueOrderByCreatedDateDesc")
    public DataResult<List<JobAdvert>>getByisOpenTrueOrderByCreatedDateDesc(){
        return this.jobAdvertService.getByisOpenTrueOrderByCreatedDateDesc();
    }

    @GetMapping("/getAllOpenJobAdvertByEmployer")
    public DataResult<List<JobAdvert>> getByisOpenTrueAndEmployer_Id(int id) {
        return this.jobAdvertService.getByisOpenTrueAndEmployer_Id(id);
    }

}
