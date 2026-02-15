package com.uniflix.model.calendar;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

@Getter
@Setter
public class CustomPauses implements java.io.Serializable {

    @Indexed(direction = IndexDirection.ASCENDING)
    protected String year;
    protected Holidays holidays;
}
