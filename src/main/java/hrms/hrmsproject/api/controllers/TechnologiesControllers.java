package hrms.hrmsproject.api.controllers;

import hrms.hrmsproject.business.abstracts.TechnologyService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.dtos.technologyDtos.TechnologyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/technologies")
@CrossOrigin
public class TechnologiesControllers {

    private TechnologyService technologyService;

    @Autowired
    public TechnologiesControllers(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody TechnologyDto technologyDto){
        return ResponseEntity.ok(this.technologyService.add(technologyDto));
    }

    @GetMapping("/getall")
    public DataResult<List<TechnologyDto>> getAll(){
        return this.technologyService.getAll();
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody TechnologyDto technologyDto){
      return   ResponseEntity.ok(this.technologyService.update(technologyDto));
    }

    @PostMapping("/delete")
    public Result delete(int id){
        return this.technologyService.delete(id);
    }

}
