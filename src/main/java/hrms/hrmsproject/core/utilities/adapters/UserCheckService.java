package hrms.hrmsproject.core.utilities.adapters;

import hrms.hrmsproject.entities.concretes.JobSeeker;

public interface UserCheckService {
    boolean checkIfRealPerson(JobSeeker jobSeeker);
}
