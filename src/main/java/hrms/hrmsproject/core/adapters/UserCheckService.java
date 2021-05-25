package hrms.hrmsproject.core.adapters;

import java.time.LocalDate;
import java.util.Date;

public interface UserCheckService {
    boolean checkIfRealPerson(String nationalityId, String firstName, String lastName, LocalDate dateOfBirthYear);
}
