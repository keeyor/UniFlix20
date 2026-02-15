package com.uniflix.model.security;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserRights implements Serializable {

    protected Boolean isSa;
    protected List<CoursePermission> coursePermissions;
    protected List<EventPermission> eventPermissions;
    protected List<UnitPermission> unitPermissions;

    @Getter
    @Setter
    public static class CoursePermission implements Serializable {
        protected String courseId;
        protected String staffMemberId;
        protected boolean contentManager;
        protected boolean scheduleManager;
    }

    @Getter
    @Setter
    public static class EventPermission implements Serializable {
        protected String eventId;
        protected String staffMemberId;
        protected boolean contentManager;
        protected boolean scheduleManager;
    }

    @Getter
    @Setter
    public static class UnitPermission implements Serializable {
        protected String unitId;
        protected boolean contentManager;
        protected boolean dataManager;
        protected boolean scheduleManager;
    }

    @SuppressWarnings("unused")
    public List<CoursePermission> getCoursePermissions() {
        if (coursePermissions == null) {
            coursePermissions = new ArrayList<>();
        }
        return coursePermissions;
    }
    @SuppressWarnings("unused")
    public List<EventPermission> getEventPermission() {
        if (eventPermissions == null) {
            eventPermissions = new ArrayList<>();
        }
        return eventPermissions;
    }
    @SuppressWarnings("unused")
    public List<UnitPermission> getUnitPermissions() {
        if (unitPermissions == null) {
            unitPermissions = new ArrayList<>();
        }
        return unitPermissions;
    }

}