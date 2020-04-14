package com.codechallenge.domains;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

import com.codechallenge.serialization.MoneySerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "transaction_7075931734841441869")
@ApiModel(value = "Transaction", description = "A bank transaction")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 7075931734841441869L;

	@Id
	private String reference;

	@NotNull
	@NaturalId
	@JsonProperty("account_iban")
	private String iban;

	private ZonedDateTime date;

	@NotNull
	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal amount;

	@JsonSerialize(using = MoneySerializer.class)
	private BigDecimal fee;

	private String description;

	public Transaction() {
	}

	public Transaction(String iban, BigDecimal amount) {
		this.iban = iban;
		this.amount = amount;
	}

	public Transaction(String reference, String iban, ZonedDateTime date, BigDecimal amount, BigDecimal fee,
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
