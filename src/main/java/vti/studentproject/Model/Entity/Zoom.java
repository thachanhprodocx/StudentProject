package vti.studentproject.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "zoom")
public class Zoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "link", unique = true)
    private String link;

    @Column(name = "description")
    private String description;

    @Column(name = "note")
    private String note;

    @Column(name = "meeting_id", unique = true)
    private String meetingId;

    @Column(name = "pass_code")
    private String passCode;
}
