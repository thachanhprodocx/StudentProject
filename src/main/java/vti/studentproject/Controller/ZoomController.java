package vti.studentproject.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vti.studentproject.Model.Entity.Zoom;
import vti.studentproject.Model.Request.ZoomRequest;
import vti.studentproject.Service.Class.ZoomServiceImpl;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@Validated
@RestController
@RequestMapping("/api/zooms")
public class ZoomController {

    @Autowired
    private ZoomServiceImpl zoomService;


    @GetMapping("get-all")
    public List<Zoom> getAllZooms() {
        return zoomService.getAllZooms();

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getZoomById(@PathVariable("id") int id) {
        Zoom zoom = zoomService.getZoomById(id);
        return ResponseEntity.status(HttpStatus.OK).body(zoom);
    }

    @PostMapping
    public ResponseEntity<Void> createZoom(@RequestBody @Valid ZoomRequest zoomRequest) {
        zoomService.createZoom(zoomRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Zoom> updateZoom(@PathVariable("id") int id, @RequestBody ZoomRequest zoomRequest) {
        Zoom zoom = zoomService.updateZoom(id, zoomRequest);
        return new ResponseEntity<>(zoom, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZoomById(@PathVariable("id") int id) {
        zoomService.deleteZoomById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
