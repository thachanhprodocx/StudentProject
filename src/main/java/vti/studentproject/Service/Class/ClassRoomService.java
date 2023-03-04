package vti.studentproject.Service.Class;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vti.studentproject.Exception.AppException;
import vti.studentproject.Exception.ErrorResponseBase;
import vti.studentproject.Model.Entity.Account;
import vti.studentproject.Model.Entity.ClassRoom;
import vti.studentproject.Model.Request.ClassRoomRequest;
import vti.studentproject.Repository.AccountRepository;
import vti.studentproject.Repository.ClassRoomRepository;
import vti.studentproject.Service.IClassRoomService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClassRoomService implements IClassRoomService {
    @Autowired
    private ClassRoomRepository classRoomRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<ClassRoom> getAllClassRooms() {

        return classRoomRepository.findAll();
    }

    @Override
    public void createClassRoom(ClassRoomRequest classRoomRequest) {
        // Lấy thông tin mentor từ id được gửi lên
        Optional<Account> optional = accountRepository.findById(classRoomRequest.getMentorId());

        // Nếu mentor không tồn tại, throw ra exception hoặc xử lý tùy ý
        if (!optional.isPresent()) {
            throw new RuntimeException("Mentor not found!");
        }

        // Tạo một đối tượng ClassRoom mới với thông tin từ request và mentor lấy được từ id
        ClassRoom classRoom = new ClassRoom();
        classRoom.setName(classRoomRequest.getName());
        classRoom.setAddress(classRoomRequest.getAddress());
        classRoom.setNote(classRoomRequest.getNote());
        classRoom.setSize(classRoomRequest.getSize());


        // Lưu đối tượng mới vào database
        classRoomRepository.save(classRoom);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteClassRoom(int id) {
        Optional<ClassRoom> optional = classRoomRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppException(ErrorResponseBase.NOT_FOUND);
        }
        classRoomRepository.delete(optional.get());
    }

    @Override
    public ClassRoom getClassRoomById(int id) {
        Optional<ClassRoom> optional = classRoomRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            // xử lý nếu không tìm thấy
            throw new AppException(ErrorResponseBase.INVALID_Room);

        }
    }

    @Override
    public ClassRoom updateClassRoom(int id, ClassRoomRequest classRoomRequest) {
        ClassRoom classRoomDb = classRoomRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorResponseBase.NOT_FOUND));
        BeanUtils.copyProperties(classRoomRequest, classRoomDb);
        return classRoomRepository.save(classRoomDb);
    }
}
