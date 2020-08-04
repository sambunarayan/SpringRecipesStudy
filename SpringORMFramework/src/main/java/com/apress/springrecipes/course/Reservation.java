package com.apress.springrecipes.course;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name= "RESERVATION")
public class Reservation {
	
	@Id
	@SequenceGenerator(name="reserv_generator", sequenceName="reservation_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="reserv_generator")
	@Column(name="ID")
	private Long id;
	@Column(name="CUSTOMER", length=20, nullable=false)
	private String customer;
	@Column(name="FROM_DATE")
	private Date fromDate;
	@Column(name="TO_DATE")
	private Date toDate;
	@Column(name="AMOUNT")
	private int amount;
	
	public Reservation() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "["
				+ id + ", "
				+ customer + ", "
				+ fromDate + ", "
				+ toDate + ", "
				+ amount + "]";
	}
}
