package hrms.hrmsproject.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "job_adverts")
public class JobAdvert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "description")
    @NotBlank
    @Size(min = 2, max = 250)
    private String description;

    @Column(name = "salary_min")
    @Nullable
    private int salaryMin;
    @Nullable
    @Column(name = "salary_max")
    private int salaryMax;

    @Column(name = "open_position_count")
    @Min(value = 1, message = "açık iş pozisyonu adedi 1 den düşük olamaz")
    @NotNull
    private int openPositionCount;

    @Column(name = "deadline")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    @NotNull
    private LocalDate deadLine;


    @Column(name = "created_date")
    @JsonIgnore
    private LocalDate createdDate = LocalDate.now();

    @Column(name = "is_open")

    private boolean isOpen;

    @Column(name = "is_active")

    private boolean isActive;

    @Column(name = "is_confirmed")

    private boolean isConfirmed;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "job_type_id")
    private JobType jobType;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "job_workspace_type_id")
    private JobWorkSpaceType jobWorkSpaceType;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "job_position_id")
    private JobPosition jobPosition;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "city_id")
    private City city;


    @JsonIgnore
    @OneToMany(mappedBy = "jobSeeker")
    private List<JobSeekerFavoriteJobAdvert> jobSeekerFavoriteJobAdverts;


}
