package hrms.hrmsproject.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job_positions")
public class JobPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotBlank
    @Size(min = 2,max = 100)
    private String name;

    @Column(name= "created_date")
    @JsonIgnore
    private LocalDate createdDate = LocalDate.now();

    @Column(name= "is_active")
    @JsonIgnore
    private boolean isActive = true;

    @JsonIgnore
    @OneToMany(mappedBy = "jobPosition")
    private List<JobAdvert> jobAdverts;
}
