package hrms.hrmsproject.entities.concretes;

import hrms.hrmsproject.core.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "job_seekers")
public class JobSeeker extends User {


    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "nationality_id")
    private String nationalityId;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;


    public JobSeeker(int id, String email, String password, String firstName, String lastName, String nationalityId, Date dateOfBirth) {
        super(id,email,password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalityId = nationalityId;
        this.dateOfBirth = dateOfBirth;
    }
}
