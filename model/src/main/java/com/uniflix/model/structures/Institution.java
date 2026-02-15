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


@Document(collection = "Institutions")
@Getter
@Setter
public class Institution implements Serializable {
    @Id
    private String id;

    @Indexed(direction = IndexDirection.ASCENDING)
    private String identity;
    private String title;
    private String titleEn;
    private String url;
    private NOC noc;
    private String logoUrl;
    private Administrator administrator;
    private String organizationLicense;
    private List<Periods.Period> periodList;
    private List<Holidays.Holiday> holidayList;

    public Institution() {}

    public List<Periods.Period> getPeriodList() {
        if (periodList == null) {
            periodList = new ArrayList<>();
        }
        return periodList;
    }
    public List<Holidays.Holiday> getHolidayList() {
        if (holidayList == null) {
            holidayList = new ArrayList<>();
        }
        return holidayList;
    }
    @Getter
    @Setter
    public static class Administrator implements Serializable{

        private String name = "UNKNOWN";
        private String email = "UNKNOWN";
        private List<String> telephone = new ArrayList<>();
    }

    @Getter
    @Setter
    public static class NOC implements Serializable{

        private String title = "UNKNOWN";
        private String title_en = "UNKNOWN";
        private String url = "UNKNOWN";
        private String email = "UNKNOWN";

    }
    @SuppressWarnings("unused")
    public String getTitle(Locale locale) {
        if (locale.getLanguage().equals("el")) {
            return this.title;
        }
        else {
            if (titleEn != null && !titleEn.isEmpty()) {
                return titleEn;
            }
            else {
                return title;
            }
        }
    }

}
