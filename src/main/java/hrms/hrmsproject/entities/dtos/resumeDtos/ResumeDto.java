package hrms.hrmsproject.entities.dtos.resumeDtos;

import hrms.hrmsproject.entities.concretes.JobExperience;
import hrms.hrmsproject.entities.concretes.Language;
import hrms.hrmsproject.entities.concretes.School;
import hrms.hrmsproject.entities.concretes.Technology;
import lombok.Data;

import java.util.List;

@Data
public class ResumeDto {


    private int id;
    private int jobSeekerId;
    private String githubLink;
    private String linkedLink;
    private String photo;
    private String description;
    private List<Language> languages;
    private List<Technology> technologies;
    private List<School> schools;
    private List<JobExperience> jobExperiences;
}
