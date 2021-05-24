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
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "employers")
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Employer extends User {

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "web_site")
    private String webSite;

    @Column(name = "phone")
    private String phone;

    public Employer(
            int id,
            String email,
            String password,
            String companyName,
            String webSite,
            String phone) {
        super(id, email, password);
        this.companyName = companyName;
        this.webSite = webSite;
        this.phone = phone;
    }


}
