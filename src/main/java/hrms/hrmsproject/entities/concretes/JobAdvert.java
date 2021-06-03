package hrms.hrmsproject.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

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
    @NotNull
    @NotBlank
    @Size(min = 10,max = 250)
    private String description;

    @Column(name = "salary_min")
    @NotNull
    @NotBlank
//    @Min(value = 2800,message = "Min maaş değeri asgari ücretden düşük olamaz")
    private int salaryMin;

    @Column(name = "salary_max")
    @NotNull
    @NotBlank
    private int salaryMax;

    @Column(name = "open_position_count")
    @Min(value = 1,message = "açık iş pozisyonu adedi 1 den düşük olamaz")
    @NotNull
    @NotBlank
    private int openPositionCount;

    @Column(name = "deadline")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    @NotNull
    @NotBlank
    private LocalDate  deadLine;


    @Column(name = "created_date")
    @JsonIgnore
    private LocalDate createdDate = LocalDate.now();

    @Column(name = "is_open")
    @NotNull
    @NotBlank
    private boolean isOpen;

    @Column(name = "is_active")
    @JsonIgnore
    private boolean isActive = true;


    @ManyToOne
    @NotNull
    @NotBlank
    @JoinColumn(name = "job_position_id")
    private JobPosition jobPosition;

    @ManyToOne
    @NotNull
    @NotBlank
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @ManyToOne
    @NotNull
    @NotBlank
    @JoinColumn(name = "city_id")
    private City city;

    public JobAdvert(int cityId, int jobTitleId, int employerId, String description, Integer salaryMin, Integer salaryMax, int openPositionCount,
                     LocalDate deadLine) {
        super();
        this.description = description;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.openPositionCount = openPositionCount;
        this.deadLine = deadLine;
        this.city = new City();
        this.jobPosition = new JobPosition();
        this.employer = new Employer();
        this.city.setId(cityId);
        this.jobPosition.setId(jobTitleId);
        this.employer.setId(employerId);
    }
}
