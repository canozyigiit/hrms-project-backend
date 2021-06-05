package hrms.hrmsproject.entities.dtos.jobExperienceDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class JobExperienceDto {

    @JsonIgnore
    private int id;
    private int resumeId;
    @NotBlank
    private String companyName;
    @NotBlank
    private String position;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @Past
    private LocalDate startedDate;

    @Column(name = "ended_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endedDate;
}
