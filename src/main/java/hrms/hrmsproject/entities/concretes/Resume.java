package hrms.hrmsproject.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name="resumes")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","languages","technologies","schools","jobExperiences"})

public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne()
    @JoinColumn(name = "job_seeker_id")
    private JobSeeker jobSeeker;

    @Column(name="github_link")
    private String githubLink;

    @Column(name="linked_link")
    private String linkedLink;

    @Column(name="photo")
    private String photo;

    @Column(name="description")
    @Size(max = 250)
    private String description;

    @Column(name="created_date")
    @JsonIgnore
    private LocalDate createdDate = LocalDate.now();

    @Column(name="updated_date")
    @JsonIgnore
    private LocalDate updatedDate = null;

    @Column(name="is_active")
    @JsonIgnore
    private boolean isActive=true;


    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL )
    private List<Language> languages;

    @OneToMany(mappedBy = "resume",cascade = CascadeType.ALL )
    private List<Technology> technologies;

    @OneToMany(mappedBy = "resume",cascade = CascadeType.ALL )
    private List<School> schools;

    @OneToMany(mappedBy = "resume",cascade = CascadeType.ALL )
    private List<JobExperience> jobExperiences;
}
