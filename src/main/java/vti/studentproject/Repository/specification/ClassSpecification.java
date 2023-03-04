package vti.studentproject.Repository.specification;

import org.springframework.data.jpa.domain.Specification;
import vti.studentproject.Model.Entity.*;
import vti.studentproject.Model.Request.SearchClassReq;

import javax.persistence.criteria.Join;
import java.util.Date;

public class ClassSpecification {

    public static Specification<Class> buildCondition(SearchClassReq req) {
        return Specification.where(searchByClassName(req.getClassName()))
                .and(searchByStartDate(req.getStartDate()))
                .and(searchByEndDate(req.getEndDate()))
                .and(searchByMentorId(req.getMentorId()))
                .and(searchByClassroomId(req.getClassRoomId()))
                .and(searchByZoomId(req.getZoomId()))
                .and(searchBySchedule(req.getSchedule()))
                .and(searchByClassStatus(req.getClassStatus()))
                .and(searchByTeachingForm(req.getTeachingForm()));
    }

    private static Specification<Class> searchByClassName(String className) {
        if (className != null) {
            return ((root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("className"), "%" + className + "%");
            });
        } else {
            return null;
        }
    }

    ;

    private static Specification<Class> searchByStartDate(Date startDate) {
        if (startDate != null) {
            return ((root, query, criteriaBuilder) -> {
                return criteriaBuilder.lessThanOrEqualTo(root.get("startDate"), "%" + startDate + "%");
            });
        } else {
            return null;
        }
    }

    ;

    private static Specification<Class> searchByEndDate(Date endDate) {
        // So sanh ngay ko dung like, dung greater, ...
        if (endDate != null) {
            return ((root, query, criteriaBuilder) -> {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("endDate"), "%" + endDate + "%");
            });
        } else {
            return null;
        }
    }

    ;

    private static Specification<Class> searchByMentorId(int id) {
        if (id > 0) {
            // Kieemr tra dieu kien tim kiem (Su dung root -> join)
            return ((root, query, criteriaBuilder) -> {
                Join<Class, Account> joiner = root.join("mentorId");
                return criteriaBuilder.equal(joiner.get("id"), id);
            });
        } else {
            return null;
        }
    }

    private static Specification<Class> searchByZoomId(int zoomId) {
        if (zoomId > 0) {
            // Kieemr tra dieu kien tim kiem (Su dung root -> join)
            return ((root, query, criteriaBuilder) -> {
                Join<Class, Zoom> joiner = root.join("zoomId");
                return criteriaBuilder.equal(joiner.get("id"), zoomId);
            });
        } else {
            return null;
        }
    }

    private static Specification<Class> searchByClassroomId(int classRoomId) {
        if (classRoomId > 0) {
            // Kieemr tra dieu kien tim kiem (Su dung root -> join)
            return ((root, query, criteriaBuilder) -> {
                Join<Class, ClassRoom> joiner = root.join("classRoomId");
                return criteriaBuilder.equal(joiner.get("id"), classRoomId);
            });
        } else {
            return null;
        }
    }

    private static Specification<Class> searchBySchedule(String schedule) {
        if (schedule != null) {
            return ((root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("schedule"), "%" + schedule + "%");
            });
        } else {
            return null;
        }
    }

    ;

    private static Specification<Class> searchByClassStatus(ClassStatus classStatus) {
        if (classStatus != null) {
            return ((root, query, criteriaBuilder) -> {
                return criteriaBuilder.equal(root.get("classStatus"), classStatus);
            });
        } else {
            return null;
        }
    }

    ;

    private static Specification<Class> searchByTeachingForm(TeachingForm teachingForm) {
        if (teachingForm != null) {
            return ((root, query, criteriaBuilder) -> {
                return criteriaBuilder.equal(root.get("teachingForm"), teachingForm);
            });
        } else {
            return null;
        }
    }

    ;
}
