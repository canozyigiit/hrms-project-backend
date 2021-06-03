package hrms.hrmsproject.adapters;

import hrms.hrmsproject.entities.concretes.JobSeeker;

public interface UserCheckService {
    boolean checkIfRealPerson(JobSeeker jobSeeker);
}
