package vti.studentproject.Repository.specification;

import org.springframework.data.jpa.domain.Specification;
import vti.studentproject.Model.Entity.Account;
import vti.studentproject.Model.Entity.Role;
import vti.studentproject.Model.Request.SearchAccountReq;

import javax.persistence.criteria.Join;
import java.util.Set;

public class AccountSpecification {

    public static Specification<Account> buildCondition(SearchAccountReq req) {
        return Specification.where(searchByFullname(req.getFullName()))
                .and(searchByRole(req.getRole()))
                .and(searchByAddress(req.getAddress()))
                .and(searchByEmail(req.getEmail()))
                .and(searchByClassId(req.getClassId()))
                .and(searchByUserName(req.getUsername()))
                .and(byPriceMin(req.getMinPrice()))
                .and(byPriceMax(req.getMaxPrice()));
    }

    private static Specification<Account> searchByFullname(String fullName) {
        if (fullName != null) {
            return ((root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("fullName"), "%" + fullName + "%");
            });
        } else {
            return null;
        }
    }

    private static Specification<Account> searchByRole(Set<Role> role) {
        if (role != null) {
            return ((root, query, criteriaBuilder) -> {
                return criteriaBuilder.equal(root.get("role"), role);
            });
        } else {
            return null;
        }
    }

    private static Specification<Account> searchByEmail(String email) {
        if (email != null) {
            return ((root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("email"), "%" + email + "%");
            });
        } else {
            return null;
        }
    }

    private static Specification<Account> searchByClassId(int id) {
        if (id > 0) {
            // Kieemr tra dieu kien tim kiem (Su dung root -> join)
            return ((root, query, criteriaBuilder) -> {
                Join<Account, Class> joiner = root.join("classId");
                return criteriaBuilder.equal(joiner.get("id"), id);
            });
        } else {
            return null;
        }
    }

    private static Specification<Account> searchByAddress(String andress) {
        if (andress != null) {
            return ((root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("address"), "%" + andress + "%");
            });
        } else {
            return null;
        }
    }

    private static Specification<Account> searchByUserName(String username) {
        if (username != null) {
            return ((root, query, criteriaBuilder) -> {
                return criteriaBuilder.like(root.get("username"), "%" + username + "%");
            });
        } else {
            return null;
        }
    }
    private static Specification<Account> byPriceMin(Long minPrice) {
        if (minPrice != null) {
            return (root, query, cri) -> {
                return cri.greaterThanOrEqualTo(root.get("price"), minPrice);
            };
        }
        return null;
    }

    private static Specification<Account> byPriceMax(Long maxPrice) {
        if (maxPrice != null) {
            return (root, query, cri) -> {
                return cri.lessThanOrEqualTo(root.get("price"), maxPrice);
            };
        }
        return null;
    }
}

