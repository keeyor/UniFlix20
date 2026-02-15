package com.uniflix.model.resources;


import com.uniflix.model.ref.LmsReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Locale;


@Document(collection = "Courses")
@Getter
@Setter
public class Course implements Serializable {

    @Id
    protected String id;
    @TextIndexed(weight=5)
    protected String title;
    @TextIndexed(weight=5)
    protected String titleEn;

    protected String description;
    protected String descriptionEn;

    @Indexed(direction = IndexDirection.ASCENDING)
    protected String identity;
    protected String[] scopeId;
    @Indexed(direction = IndexDirection.ASCENDING)
    private String institutionId;
    @Indexed(direction = IndexDirection.ASCENDING)
    private String schoolId;
    @Indexed(direction = IndexDirection.ASCENDING)
    protected String departmentId;
    protected String[] departmentsRelated;

    @Indexed(direction = IndexDirection.ASCENDING)
    protected List<String> studyProgramIds;

    protected String[] semester;
    protected String[] categories;
    protected boolean inactive;

    protected List<LmsReference> lmsReferences;

    protected Instant dateModified;
    protected String editor;

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

