package hrms.hrmsproject.entities.dtos.technologyDtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class TechnologyDto {


    @JsonIgnore
    private int id;
    private int resumeId;
    @NotNull
    @NotBlank
    @Size(max = 50)
    private String description;
}
