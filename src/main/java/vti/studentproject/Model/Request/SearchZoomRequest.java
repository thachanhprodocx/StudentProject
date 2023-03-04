package vti.studentproject.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchZoomRequest {
    private String name;


    private String link;

    private String description;

    private String note;

    private String meetingId;

    private String passCode;
}
