package hrms.hrmsproject.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "technologies")
@AllArgsConstructor
@NoArgsConstructor
public class Technology {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonProperty(access = Access.WRITE_ONLY)
    @ManyToOne(targetEntity = Resume.class)
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @Column(name = "description")
    @NotBlank
    private String description;

    @Column(name = "created_date")
    @JsonIgnore
    private LocalDate createdDate = LocalDate.now();

}