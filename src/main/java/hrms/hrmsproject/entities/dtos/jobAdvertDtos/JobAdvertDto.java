package hrms.hrmsproject.entities.dtos.jobAdvertDtos;

import hrms.hrmsproject.core.entities.Dto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class JobAdvertDto implements Dto {
    private String employerCompanyName;
    private String jobPositionName;
    private int openPositionCount;
    private LocalDate createdDate;
    private LocalDate deadLine;
}
