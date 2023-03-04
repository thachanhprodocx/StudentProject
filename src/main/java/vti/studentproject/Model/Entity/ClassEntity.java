package vti.studentproject.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "class")
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "class_name", unique = true, nullable = false)
    private String className;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "class_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ClassStatus classStatus;

    @Column(name = "teaching_form", nullable = false)
    @Enumerated(EnumType.STRING)
    private TeachingForm teachingForm;

    @ManyToOne(cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "mentor_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "class_fk0"))
    private Account mentorId;

    @ManyToOne(cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "zoom_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "class_fk1"))
    private Zoom zoomId;

    @ManyToOne(cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "class_zoom_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "class_fk2"))
    private ClassRoom classRoomId;

    @Column(name = "description")
    private String description;

    @Column(name = "schedule", nullable = false)
    private String schedule;


}
