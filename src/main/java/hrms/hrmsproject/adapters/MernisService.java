package hrms.hrmsproject.adapters;

import Mernis.DAGKPSPublicSoap;
import hrms.hrmsproject.entities.concretes.JobSeeker;
import org.springframework.stereotype.Component;

@Component
public class MernisService implements UserCheckService {
    @Override
    public boolean checkIfRealPerson(JobSeeker jobSeeker) {
        DAGKPSPublicSoap client = new DAGKPSPublicSoap();
        try {
            return client.TCKimlikNoDogrula(Long.parseLong(jobSeeker.getNationalityId()), jobSeeker.getFirstName(), jobSeeker.getLastName(), jobSeeker.getDateOfBirth().getYear());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
