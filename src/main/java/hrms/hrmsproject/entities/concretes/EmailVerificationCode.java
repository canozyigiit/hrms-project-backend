package hrms.hrmsproject.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hrms.hrmsproject.core.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "verification_codes")
public class EmailVerificationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(targetEntity = User.class ,fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName =  "id" ,nullable = false)
    private User userId;


    @Column(name = "email_verification_code")
    private String emailVerificationCode;

    @Column(name = "is_confirmed")
    private boolean isConfirmed;

    @Column(name = "created_date")
    @JsonIgnore
    private LocalDate createdDate;

    @Column(name = "confirmed_date")
    private LocalDate confirmedDate;
}
