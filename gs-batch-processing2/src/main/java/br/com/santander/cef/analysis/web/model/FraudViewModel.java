package br.com.santander.cef.analysis.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FraudViewModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1585498216494446623L;
	private List<PhoneViewModel> phones = new ArrayList<>();
	private List<ConnectionViewModel> connections = new ArrayList<>();
	public List<PhoneViewModel> getPhones() {
		return phones;
	}
	public void setPhones(List<PhoneViewModel> phones) {
		this.phones = phones;
	}
	public List<ConnectionViewModel> getConnections() {
		return connections;
	}
	public void setConnections(List<ConnectionViewModel> connections) {
		this.connections = connections;
	}
}
