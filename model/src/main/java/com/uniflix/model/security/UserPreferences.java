package com.uniflix.model.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPreferences {

    private boolean record = false;
    private boolean broadcast = true;
    private AccessOptions accessType = AccessOptions.SSO;
    private PublishOptions publish = PublishOptions.PRIVATE;

}
