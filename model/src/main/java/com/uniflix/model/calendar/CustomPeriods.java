package com.uniflix.model.calendar;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

@Getter
@Setter
public class CustomPeriods implements java.io.Serializable {

    @Indexed(direction = IndexDirection.ASCENDING)
    protected String year;
    protected Periods periods;
    protected boolean inherited;

}
