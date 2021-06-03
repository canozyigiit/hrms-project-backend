package hrms.hrmsproject.entities.concretes;

import com.sun.istack.NotNull;
import hrms.hrmsproject.core.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "system_personnels")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name = "user_id", referencedColumnName = "id")
public class SystemPersonnel extends User {

    @Column(name = "first_name")
    @NotNull
    @NotBlank
    @Max(50)
    private String firstName;
    @Column(name = "last_name")
    @NotNull
    @NotBlank
    @Max(50)
    private String lastName;


}
