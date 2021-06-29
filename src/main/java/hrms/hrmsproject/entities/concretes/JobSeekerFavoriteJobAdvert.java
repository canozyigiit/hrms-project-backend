package hrms.hrmsproject.entities.concretes;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "job_seeker_favorite_job_adverts")
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerFavoriteJobAdvert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id" ,referencedColumnName = "user_id")
    private JobSeeker jobSeeker;


    @ManyToOne()
    @JoinColumn(name = "job_advert_id",referencedColumnName = "id")
    private JobAdvert jobAdvert;


}
