package br.com.santander.cef.analysis.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionViewModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1356467328258050608L;
	private String id;
	private List<ActionViewModel> actions = new ArrayList<>();
	private List<FieldViewModel> fields = new ArrayList<>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<FieldViewModel> getFields() {
		return fields;
	}
	public void setFields(List<FieldViewModel> fields) {
		this.fields = fields;
	}
	public List<ActionViewModel> getActions() {
		return actions;
	}
	public void setActions(List<ActionViewModel> actions) {
		this.actions = actions;
	}
}
