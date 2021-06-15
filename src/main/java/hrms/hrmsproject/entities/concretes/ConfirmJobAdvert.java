package hrms.hrmsproject.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "confirm_job_advert")
public class ConfirmJobAdvert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private int id;

    @OneToOne(targetEntity = JobAdvert.class)
    @JoinColumn(name="job_advert_id", referencedColumnName="id")
    private JobAdvert jobAdvert;

    @OneToOne(targetEntity = SystemPersonnel.class)
    @JoinColumn(name="system_personnel_id", referencedColumnName="user_id")
    private SystemPersonnel systemPersonnel;

    @Column(name="confirmed_date")
    private LocalDate confirmedDate;

    @Column(name="is_confirmed")
    private boolean isConfirmed;
}
