package com.hastane.entity;

import java.util.Date;

public class Randevu {

	private int randevuId;
	private Date date;
	private Doktor doktor;
	private Hasta hasta;

	public int getRandevuId() {
		return randevuId;
	}

	public void setRandevuId(int randevuId) {
		this.randevuId = randevuId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Doktor getDoktor() {
		return doktor;
	}

	public void setDoktor(Doktor doktor) {
		this.doktor = doktor;
	}

	public Hasta getHasta() {
		return hasta;
	}

	public void setHasta(Hasta hasta) {
		this.hasta = hasta;
	}

	public Randevu(int randevuId) {
		super();
		this.randevuId = randevuId;
	}

	public Randevu() {
		super();
	}

	public Randevu(int randevuId, Date date, Doktor doktor, Hasta hasta) {
		super();
		this.randevuId = randevuId;
		this.date = date;
		this.doktor = doktor;
		this.hasta = hasta;
	}

	public String toString() {
		return "RandevuId: " + randevuId + "RandevuTarihi: " + date + "Doktor: " + doktor + "Hasta: " + hasta;
	}

}
