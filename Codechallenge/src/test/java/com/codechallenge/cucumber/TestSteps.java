package com.codechallenge.cucumber;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;

import com.codechallenge.TransactionsApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration
@SpringBootTest(classes = TransactionsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestSteps {

	@Autowired
	private TestRestTemplate restTemplate;	

	private String reference = null;
	private String date = null;
	private String amount = null;
	private String fee = null;
	private String description = null;
	private String iban = null;
	private String sort = null;
	private String error = null;
	

	private String transactionResultAsJsonStr = null;

	private final ObjectMapper objectMapper = new ObjectMapper();
	private JsonNode root;

	@Given("^A transaction with reference (.*)")
	public void aTransNotStored(final String reference) {
		this.reference = reference;
	}

	@When("^I check the status from (.*)")
	public void whenICheckStatus(final String channel)
			throws JSONException, JsonMappingException, JsonProcessingException {

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		final JSONObject transactionJsonObject = new JSONObject();
		transactionJsonObject.put("reference", this.reference);
		transactionJsonObject.put("channel", channel);

		final HttpEntity<String> request = new HttpEntity<>(transactionJsonObject.toString(), headers);

		this.transactionResultAsJsonStr = this.restTemplate.postForObject("/status", request, String.class);
		this.root = this.objectMapper.readTree(this.transactionResultAsJsonStr);

	}

	@Then("^The system returns the status (.*)")
	public void thSystemReturnsStatus(final String status) throws Throwable {

		Assert.assertEquals(status, this.root.path("status").asText());
	}

	@And("^The system returns amount (.*)")
	public void thSystemReturnsAmount(final String amount) throws Throwable {

		Assert.assertEquals(amount, this.root.path("amount").asText());
	}

	@And("^The system returns fee (.*)")
	public void thSystemReturnsFee(final String fee) throws Throwable {
		Assert.assertEquals(fee, this.root.path("fee").asText());
	}

	// Create

	@Given("^reference (.*), account iban (.*), date (.*), amount (.*), fee (.*) and description (.*)$")
	public void reference_iban_ES_date_amount_fee_and_description(final String reference, final String iban,
			final String date, final String amount, final String fee, final String description) throws Throwable {

		this.setReference(reference);
		this.setIban(iban);
		this.setDate(date);
		this.setAmount(amount);
		this.setFee(fee);
		this.setDescription(description);

	}

	@When("^I call create$")
	public void i_call_create() throws Throwable {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		final JSONObject transactionJsonObject = new JSONObject();

		transactionJsonObject.put("reference", this.getReference());
		transactionJsonObject.put("account_iban", this.getIban());
		transactionJsonObject.put("date", this.getDate());
		transactionJsonObject.put("amount", this.getAmount());
		transactionJsonObject.put("fee", this.getFee());
		transactionJsonObject.put("description", this.getDescription());

		final HttpEntity<String> request = new HttpEntity<>(transactionJsonObject.toString(), headers);

		this.transactionResultAsJsonStr = this.restTemplate.postForObject("/", request, String.class);
		this.root = this.objectMapper.readTree(this.transactionResultAsJsonStr);
	}

	@Then("^The system returns the reference of the newly created transaction$")
	public void the_system_returns_the_reference_of_the_newly_created_transaction() throws Throwable {
		this.setReference(this.root.path("reference").asText());
		Assert.assertTrue(this.reference != null && !this.reference.equals(""));
	}
	
	@Then("^The system returns the specific exception$")
	public void the_system_returns_the_specific_exception() throws Throwable {	
		JsonNode errors = this.root.path("errors");
		StringBuilder errorBuilder = new StringBuilder();
		if(errors != null && errors.isArray()) {
			for (JsonNode error : errors) {
				errorBuilder.append(error.asText());
			}
		}
		this.setError(errorBuilder.toString());
		Assert.assertTrue(this.error != null && !this.error.equals(""));
	}


	// Search

	@Given("^An IBAN (.*) and sort (.*)$")
	public void an_IBAN_ES_and_sort_asc(final String iban, final String sort) throws Throwable {

		this.setIban(iban);
		this.setSort(sort);

	}

	@When("^I search$")
	public void i_search() throws Throwable {

		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		final JSONObject transactionJsonObject = new JSONObject();

		transactionJsonObject.put("iban", this.getIban());
		transactionJsonObject.put("sort", this.getSort());

		final HttpEntity<String> request = new HttpEntity<>(transactionJsonObject.toString(), headers);

		this.transactionResultAsJsonStr = this.restTemplate.postForObject("/search", request, String.class);
		// root = objectMapper.readTree(transactionResultAsJsonStr);
	}

	@Then("^I receive a list of transactions for that IBAN$")
	public void i_receive_a_list_of_transactions_for_that_IBAN() throws Throwable {

		final JSONArray transactionsList = new JSONArray(this.transactionResultAsJsonStr);

		Assert.assertTrue(transactionsList.length() > 0);
	}

	public TestRestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(TestRestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getTransactionResultAsJsonStr() {
		return transactionResultAsJsonStr;
	}

	public void setTransactionResultAsJsonStr(String transactionResultAsJsonStr) {
		this.transactionResultAsJsonStr = transactionResultAsJsonStr;
	}

	public JsonNode getRoot() {
		return root;
	}

	public void setRoot(JsonNode root) {
		this.root = root;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}