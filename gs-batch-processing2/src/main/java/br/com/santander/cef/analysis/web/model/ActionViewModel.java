package br.com.santander.cef.analysis.web.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.santander.cef.analysis.helper.InitHelper;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActionViewModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9206098902886528779L;
	private String actionId = InitHelper.stringType();
	private String status = InitHelper.stringType();
	
	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
