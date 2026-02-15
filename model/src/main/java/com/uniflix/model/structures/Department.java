package com.uniflix.model.structures;


import com.uniflix.model.calendar.Holidays;
import com.uniflix.model.calendar.Periods;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Document(collection = "Departments")
@Getter
@Setter
public class Department implements Serializable {

    @Id
    protected String id;

    protected String title;
    protected String titleEn;
    @Indexed(direction = IndexDirection.ASCENDING)
    protected String identity;
    protected String url;
    protected String logoUrl;
    protected String password;
    @Indexed(direction = IndexDirection.ASCENDING)
    private String institutionId;
    @Indexed(direction = IndexDirection.ASCENDING)
    private String schoolId;
    @Indexed(direction = IndexDirection.ASCENDING)
    private List<String> classrooms;
    private List<Periods.Period> periodList;
    private List<Holidays.Holiday> holidayList;

    @SuppressWarnings("unused")
    public List<String> getClassrooms() {
        if (classrooms == null) {
            classrooms = new ArrayList<>();
        }
        return classrooms;
    }
    @SuppressWarnings("unused")
    public List<Periods.Period> getPeriodList() {
        if (periodList == null) {
            periodList = new ArrayList<>();
        }
        return periodList;
    }
    @SuppressWarnings("unused")
    public List<Holidays.Holiday> getHolidayList() {
        if (holidayList == null) {
            holidayList = new ArrayList<>();
        }
        return holidayList;
    }
    @SuppressWarnings("unused")
    public String getTitle(Locale locale) {
        if (locale.getLanguage().equals("el")) {
            return this.getTitle();
        }
        else {
            if (titleEn != null && !titleEn.isEmpty()) {
                return this.getTitleEn();
            }
            else {
                return title;
            }
        }
    }
}

