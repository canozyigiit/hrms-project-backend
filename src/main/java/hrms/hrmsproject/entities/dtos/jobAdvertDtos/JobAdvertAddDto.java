package hrms.hrmsproject.entities.dtos.jobAdvertDtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;



@Data
public class JobAdvertAddDto{

    @JsonIgnore
    private int id;
    private int cityId;
    private int jobPositionId;
    private int employerId;
    @NotNull
    @NotBlank
    @Size(min = 10,max = 250)
    private String description;
    private Integer salaryMin;
    private Integer salaryMax;
    private boolean isOpen;
    @NotNull
    @Min(value = 1,message = "açık iş pozisyonu adedi 1 den düşük olamaz")
    private int openPositionCount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    @com.sun.istack.NotNull
    @NotBlank
    private LocalDate deadLine;
}
