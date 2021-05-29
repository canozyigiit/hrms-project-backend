package hrms.hrmsproject.entities.dtos.jobAdvertDtos;

import hrms.hrmsproject.core.entities.Dto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class JobAdvertDetailDto implements Dto {
    private String companyName;
    private String name;
    private int openPositionCount;
    private LocalDate deadLine;
}
