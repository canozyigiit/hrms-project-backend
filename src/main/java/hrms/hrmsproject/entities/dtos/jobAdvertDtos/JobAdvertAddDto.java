package hrms.hrmsproject.entities.dtos.jobAdvertDtos;

import hrms.hrmsproject.core.entities.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertAddDto implements Dto {

    private int cityId;
    private int jobPositionId;
    private int employerId;
    private String description;
    private Integer minSalary;
    private Integer maxSalary;
    private int openPositionCount;
    private LocalDate deadLine;
}
