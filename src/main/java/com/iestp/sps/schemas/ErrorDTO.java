package com.iestp.sps.schemas;

public class ErrorDTO {
	private String detail;

	public ErrorDTO(String detail) {
		this.detail = detail;
	}

	public String getDetail() { return detail; }
}
