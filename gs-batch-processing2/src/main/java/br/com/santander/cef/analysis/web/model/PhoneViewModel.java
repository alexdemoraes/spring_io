package br.com.santander.cef.analysis.web.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.santander.cef.analysis.helper.InitHelper;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneViewModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2843085747122533065L;

	private String ddd = InitHelper.stringType();
	private String number = InitHelper.stringType();
	@Field("class")
	@JsonProperty(value="class")
	private String classe = InitHelper.stringType();
	private String type = InitHelper.stringType();
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
