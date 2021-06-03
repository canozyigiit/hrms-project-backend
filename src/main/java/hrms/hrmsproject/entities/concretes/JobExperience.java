package hrms.hrmsproject.entities.concretes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @JsonProperty(access = Access.WRITE_ONLY)
    @ManyToOne(targetEntity = Resume.class)
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @Column(name = "business_name")
    @NotNull
    @NotBlank
    private String businessName;

    @Column(name = "position")
    @NotNull
    @NotBlank
    private String position;

    @Column(name = "started_date")
    private LocalDate startingDate = LocalDate.now();

    @Column(name = "ended_date")
    private LocalDate endedDate = LocalDate.now();

    @Column(name = "created_date")
    private LocalDate createdDate = LocalDate.now();

}