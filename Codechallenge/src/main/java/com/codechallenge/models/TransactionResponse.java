package com.codechallenge.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Transaction Response", description = "Response from a created transaction")
public class TransactionResponse {
	@ApiModelProperty(value = "The transaction unique reference number")
	private String reference;

	public TransactionResponse() {
	}

	public TransactionResponse(String reference) {
		this.reference = reference;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}
}
