package hrms.hrmsproject.entities.concretes;

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

    @Column(name = "user_id")
    private int userId;//kimin kodu

    @Column(name = "email_verification_code")
    private String emailVerificationCode;//dogrulamakodu

    @Column(name = "is_confirmed")
    private boolean isConfirmed;//onaylanmabilgisi

    @Column(name = "created_date")
    private LocalDate createdDate;//oluşturulmatarihi

    @Column(name = "confirmed_date")
    private LocalDate confirmedDate;//onaylanmatarihi
}
