package hrms.hrmsproject.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hrms.hrmsproject.core.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "job_seekers")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","resumes"})

public class JobSeeker extends User {


    @Column(name = "first_name")
    @NotBlank
    @Size(min = 2,max = 50)
    private String firstName;

    @Column(name = "last_name")
    @NotBlank
    @Size(min = 2,max = 50)
    private String lastName;

    @Column(name = "national_id",unique = true)
    @NotBlank
    private String nationalityId;

    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private LocalDate dateOfBirth;

    @Column(name="photo")
    private String photo;

    @Column(name="job")
    private String job;

    @JsonIgnore
    @OneToMany(mappedBy = "jobSeeker")
    private List<Resume> resumes;

    @JsonIgnore
    @OneToMany(mappedBy = "jobSeeker")
    private List<JobSeekerFavoriteJobAdvert> jobSeekerFavoriteJobAdverts;



}
