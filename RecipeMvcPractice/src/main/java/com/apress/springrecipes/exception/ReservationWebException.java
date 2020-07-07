package com.apress.springrecipes.exception;

import java.util.Date;

public class ReservationWebException extends RuntimeException {

	private static final long serialVersionUID = -6587128906056492647L;

	private String msg;
	private Date date;
	private String detail;

	public ReservationWebException(String msg, Date date, String detail) {
		this.msg = msg;
		this.date = date;
		this.detail = detail;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
