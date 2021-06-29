package hrms.hrmsproject.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonProperty.Access;
import hrms.hrmsproject.core.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "employers")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name = "user_id" , referencedColumnName = "id")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","jobAdverts"})

public class Employer extends User {

    @Column(name = "company_name")
    @NotBlank
    @Size(min = 2,max = 100)
    private String companyName;

    @Column(name = "website")
    @NotBlank
    @Size(min = 2,max = 100)
    @Pattern(regexp = "(www)?\\.?[a-zA-Z0-9]+\\.?[a-zA-Z]{2,}",message = "Website is not valid.")
    private String webSite;

    @Column(name = "phone")
    @NotBlank
    @Pattern(regexp ="^(\\d{3}[- .]?){2}\\d{4}$",message = "Phone number is not valid.(Ör:***-***-**** )")
    private String phone;

    @Nullable
    @Column(name="team_size")
    private int teamSize;

    @Nullable
    @Column(name="since")
    private int since;

    @Column(name = "is_confirmed")
    private boolean isConfirmed = false;

    @Column(name = "is_updated")
    private boolean isUpdated = false;

    @Column(name = "updated_date")
    private LocalDate updatedDate ;

    @Column(name = "photo")
    @JsonProperty(access = Access.READ_ONLY)
    private String photo;

    @JsonIgnore
    @OneToMany(mappedBy = "employer")
    private List<JobAdvert> jobAdverts;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "city_id")
    private City city;




}
