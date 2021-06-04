package hrms.hrmsproject.entities.dtos.languageDtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class LanguageDto {


    @JsonIgnore
    private int id;
    private int resumeId;
    @NotBlank
    private String languageName;
    @NotNull
    @Min(1)
    @Max(5)
    private int languageLevel;
}
