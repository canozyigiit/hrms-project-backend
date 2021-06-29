package hrms.hrmsproject.entities.dtos.jobExperienceDto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class JobExperienceDto {

    private int id;
    @NotNull
    private int resumeId;
    @NotBlank
    private String companyName;
    @NotBlank
    private String position;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @Past
    private LocalDate startedDate;

    @Nullable
    private LocalDate endedDate;
}
