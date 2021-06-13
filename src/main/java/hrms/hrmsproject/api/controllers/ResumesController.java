package hrms.hrmsproject.api.controllers;

import hrms.hrmsproject.business.abstracts.ResumeService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.ErrorDataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.dtos.resumeDtos.ResumeAddDto;
import hrms.hrmsproject.entities.dtos.resumeDtos.ResumeDto;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException
            (MethodArgumentNotValidException exceptions) {
        Map<String, String> validationErrors = new HashMap<String, String>();
        for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ErrorDataResult<Object> errors
                = new ErrorDataResult<Object>(validationErrors, "Doğrulama hataları");
        return errors;
    }
}
