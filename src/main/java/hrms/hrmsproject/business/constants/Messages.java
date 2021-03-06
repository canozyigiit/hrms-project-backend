package hrms.hrmsproject.business.constants;

public class Messages {

    //Employer
    public static String employerAdded = "Employer Added";
    public static String employerGetAll = "Employers Listed";
    public static String employerGet = "Employer Found";
    public static String employerDeleted = "Employer Deteled";
    public static String employerEmailExists = "There is already an employer with this email";
    public static String employerFieldCheck = "All fields are required";
    public static String errorEmployerEmailNotCorporate = "Your e-mail address is not corporate";
    public static String errorEmployerEmailValid = "Employer email is not correct";


    //JobPosition
    public static String jobPositionAdded = "Job Position Added";
    public static String jobPositionGetAll = "Job Position Listed";
    public static String jobPositionGet = "Job Position Found";
    public static String jobPositionDeleted = "Job Position Deteled";
    public static String jobPositionNameLengthError = "Job position name must be longer than 3 characters";
    public static String jobPositionExists = "Job position with this name already exists";

    //JobSeeker
    public static String jobSekeerAdded = "Job Sekeer Added";
    public static String jobSekeerGetAll = "Job Sekeer Listed";
    public static String jobSekeerGet = "Job Sekeer Found";
    public static String jobSekeerDeleted = "Job Sekeer Deteled";
    public static String errorjobSeekerEmail = "The job seeker email is invalid";
    public static String jobSeekerEmailExists = "There is already an job seeker with this email";
    public static String jobSeekerFieldCheck = "All fields are required";
    public static String jobSeekerNationalIdExists = " The user with this national ID already exists";
    public static String nationalIdLengthError = "National ID must be 11 digits";
    public static String notRealPerson = "Not a real user information";

    //User
    public static String userAdded = "User Added";
    public static String userGetAll = "User Listed";
    public static String userGet = "User Found";
    public static String userDeleted = "User Deteled";
    public static String userNotFound = "User not found";


    //SystemPersonnel
    public static String systemPersonnelAdded = "System Personnel Added";
    public static String systemPersonnelGetAll = "System Personnel Listed";
    public static String systemPersonnelGet = "System Personnel Found";
    public static String systemPersonnelDeleted = "System Personnel Deteled";

    //Auth
    public static String successFullLogin = "Login to the system is successful.";
    public static String userAlreadyExists = "This user already exists.";
    public static String userRegistered = "Registration is complete.";
    public static String passwordError = "Password Error!";
    public static String passwordConfirmError = "Passwords do not match.";
    public static String authFieldsError = "All fields are required";
    public static String mailVerification = "Verification has been sent to the mail :";

    //City
    public static String CityAdded = "City Added";
    public static String CityListed = "Cities Listed";
    public static String cityExists = "such a city already exists";

    //Job Advert
    public static String JobAdvertAdded = "Job Advert Added";
    public static String JobAdvertUpdated = "Job Advert Updated";
    public static String JobAdvertDeleted = "Job Advert Deleted";

    public static String allActivePositonsInCompany = "open job postings of the company are listed";
    public static String allActivePositionsListed = "Open job adverts listed";
    public static String JobAdvertListed = "Job Advert Listed";
    public static String JobAdvertGet = "Job Advert Found";
    public static String jobAdvertFieldCheck = "job advert fields cannot be left blank";
    public static String openPositionCountError = "work position is not less than zero";
    public static String salaryError = "min salary cannot be greater than max salary";
    public static String mixSalaryEqualsMaxSalary = "The min salary and the max salary cannot be equated.";
    public static String jobAdvertNotFound = "job adverts not found";
    public static String jobAdvertClosed = "Job adverts are disabled";

    //Graduate
    public static String graduateAdded;
    public static String graduateListed;
    public static String graduateGet;

    //JobExperience
    public static String jobExperienceAdded;
    public static String jobExperienceListed;
    public static String jobExperienceGet;

    //Language
    public static String languageAdded;
    public static String languageListed;
    public static String languageGet;
    //Resume
    public static String resumeAdded;
    public static String resumeListed;
    public static String resumeGet;
    
    //School
    public static String schoolAdded;
    public static String schoolListed;
    public static String schoolGet;
    
    //Technology
    public static String technologyAdded;
    public static String technologyListed;
}
