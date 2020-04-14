package com.codechallenge.models;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Transaction", description = "A bank transaction")
public class TransactionRequest {
	@ApiModelProperty(value = "(Optional) The transaction unique reference number in our system. If not present, the system will generate one", required = false)
	private String reference;

	@JsonProperty("account_iban")
	@ApiModelProperty(value = "The IBAN number of the account where the transaction has happened")
	private String iban;

	@ApiModelProperty(value = "(Optional) Date when the transaction took place", required = false)
	private ZonedDateTime date;

	@ApiModelProperty(value = "If positive the transaction is a credit (add money) to the account. If negative it is a debit (deduct money from the account)")
	private BigDecimal amount;

	@ApiModelProperty(value = "(Optional) Fee that will be deducted from the amount, regardless on the amount being positive or negative", required = false)
	private BigDecimal fee;

	@ApiModelProperty(value = "(Optional) The description of the transaction", required = false)
	private String description;

	public TransactionRequest() {
	}

	public TransactionRequest(String reference, String iban, ZonedDateTime date, BigDecimal amount, BigDecimal fee,
			String description) {
		this.reference = reference;
		this.iban = iban;
		this.date = date;
		this.amount = amount;
		this.fee = fee;
		this.description = description;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public ZonedDateTime getDate() {
		return date;
	}

	public void setDate(ZonedDateTime date) {
		this.date = date;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
