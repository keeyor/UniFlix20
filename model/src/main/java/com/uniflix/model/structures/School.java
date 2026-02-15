package com.uniflix.model.structures;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Locale;

@Document(collection = "Schools")
@Getter
@Setter
public class School implements Serializable {

    @Id
    private String id;

    @Indexed(direction = IndexDirection.ASCENDING)
    private String identity;
    private String title;
    private String titleEn;

    @SuppressWarnings("unused")
    public String getTitle(Locale locale) {
        if (locale.getLanguage().equals("el")) {
            return this.getTitle();
        }
        else {
            if (this.getTitleEn() != null && !this.getTitleEn().isEmpty()) {
                return this.getTitleEn();
            }
            else {
                return this.getTitle();
            }
        }
    }
}

