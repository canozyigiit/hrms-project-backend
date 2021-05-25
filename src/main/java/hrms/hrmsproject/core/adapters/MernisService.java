package hrms.hrmsproject.core.adapters;

import Mernis.DAGKPSPublicSoap;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Component
public class MernisService implements UserCheckService{
    @Override
    public boolean checkIfRealPerson(String nationalityId, String firstName, String lastName, LocalDate dateOfBirthYear) {
        DAGKPSPublicSoap client = new DAGKPSPublicSoap();
        boolean result = true;
        try {
            result= client.TCKimlikNoDogrula(Long.parseLong(nationalityId),firstName,lastName,dateOfBirthYear.getYear());
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
