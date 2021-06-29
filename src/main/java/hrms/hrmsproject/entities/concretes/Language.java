package hrms.hrmsproject.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
@Entity
@Table(name = "languages")
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Language {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(name = "resume_id")
    @JsonIgnore
    private Resume resume;

    @Column(name = "language")
    @NotBlank
    private String languageName;

    @Column(name = "lang_level")
    @NotNull
    @Min(1)
    @Max(5)
    private int languageLevel;

    @Column(name = "created_date")
    @JsonIgnore
    private LocalDate createdDate = LocalDate.now();


}
