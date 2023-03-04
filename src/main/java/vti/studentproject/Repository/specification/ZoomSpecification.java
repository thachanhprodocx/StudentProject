package vti.studentproject.Repository.specification;


import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import vti.studentproject.Model.Entity.Zoom;
import vti.studentproject.Model.Request.SearchZoomRequest;

public class ZoomSpecification {


    public static Specification<Zoom> buildCondition(SearchZoomRequest req) {
        return Specification.where(searchByZoomName(req.getName()))
                .and(searchByDescription(req.getDescription()))
                .and(searchByLink(req.getLink()))
                .and(searchByNote(req.getNote()))
                .and(searchByMeetingId(req.getMeetingId()))
                .and(searchByPassCode(req.getPassCode()));
    }

    private static Specification<Zoom> searchByZoomName(String zoomName) {
        if (StringUtils.isNotBlank(zoomName)) {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("zoomName"), "%" + zoomName + "%");
            };
        } else return null;
    }

    private static Specification<Zoom> searchByLink(String link) {
        if (StringUtils.isNotBlank(link)) {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.equal(root.get("link"), link);
            };
        } else return null;
    }

    private static Specification<Zoom> searchByDescription(String description) {
        if (StringUtils.isNotBlank(description)) {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("description"), "%" + description + "%");
            };
        } else return null;
    }


    private static Specification<Zoom> searchByNote(String note) {
        if (StringUtils.isNotBlank(note)) {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("note"), "%" + note + "%");
            };
        } else return null;
    }


    private static Specification<Zoom> searchByMeetingId(String meetingId) {
        if (StringUtils.isNotBlank(meetingId)) {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.equal(root.get("description"), meetingId);
            };
        } else return null;
    }
    private static Specification<Zoom> searchByPassCode(String passCode) {
        if (StringUtils.isNotBlank(passCode)) {
            return (root, query, criteriaBuilder) -> {
                return criteriaBuilder.equal(root.get("description"), passCode);
            };
        } else return null;
    }
}
