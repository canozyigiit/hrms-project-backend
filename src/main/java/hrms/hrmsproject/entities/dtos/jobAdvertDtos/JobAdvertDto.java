package hrms.hrmsproject.entities.dtos.jobAdvertDtos;

import hrms.hrmsproject.core.entities.Dto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class JobAdvertDto implements Dto {
    private int id;
    private String employerCompanyName;
    private String employerPhoto;
    private String jobPositionName;
    private int openPositionCount;
    private String cityName;
    private LocalDate createdDate;
    private LocalDate deadLine;
    private int salaryMin;
    private int salaryMax;
}
