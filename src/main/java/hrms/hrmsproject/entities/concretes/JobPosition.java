package hrms.hrmsproject.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "job_positions")

public class JobPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name= "created_at", columnDefinition = "Date default CURRENT_DATE")
    private LocalDate createdDate = LocalDate.now();

    @Column(name= "is_active", columnDefinition = "boolean default true")
    private boolean isActive = true;
}
