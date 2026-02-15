package com.uniflix.model.calendar;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Periods implements Serializable {

    protected String refId;
    protected String id;
    protected String inherit;
    protected List<Period> periodList;

    @SuppressWarnings("unused")
    public List<Period> getPeriodList() {
        if (periodList == null) {
            periodList = new ArrayList<>();
        }
        return this.periodList;
    }

    @Getter
    @Setter
    public static class Period implements Serializable {

        protected String name;
        protected String startDate;
        protected String endDate;

    }
}
