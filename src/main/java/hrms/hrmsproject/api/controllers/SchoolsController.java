package hrms.hrmsproject.api.controllers;

import hrms.hrmsproject.business.abstracts.SchoolService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.entities.dtos.schoolDtos.SchoolDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/schools")
@CrossOrigin
public class SchoolsController {

    private SchoolService schoolService;

    @Autowired
    public SchoolsController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody SchoolDto schoolDto){
        return ResponseEntity.ok(this.schoolService.add(schoolDto));
    }

    @GetMapping("/getall")
    public DataResult<List<SchoolDto>> getAll(){
        return this.schoolService.getAll();
    }

    @PostMapping("/update")
    public ResponseEntity<?> update( @RequestParam int  id, String schoolDepartment, String schoolName, LocalDate startedDate, LocalDate endedDate, Integer graduateId){
        return ResponseEntity.ok(this.schoolService.update(id,schoolDepartment,schoolName,startedDate,endedDate,graduateId));
    }

}
