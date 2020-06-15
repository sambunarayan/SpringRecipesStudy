package com.apress.springrecipes.domain;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Reservation {

	@NotNull
	@Size(min=4)
	private String courtName;
	@NotNull
	private Date date;
	@Min(9)
	@Max(21)
	private int hour;
	@Valid
	private Player player;
	@NotNull
	private SportType sportType;
	
	public Reservation() {}
	
	public Reservation(String courtName, Date date, int hour, Player player, SportType sportType) {
		this.courtName = courtName;
		this.date = date;
		this.hour = hour;
		this.player = player;
		this.sportType = sportType;
	}

	public String getCourtName() {
		return courtName;
	}

	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date	 date) {
		this.date = date;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public SportType getSportType() {
		return sportType;
	}

	public void setSportType(SportType sportType) {
		this.sportType = sportType;
	}
}
