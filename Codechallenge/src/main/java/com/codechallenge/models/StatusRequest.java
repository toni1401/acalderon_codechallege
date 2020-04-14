package com.codechallenge.models;

import com.codechallenge.models.enums.TransactionChannel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Transaction Status Request", description = "Params to get a transaction status")
public class StatusRequest {
	@ApiModelProperty(value = "The transaction reference number")
	private String reference;

	@ApiModelProperty(value = "(Optional) The type of the channel that is asking for the status. Defaults to CLIENT", allowableValues = "CLIENT, ATM, INTERNAL", required = false)
	private TransactionChannel channel;

	public StatusRequest() {
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public TransactionChannel getChannel() {
		return channel;
	}

	public void setChannel(TransactionChannel channel) {
		this.channel = channel;
	}
}
