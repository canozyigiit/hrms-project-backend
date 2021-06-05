package hrms.hrmsproject.entities.dtos.resumeDtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hrms.hrmsproject.entities.concretes.*;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ResumeAddDto {

    @JsonIgnore
    private int id;
    private JobSeeker jobSeeker;
    private String githubLink;
    private String linkedLink;
    private String photo;
    @Size(max = 250)
    private String description;
    @JsonIgnore
    private List<Language> languages;
    @JsonIgnore
    private List<Technology> technologies;
    @JsonIgnore
    private List<School> schools;
    @JsonIgnore
    private List<JobExperience> jobExperiences;

}
