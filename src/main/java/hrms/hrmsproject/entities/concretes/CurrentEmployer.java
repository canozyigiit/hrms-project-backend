package hrms.hrmsproject.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "current_employer")
@AllArgsConstructor
@NoArgsConstructor
public class CurrentEmployer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name="employer_id")
    private int employerId;
    @Column(name="email")
    private String email;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "website")
    private String webSite;
    @Column(name = "phone")
    private String phone;
    @Column(name="team_size")
    private int teamSize;
    @Column(name="since")
    private int since;
    @Column(name = "photo")
    private String photo;
    @ManyToOne()
    @JoinColumn(name = "city_id")
    private City city;


}
