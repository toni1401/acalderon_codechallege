package com.codechallenge.models;

import org.springframework.data.domain.Sort.Direction;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Transaction Search Request", description = "Optional params to search transactions")
public class SearchRequest {

	@JsonProperty("account_iban")
	@ApiModelProperty(value = "(Optional) Filter by IBAN", required = false)
	private String iban;

	@ApiModelProperty(value = "(Optional) Sort by amount (ascending/descending), defaults to ASC", required = false, allowableValues = "ASC,DESC")
	private Direction sort;

	public SearchRequest() {
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public Direction getSort() {
		return sort;
	}

	public void setSort(Direction sort) {
		this.sort = sort;
	}
}
