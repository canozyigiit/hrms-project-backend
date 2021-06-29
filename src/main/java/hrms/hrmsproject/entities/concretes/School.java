package hrms.hrmsproject.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "schools")
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne()
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @Column(name = "school_name")
    @NotBlank
    private String schoolName;

    @ManyToOne(targetEntity = Graduate.class)
    @JoinColumn(name = "graduate_id", referencedColumnName = "id", nullable = false)
    private Graduate graduate;

    @Column(name = "school_department")
    private String schoolDepartment;

    @Column(name = "started_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    @NotNull
    private LocalDate startedDate;

    @Nullable
    @Column(name = "ended_date",nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endedDate;

    @Column(name = "created_date")
    @JsonIgnore
    private LocalDate createdDate = LocalDate.now();
}
