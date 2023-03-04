package vti.studentproject.Repository.specification;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import vti.studentproject.Model.Entity.ClassRoom;
import vti.studentproject.Model.Request.SearchClassRoomReq;


public class ClassRoomSpecification {

    public static Specification<ClassRoom> buildCondition(SearchClassRoomReq req) {
        return Specification.where(searchByName(req.getName()))
                .and(searchByAddress(req.getAddress()))
                .and(searchByNote(req.getNote()))
                .and(searchBySize(req.getSize()));
    }

    private static Specification<ClassRoom> searchByName(String roomName) {
        if (StringUtils.isNotBlank(roomName)) {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("roomName"), "%" + roomName + "%");
            };
        } else {
            return null;
        }
    }

    private static Specification<ClassRoom> searchByAddress(String address) {
        if (StringUtils.isNotBlank(address)) {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("address"), "%" + address + "%");
            };
        } else {
            return null;
        }
    }

    private static Specification<ClassRoom> searchByNote(String note) {
        if (StringUtils.isNotBlank(note)) {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("note"), "%" + note + "%");
            };
        } else {
            return null;
        }
    }


    private static Specification<ClassRoom> searchBySize(int size) {
        if (size > 0) {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("size"), size);
            };
        } else {
            return null;
        }

    }


}
