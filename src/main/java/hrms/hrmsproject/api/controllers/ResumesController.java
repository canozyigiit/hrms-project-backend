package hrms.hrmsproject.api.controllers;

import hrms.hrmsproject.business.abstracts.ResumeService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.Resume;
import hrms.hrmsproject.entities.dtos.resumeDtos.ResumeAddDto;
import hrms.hrmsproject.entities.dtos.resumeDtos.ResumeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/resumes")
@CrossOrigin
public class ResumesController {
    private ResumeService resumeService;

    @Autowired
    public ResumesController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody ResumeAddDto resumeAddDto){
        return ResponseEntity.ok(this.resumeService.add(resumeAddDto));
    }

    @GetMapping("/getall")
    public DataResult<List<ResumeDto>> getAll(){
        return this.resumeService.getAll();
    }

    @GetMapping("/findAllByJobSeekerId")
    public DataResult<List<ResumeDto>> findAllByJobSeekerId(int id){
        return this.resumeService.findAllByJobSeekerId(id);
    }

    @PutMapping("/addImageResume")
    public Result addImageResume(@RequestBody MultipartFile file, @RequestParam int resumeId) {
        return this.resumeService.addImageResume(file, resumeId);
    }

    @GetMapping("/getByJobSeeker_Id")
    public DataResult<ResumeDto> getByJobSeeker_Id( int id){
        return this.resumeService.getByJobSeeker_Id(id);
    }


    @PostMapping("/updateResume")
    public ResponseEntity<?> update(@Valid @RequestBody Resume resume){
        return ResponseEntity.ok(this.resumeService.update(resume));
    }

}
