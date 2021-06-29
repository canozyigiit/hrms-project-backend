package hrms.hrmsproject.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "job_experiences")
@AllArgsConstructor
@NoArgsConstructor
public class JobExperience {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "resume_id")
    @JsonIgnore
    private Resume resume;

    @Column(name = "company_name")
    @NotBlank
    private String companyName;

    @Column(name = "position")
    @NotBlank
    private String position;

    @Column(name = "started_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @NotBlank
    @Past
    private LocalDate startedDate;

    @Nullable
    @Column(name = "ended_date")
    private LocalDate endedDate;

    @Column(name = "created_date")
    @JsonIgnore
    private LocalDate createdDate = LocalDate.now();

}