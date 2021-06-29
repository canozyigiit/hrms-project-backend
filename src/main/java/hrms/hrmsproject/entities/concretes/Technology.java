package hrms.hrmsproject.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "technologies")
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Technology {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "resume_id")
    @JsonIgnore
    private Resume resume;

    @Column(name = "description")
    @NotBlank
    private String description;

    @Column(name = "created_date")
    @JsonIgnore
    private LocalDate createdDate = LocalDate.now();

}