package com.uniflix.model.ref;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OpUserProfile implements Serializable {

	protected String lastKnownRole;
	protected List<String> knownRoles;

	protected Boolean onRequestUpdateEmail;
	protected Boolean onRequestUpdateNote;

	protected Boolean onScheduleUpdateEmail;
	protected Boolean onScheduleUpdateNote;

	protected Boolean onResourceUpdateEmail;
	protected Boolean onResourceUpdateNote;

	protected Boolean ManagerRequestNotifications;
	protected List<String> favoriteServices;

	protected List<String> exportResources;

	@SuppressWarnings("unused")
	public List<String> getKnownRoles() {
		if (knownRoles == null) {
			knownRoles = new ArrayList<>();
		}
		return knownRoles;
	}
	@SuppressWarnings("unused")
	public List<String> getFavoriteServices() {
		if (favoriteServices == null) {
			favoriteServices = new ArrayList<>();
		}
		return favoriteServices;
	}
	@SuppressWarnings("unused")
	public List<String> getExportResources() {
		if (exportResources == null) {
			exportResources = new ArrayList<>();
		}
		return exportResources;
	}
	@SuppressWarnings("unused")
	public Boolean getOnRequestUpdateEmail() {
		if (onRequestUpdateEmail == null) {
			onRequestUpdateEmail = true;
		}
		return onRequestUpdateEmail;
	}
	@SuppressWarnings("unused")
	public Boolean getOnRequestUpdateNote() {
		if (onRequestUpdateNote == null) {
			onRequestUpdateNote = true;
		}
		return onRequestUpdateNote;
	}
	@SuppressWarnings("unused")
	public Boolean getOnScheduleUpdateEmail() {
		if (onScheduleUpdateEmail == null) {
			onScheduleUpdateEmail = true;
		}
		return onScheduleUpdateEmail;
	}
	@SuppressWarnings("unused")
	public Boolean getOnScheduleUpdateNote() {
		if (onScheduleUpdateNote == null) {
			onScheduleUpdateNote = true;
		}
		return onScheduleUpdateNote;
	}
	@SuppressWarnings("unused")
	public Boolean getOnResourceUpdateEmail() {
		if (onResourceUpdateEmail == null) {
			onResourceUpdateEmail = true;
		}
		return onResourceUpdateEmail;
	}
	@SuppressWarnings("unused")
	public Boolean getOnResourceUpdateNote() {
		if (onResourceUpdateNote == null) {
			onResourceUpdateNote = true;
		}
		return onResourceUpdateNote;
	}

}
