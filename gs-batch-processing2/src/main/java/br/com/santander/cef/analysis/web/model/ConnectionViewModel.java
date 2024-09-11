package br.com.santander.cef.analysis.web.model;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.santander.cef.analysis.helper.InitHelper;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectionViewModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3946976755243770421L;
	@Field("ip_address")
	private String ipAddress = InitHelper.stringType();
	private String usual = InitHelper.stringType();
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getUsual() {
		return usual;
	}
	public void setUsual(String usual) {
		this.usual = usual;
	}
}
