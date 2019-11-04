package com.gl.ceir.config.specification;

import static org.springframework.data.jpa.domain.Specifications.where;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.gl.ceir.config.model.ConsignmentMgmt;

public class ConsignmentManagementSpecification {

	private final String wildcard = "%";

	/*public Specification<ConsignmentMgmt> getFilter(ConsignmentMgmt request) {

		return new Specification<ConsignmentMgmt>() {
			@Override
			public Predicate toPredicate(Root<ConsignmentMgmt> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.or(root.getModel().getDeclaredSingularAttributes().stream().filter(a -> {
                    if (a.getJavaType().getSimpleName().equalsIgnoreCase("string")) {
                        return true;
                    } else {
                        return false;
                    }
                }).map(a -> builder.like(root.get(a.getName()), finalText)).toArray(Predicate[]::new));
			}
        };
	}*/

	private Specification<ConsignmentMgmt> firstNameContains(String firstName) {
		return userAttributeContains("consignmentStatus", firstName);
	}

	private Specification<ConsignmentMgmt> lastNameContains(String lastName) {
		return userAttributeContains("taxPaidStatus", lastName);
	}

	/*  private Specification<ConsignmentMgmt> emailContains(String email) {
        return userAttributeContains("email", email);
    }*/

	private Specification<ConsignmentMgmt> userAttributeContains(String attribute, String value) {
		return (root, query, cb) -> {
			if(value == null) {
				return null;
			}

			return cb.like(
					cb.lower(root.get(attribute)),
					containsLowerCase(value)
					);
		};
	}

	protected String containsLowerCase(String searchField) {
		return wildcard + searchField.toLowerCase() + wildcard;
	}


	/*    private Specification<ConsignmentMgmt> cityContains(String city) {
        return addressAttributeContains("city", city);
    }

    private Specification<ConsignmentMgmt> streetContains(String street) {
        return addressAttributeContains("street", street);
    }

    private Specification<ConsignmentMgmt> addressAttributeContains(String attribute, String value) {
        return (root, query, cb) -> {
            if(value == null) {
                return null;
            }

            ListJoin<User, Address> addresses = root.joinList("addresses", JoinType.INNER);

            return cb.like(
                cb.lower(addresses.get(attribute)),
                containsLowerCase(value)
            );
        };
    }*/

}
