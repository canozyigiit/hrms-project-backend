package hrms.hrmsproject.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name="resumes")
@AllArgsConstructor
@NoArgsConstructor
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(targetEntity = JobSeeker.class)
    @JoinColumn(name = "job_seeker_id", referencedColumnName =  "user_id",nullable = false)
    private JobSeeker jobSeeker;

    @Column(name="github_link")
    private String githubLink;

    @Column(name="linked_link")
    private String linkedLink;

    @Column(name="photo")
    private String photo;

    @Column(name="description")
    private String description;

    @Column(name="created_date")
    @JsonIgnore
    private LocalDate createdDate = LocalDate.now();

    @Column(name="updated_date")
    private LocalDate updatedDate;

    @Column(name="is_active")
    @JsonIgnore
    private boolean isActive=true;


    @OneToMany(mappedBy = "resume" )
    private List<Language> languages;

    @OneToMany(mappedBy = "resume" )
    private List<Technology> technologies;

    @OneToMany(mappedBy = "resume" )
    private List<School> schools;

    @OneToMany(mappedBy = "resume" )
    private List<JobExperience> jobExperiences;
}
