package hrms.hrmsproject.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "cities")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobAdverts"})

public class City {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    @Column(name = "name",unique = true)
    @NotBlank
    @Size(min = 2,max = 50)
    private String name;


    @JsonIgnore
    @OneToMany(mappedBy = "city")
    private List<JobAdvert> jobAdverts;

    @JsonIgnore
    @OneToMany(mappedBy = "city")
    private List<Employer> employers;
    @JsonIgnore
    @OneToMany(mappedBy = "city")
    private List<CurrentEmployer> currentEmployers;
}
