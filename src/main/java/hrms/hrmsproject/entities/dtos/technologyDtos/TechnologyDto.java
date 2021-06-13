package hrms.hrmsproject.entities.dtos.technologyDtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class TechnologyDto {

    private int id;
    private int resumeId;
    @NotNull
    @NotBlank
    @Size(max = 50)
    private String description;
}
