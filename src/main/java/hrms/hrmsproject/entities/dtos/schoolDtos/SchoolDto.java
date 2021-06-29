package hrms.hrmsproject.entities.dtos.schoolDtos;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class SchoolDto {


    private int id;
    @NotNull
    private int resumeId;
    @NotBlank
    private String schoolName;
    @NotNull
    private int graduateId;
    private String schoolDepartment;
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate startedDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endedDate;
}
