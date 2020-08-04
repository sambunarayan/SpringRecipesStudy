package com.apress.springrecipes.course;

import java.util.Date;

public class Course {
	private Long id;
	private String title;
	private Date beginDate;
	private Date endDate;
	private int fee;

	public Course() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}
	
	@Override
	public String toString() {
		return "["
				+ id + ", "
				+ title + ", "
				+ beginDate + ", "
				+ endDate + ", "
				+ fee + "]";
	}
}
