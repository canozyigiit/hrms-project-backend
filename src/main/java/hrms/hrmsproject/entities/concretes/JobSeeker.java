package hrms.hrmsproject.entities.concretes;

import hrms.hrmsproject.core.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "user_id",referencedColumnName="id")
@Table(name = "job_seekers")
public class JobSeeker extends User {


    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "national_id")
    private String nationalityId;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "is_verified", columnDefinition = "boolean default false")
    private boolean isVerified = false;


}
