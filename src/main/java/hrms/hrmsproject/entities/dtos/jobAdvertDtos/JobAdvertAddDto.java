package hrms.hrmsproject.entities.dtos.jobAdvertDtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
public class JobAdvertAddDto {


    private int id;
    @NotNull
    private int cityId;
    @NotNull
    private int jobPositionId;
    @NotNull
    private int employerId;
    @NotBlank
    private String description;
    @Nullable
    private int salaryMin;
    @Nullable
    private int salaryMax;
    @JsonIgnore
    private boolean isOpen =true;
    @JsonIgnore
    private boolean isActive =true;
    @JsonIgnore
    private boolean isConfirmed =false;
    @NotNull
    private int jobTypeId;
    @NotNull
    private int jobWorkSpaceTypeId;
    @NotNull
    private int openPositionCount;
    @NotNull
    private LocalDate deadLine;
}
