package com.codechallenge.domains;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "account_7695333200152491336")
@ApiModel(value = "Account", description = "A bank account")
public class Account implements Serializable {
	private static final long serialVersionUID = 7695333200152491336L;

	@Id
	private String iban;

	private BigDecimal amount;

	public Account() {
	}

	public Account(String iban, BigDecimal amount) {
		this.iban = iban;
		this.amount = amount;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
