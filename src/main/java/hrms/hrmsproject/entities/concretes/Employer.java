package hrms.hrmsproject.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hrms.hrmsproject.core.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "employers")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name = "user_id" , referencedColumnName = "id")
public class Employer extends User {

    @Column(name = "company_name")
    @NotBlank
    @Size(min = 2,max = 100)
    private String companyName;

    @Column(name = "website")
    @NotBlank
    @Size(min = 2,max = 100)
    @Pattern(regexp = "(www)?\\.?[a-zA-Z0-9]+\\.?[a-zA-Z]{2,}")
    private String webSite;

    @Column(name = "phone")
    @NotBlank
    @Pattern(regexp ="^(\\d{3}[- .]?){2}\\d{4}$",message = "Telefon numarasını lütfen doğru giriniz.(Ör:***-***-**** )")
    private String phone;

    @Column(name = "is_verified")
    @JsonIgnore
    private boolean isVerified = false;


    @Column(name = "photo")
    private String photo;

    @JsonIgnore
    @OneToMany(mappedBy = "employer")
    private List<JobAdvert> jobAdverts;


}
