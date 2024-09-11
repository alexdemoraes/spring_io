package br.com.santander.cef.analysis.web.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.santander.cef.analysis.helper.InitHelper;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldViewModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5432249531437726753L;
	private String name = InitHelper.stringType();
	private String value = InitHelper.stringType();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
