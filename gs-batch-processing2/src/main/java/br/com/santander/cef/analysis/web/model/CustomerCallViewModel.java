package br.com.santander.cef.analysis.web.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.santander.cef.analysis.helper.InitHelper;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerCallViewModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4412830652208677179L;
	private PhoneViewModel phone = new PhoneViewModel();
	private String result = InitHelper.stringType();
	private String notes = InitHelper.stringType();
	public PhoneViewModel getPhone() {
		return phone;
	}
	public void setPhone(PhoneViewModel phone) {
		this.phone = phone;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

}
