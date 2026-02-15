package com.uniflix.model.persons;

import com.uniflix.model.ref.OpUserProfile;
import com.uniflix.model.security.IdProvider;
import com.uniflix.model.security.UserAuthority;
import com.uniflix.model.security.UserPreferences;
import com.uniflix.model.security.UserRights;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Document(collection = "OpUsers")
@Getter
@Setter
public class OpUser implements Serializable {

	@Id
	private String id;

	@Indexed(direction = IndexDirection.ASCENDING)
	protected List<String> identity;					 		// compatibility to exist-db
	@Indexed(direction = IndexDirection.ASCENDING)
	protected String uid;										// CAS 'uid' property e.g. gatzonis
	@Indexed(direction = IndexDirection.ASCENDING)
	protected String email; 									// CAS 'mail' property e.g. gatzonis
	@Indexed(direction = IndexDirection.ASCENDING)
	protected String departmentId;  							// Ask user on first login or updated by admin

	protected String eduPersonOrgUnitDN;
	protected String eduPersonPrimaryOrgUnitDN;

	protected String name;										// CAS 'cn;lang-el' property e.g. Μιχάλης Γκατζώνης
	protected String altName;									// CAS 'cn' property e.g Michalis Gatzonis
	protected String affiliation;								// CAS 'title;lang-el' property e.g. Διοικητικό Προσωπικό
	protected String eduPersonPrimaryAffiliation;				// CAS 'eduPersonPrimaryAffiliation' property e.g. Staff
	protected List<String> eduPersonAffiliation;				// CAS 'eduPersonAffiliation' property e.g. Staff  ? maybe array

	protected List<String> userCourses;							 // STAFF_MEMBERS's own Courses
	protected boolean active;									// OpUser is active or not
	protected String password;
	protected Instant lastLogin;
	protected String token;

	protected Instant dateModified;
	protected String editor;

	protected OpUserProfile userProfile;

	protected List<UserAuthority> userAuthorities;     			// OpenDelos Roles(s): MANAGER, STAFF_MEMBER (CAS: Faculty,Staff), STUDENT
	protected UserRights userRights;							// OpenDelos Rights on other's courses, services (content, data, scheduler)
	protected UserPreferences userPreferences;

	protected IdProvider idProvider = IdProvider.SSO_USER;

	@SuppressWarnings("unused")
	public String getName(Locale locale) {

		if (locale.getLanguage().equals("el")) {
			return this.name;
		}
		else {
			if (altName != null && !altName.isEmpty()) {
				return altName;
			}
			else {
				return name;
			}
		}
	}
	@SuppressWarnings("unused")
	public String getAltName() {
		if (altName != null && !altName.isEmpty()) {
			return altName;
		}
		else {
			return name;
		}
	}
	@SuppressWarnings("unused")
	public List<String> getUserCourses() {
		if (userCourses == null) {
			userCourses = new ArrayList<>();
		}
		return userCourses;
	}
	@SuppressWarnings("unused")
	public OpUserProfile getUserProfile() {
		if (userProfile == null) {
			userProfile = new OpUserProfile();
		}
		return userProfile;
	}

}
