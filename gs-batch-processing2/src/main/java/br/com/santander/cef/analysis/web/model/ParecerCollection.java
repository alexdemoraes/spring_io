package br.com.santander.cef.analysis.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.santander.cef.analysis.helper.InitHelper;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "analise")
public class ParecerCollection implements Serializable {

	private static final long serialVersionUID = -1217562036778551299L;

	@Id
	private String id = InitHelper.stringType();
	private String area = InitHelper.stringType();
	@Field("queue_code")
	private String queueCode = InitHelper.stringType();
	private CustomerViewModel customer = new CustomerViewModel();
	private List<TransactionViewModel> transactions = new ArrayList<>();
	@Field("customer_calls")
	private List<CustomerCallViewModel> customerCalls = new ArrayList<>();
	private FraudViewModel fraud = new FraudViewModel();
	private List<ItemViewModel> questionnaire = new ArrayList<>();
	private String responseCode = InitHelper.stringType();
	private String modalityCode = InitHelper.stringType();
	private String resultCode = InitHelper.stringType();
	private AccountViewModel account = new AccountViewModel();
	private CardViewModel card = new CardViewModel();
	private String date = InitHelper.stringType();
	private String notes = InitHelper.stringType();
	private String people = InitHelper.stringType();
	private String user = InitHelper.stringType();
	@Field("pega_case_id")
	private String pegaCaseId = InitHelper.stringType();
	@Field("wrote_legacy_db")
	private Boolean wroteLegacyDb = InitHelper.booleanType();
	private String duration = InitHelper.stringType();
	private String notes1 = InitHelper.stringType();
	private String notes2 = InitHelper.stringType();
	private String notes3 = InitHelper.stringType();
	private List<ActionViewModel> actions = new ArrayList<>();
	private Date creationDate = InitHelper.dateType();
	private Date updateDate = InitHelper.dateType();
	private Boolean cancellationOfBiometrics;
	private String dateBiometricsCancellation;
	
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getQueueCode() {
		return queueCode;
	}
	public void setQueueCode(String queueCode) {
		this.queueCode = queueCode;
	}
	public CustomerViewModel getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerViewModel customer) {
		this.customer = customer;
	}
	public List<TransactionViewModel> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<TransactionViewModel> transactions) {
		this.transactions = transactions;
	}
	public List<CustomerCallViewModel> getCustomerCalls() {
		return customerCalls;
	}
	public void setCustomerCalls(List<CustomerCallViewModel> customerCalls) {
		this.customerCalls = customerCalls;
	}
	public FraudViewModel getFraud() {
		return fraud;
	}
	public void setFraud(FraudViewModel fraud) {
		this.fraud = fraud;
	}
	public List<ItemViewModel> getQuestionnaire() {
		return questionnaire;
	}
	public void setQuestionnaire(List<ItemViewModel> questionnaire) {
		this.questionnaire = questionnaire;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getModalityCode() {
		return modalityCode;
	}
	public void setModalityCode(String modalityCode) {
		this.modalityCode = modalityCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public AccountViewModel getAccount() {
		return account;
	}
	public void setAccount(AccountViewModel account) {
		this.account = account;
	}
	public CardViewModel getCard() {
		return card;
	}
	public void setCard(CardViewModel card) {
		this.card = card;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPegaCaseId() {
		return pegaCaseId;
	}
	public void setPegaCaseId(String pegaCaseId) {
		this.pegaCaseId = pegaCaseId;
	}
	public Boolean getWroteLegacyDb() {
		return wroteLegacyDb;
	}
	public void setWroteLegacyDb(Boolean wroteLegacyDb) {
		this.wroteLegacyDb = wroteLegacyDb;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getNotes1() {
		return notes1;
	}
	public void setNotes1(String notes1) {
		this.notes1 = notes1;
	}
	public String getNotes2() {
		return notes2;
	}
	public void setNotes2(String notes2) {
		this.notes2 = notes2;
	}
	public String getNotes3() {
		return notes3;
	}
	public void setNotes3(String notes3) {
		this.notes3 = notes3;
	}
	public List<ActionViewModel> getActions() {
		return actions;
	}
	public void setActions(List<ActionViewModel> actions) {
		this.actions = actions;
	}
	public Boolean getCancellationOfBiometrics() {
		return cancellationOfBiometrics;
	}
	public void setCancellationOfBiometrics(Boolean cancellationOfBiometrics) {
		this.cancellationOfBiometrics = cancellationOfBiometrics;
	}
	public String getDateBiometricsCancellation() {
		return dateBiometricsCancellation;
	}
	public void setDateBiometricsCancellation(String dateBiometricsCancellation) {
		this.dateBiometricsCancellation = dateBiometricsCancellation;
	}
}
