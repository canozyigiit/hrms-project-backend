package hrms.hrmsproject.api.controllers;

import hrms.hrmsproject.business.abstracts.JobSeekerService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.JobSeeker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jobsekeers")
public class JobSeekerControllers {
    private JobSeekerService jobSeekerService;

    @Autowired
    public JobSeekerControllers(JobSeekerService jobSeekerService) {
        this.jobSeekerService = jobSeekerService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody JobSeeker jobSeeker) {
        return this.jobSeekerService.add(jobSeeker);
    }

    @GetMapping("/getall")
    public DataResult<List<JobSeeker>> getAll() {
        return this.jobSeekerService.getAll();
    }

    @GetMapping("getbyid/{id}")
    public DataResult<JobSeeker> getById(@PathVariable int id) {
        return this.jobSeekerService.getById(id);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody JobSeeker jobSeeker) {
        return this.jobSeekerService.delete(jobSeeker);
    }


}
