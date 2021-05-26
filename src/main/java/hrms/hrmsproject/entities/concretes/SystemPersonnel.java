package hrms.hrmsproject.entities.concretes;

import hrms.hrmsproject.core.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "system_personnels")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name = "user_id")
public class SystemPersonnel extends User {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;


}
