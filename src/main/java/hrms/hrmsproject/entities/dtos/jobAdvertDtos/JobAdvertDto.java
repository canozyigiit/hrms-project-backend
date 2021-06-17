package hrms.hrmsproject.entities.dtos.jobAdvertDtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class JobAdvertDto  {

    private int id;
    private String employerCompanyName;
    private String employerPhoto;
    private int employerId;
    private String jobPositionName;
    private String description;
    private int openPositionCount;
    private String jobTypeType;
    private String jobWorkSpaceTypeName;
    private String cityName;
    private LocalDate createdDate;
    private LocalDate deadLine;
    private int salaryMin;
    private int salaryMax;
}
