package hrms.hrmsproject.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import hrms.hrmsproject.core.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "job_seekers")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
public class JobSeeker extends User {


    @Column(name = "first_name")
    @NotNull
    @NotBlank
    @Size(min = 2,max = 50)
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    @NotBlank
    @Size(min = 2,max = 50)
    private String lastName;

    @Column(name = "national_id",unique = true)
    @NotNull
    @NotBlank
    private String nationalityId;

    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private LocalDate dateOfBirth;

    @Column(name = "is_verified")
    @JsonIgnore
    private boolean isVerified = true;


}
