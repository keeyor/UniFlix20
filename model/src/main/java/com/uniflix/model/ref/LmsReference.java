package com.uniflix.model.ref;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LmsReference implements Serializable {

		protected String lmsId;
		protected String lmsCode;
}