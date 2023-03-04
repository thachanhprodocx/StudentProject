package vti.studentproject.Service.Class;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vti.studentproject.Exception.AppException;
import vti.studentproject.Exception.ErrorResponseBase;
import vti.studentproject.Model.Entity.Zoom;
import vti.studentproject.Model.Request.ZoomRequest;
import vti.studentproject.Repository.ZoomRepository;
import vti.studentproject.Service.ZoomService;

import java.util.List;

@Service
public class ZoomServiceImpl implements ZoomService {

    @Autowired
    private ZoomRepository zoomRepository;

    @Override
    public List<Zoom> getAllZooms() {
        return zoomRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));// khi trả về 1 danh sách chúng ta cần sắp sếp chúng để dễ dàng quản lý
    }

    @Override
    public Zoom getZoomById(int id) {
        return zoomRepository.findById(id).orElseThrow(() -> new AppException(ErrorResponseBase.Id_not));
    }

    @Override
    public void createZoom(ZoomRequest zoomRequest) {
        Zoom zoomEntity = new Zoom();
        BeanUtils.copyProperties(zoomRequest, zoomEntity);
        zoomRepository.save(zoomEntity);
    }

    @Override
    public Zoom updateZoom(int id, ZoomRequest zoomRequest) {
        Zoom zoomEntity = zoomRepository.findById(id).orElseThrow(() -> new AppException(ErrorResponseBase.Id_not));
        if (zoomEntity != null) {
            BeanUtils.copyProperties(zoomRequest, zoomEntity);
            return zoomRepository.save(zoomEntity);
        }
        return null;
    }

    @Override
    public void deleteZoomById(int id) {
        Zoom zoom = zoomRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorResponseBase.Id_not));
        zoomRepository.delete(zoom);
    }
}
