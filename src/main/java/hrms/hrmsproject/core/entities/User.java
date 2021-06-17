package hrms.hrmsproject.core.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email",unique = true)
    @Email
    @NotNull
    @NotBlank
    private String email;

    @Column(name = "password")
    @NotNull
    @NotBlank
    @Size(min = 4,max = 50)
    private String password;

    @Column(name = "created_at")
    @JsonIgnore
    private LocalDate createdDate = LocalDate.now();

    @JsonIgnore
    @Column(name="verify")
    private boolean verify = false;

    @Column(name = "is_active")
    @JsonIgnore
    private boolean active = true;
}
