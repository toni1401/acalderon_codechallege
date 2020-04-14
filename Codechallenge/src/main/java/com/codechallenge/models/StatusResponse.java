package com.codechallenge.models;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.codechallenge.models.enums.TransactionStatus;
import com.codechallenge.serialization.MoneySerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NonNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Transaction Status", description = "Status and additional information for a specific transaction")
public class StatusResponse {

	@NonNull
	@ApiModelProperty(value = "The transaction reference number")
	private String reference;

	@ApiModelProperty(value = "The status of the transaction", allowableValues = "PENDING,SETTLED,FUTURE,INVALID")
	private TransactionStatus status;

	@JsonSerialize(using = MoneySerializer.class)
	@ApiModelProperty(value = "The amount of the transaction")
	private BigDecimal amount;

	@JsonSerialize(using = MoneySerializer.class)
	@ApiModelProperty(value = "The fee applied to the transaction")
	private BigDecimal fee;

	public StatusResponse() {
	}

	public StatusResponse(String reference) {
		this.reference = reference;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(TransactionStatus status) {
		this.status = status;
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
}
