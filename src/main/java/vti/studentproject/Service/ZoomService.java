package vti.studentproject.Service;


import org.springframework.stereotype.Service;
import vti.studentproject.Model.Entity.Zoom;
import vti.studentproject.Model.Request.ZoomRequest;

import java.util.List;

@Service
public interface ZoomService {
    List<Zoom> getAllZooms();

    Zoom getZoomById(int id);

    void createZoom(ZoomRequest zoomRequest);

    Zoom updateZoom(int id, ZoomRequest zoomRequest);

    void deleteZoomById(int id);
}
