package br.com.santander.cef.analysis.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.santander.cef.analysis.helper.InitHelper;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerViewModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5540428777439474286L;
	
	@Field("customer_code")
	private String customerCode = InitHelper.stringType();
	@Field("document_type")
	private String documentType = InitHelper.stringType();
	@Field("document_number")
	private String documentNumber = InitHelper.stringType();
	private String segment = InitHelper.stringType();
	private List<PhoneViewModel> phones = new ArrayList<>();
	private List<AccountViewModel> accounts = new ArrayList<>();
	private List<CardViewModel> cards = new ArrayList<>();
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getDocumentNumber() {
		return documentNumber;
	}
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
	public List<PhoneViewModel> getPhones() {
		return phones;
	}
	public void setPhones(List<PhoneViewModel> phones) {
		this.phones = phones;
	}
	public List<AccountViewModel> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<AccountViewModel> accounts) {
		this.accounts = accounts;
	}
	public List<CardViewModel> getCards() {
		return cards;
	}
	public void setCards(List<CardViewModel> cards) {
		this.cards = cards;
	}
}
