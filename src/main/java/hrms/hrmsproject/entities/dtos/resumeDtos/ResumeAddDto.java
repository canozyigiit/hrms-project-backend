package hrms.hrmsproject.entities.dtos.resumeDtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class ResumeAddDto {

    @JsonIgnore
    private int id;
    private int jobSeekerId;
    private String githubLink;
    private String linkedLink;
    private String photo;
    @Size(max = 250)
    private String description;

}
