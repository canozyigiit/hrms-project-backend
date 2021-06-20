package hrms.hrmsproject.dataAccess.abstracts;

import hrms.hrmsproject.entities.concretes.EmailVerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailVerificationCodeDao extends JpaRepository<EmailVerificationCode,Integer> {

    EmailVerificationCode getByEmailVerificationCode(String Code);
    boolean existsByEmailVerificationCode(String Code);

    EmailVerificationCode getByUserId(int id);
}
