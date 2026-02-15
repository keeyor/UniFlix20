package com.uniflix.model.calendar;

import com.uniflix.model.ref.StructureType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Holidays implements Serializable {

    protected String refId;
    protected String id;
    protected List<Holiday> holidayList;

    public List<Holiday> getHolidayList() {
        if (holidayList == null) {
            holidayList = new ArrayList<>();
        }
        return holidayList;
    }

    @Getter
    @Setter
    public static class Holiday implements Serializable {

        protected String name;
        protected String startDate;
        protected String endDate;
        protected StructureType structureType;

    }
}
