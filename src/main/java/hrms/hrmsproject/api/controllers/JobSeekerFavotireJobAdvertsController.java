package hrms.hrmsproject.api.controllers;


import hrms.hrmsproject.business.abstracts.JobSeekerFavoriteJobAdvertService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.entities.concretes.JobSeekerFavoriteJobAdvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/JobSeekerFavotireJobAdverts")
@CrossOrigin
public class JobSeekerFavotireJobAdvertsController {

    private JobSeekerFavoriteJobAdvertService jobSeekerFavoriteJobAdvertService;

    @Autowired
    public JobSeekerFavotireJobAdvertsController(JobSeekerFavoriteJobAdvertService jobSeekerFavoriteJobAdvertService) {
        this.jobSeekerFavoriteJobAdvertService = jobSeekerFavoriteJobAdvertService;
    }



    @GetMapping("/getAll")
    public DataResult<List<JobSeekerFavoriteJobAdvert>> getAll() {
        return this.jobSeekerFavoriteJobAdvertService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody JobSeekerFavoriteJobAdvert jobSeekerFavoriteJobAdvert) {
        return ResponseEntity.ok(this.jobSeekerFavoriteJobAdvertService.add(jobSeekerFavoriteJobAdvert));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(JobSeekerFavoriteJobAdvert jobSeekerFavoriteJobAdvert) {
        return ResponseEntity.ok(this.jobSeekerFavoriteJobAdvertService.delete(jobSeekerFavoriteJobAdvert));
    }

    @GetMapping("getAllByJobSeekerId")
    public DataResult<List<JobSeekerFavoriteJobAdvert>> getAllJobSeekerId(int id){
        return this.jobSeekerFavoriteJobAdvertService.getAllJobSeekerId(id);
    }
}
