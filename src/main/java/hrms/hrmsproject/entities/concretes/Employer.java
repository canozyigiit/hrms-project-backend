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
@Table(name = "employers")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@PrimaryKeyJoinColumn(name = "user_id")
public class Employer extends User {

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "website")
    private String webSite;

    @Column(name = "phone")
    private String phone;

    @Column(name = "is_verified")
    private boolean isVerified = false;




}
