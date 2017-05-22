package com.hastane.entity;

public class Hasta {

	private int hastaId;
	private String hastaAdi;
	private String hastaSoyadi;
	private String email;
	private String telefonNumarasi;
	private String tcKimlik;
	private String cinsiyet;

	public int getHastaId() {
		return hastaId;
	}

	public void setHastaId(int hastaId) {
		this.hastaId = hastaId;
	}

	public String getHastaAdi() {
		return hastaAdi;
	}

	public void setHastaAdi(String hastaAdi) {
		this.hastaAdi = hastaAdi;
	}

	public String getHastaSoyadi() {
		return hastaSoyadi;
	}

	public void setHastaSoyadi(String hastaSoyadi) {
		this.hastaSoyadi = hastaSoyadi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefonNumarasi() {
		return telefonNumarasi;
	}

	public void setTelefonNumarasi(String telefonNumarasi) {
		this.telefonNumarasi = telefonNumarasi;
	}

	public String getTcKimlik() {
		return tcKimlik;
	}

	public void setTcKimlik(String tcKimlik) {
		this.tcKimlik = tcKimlik;
	}

	public String getCinsiyet() {
		return cinsiyet;
	}

	public void setCinsiyet(String cinsiyet) {
		this.cinsiyet = cinsiyet;
	}

	public Hasta(int hastaId, String hastaAdi, String hastaSoyadi, String email, String telefonNumarasi,
			String tcKimlik, String cinsiyet) {
		super();
		this.hastaId = hastaId;
		this.hastaAdi = hastaAdi;
		this.hastaSoyadi = hastaSoyadi;
		this.email = email;
		this.telefonNumarasi = telefonNumarasi;
		this.tcKimlik = tcKimlik;
		this.cinsiyet = cinsiyet;
	}

	public Hasta() {
		super();
	}

	public Hasta(int hastaId) {
		super();
		this.hastaId = hastaId;
	}

	@Override
	public String toString() {
		return "HastaId: " + hastaId + " " + "HastaAdi: " + hastaAdi + " " + "HastaSoyadi: " + hastaSoyadi + " "
				+ "HastaEmail: " + email + " " + "HastaTelefonNumarasý: " + telefonNumarasi + " " + "HastaTcKimlik: "
				+ tcKimlik + " " + "HastaCinsiyeti: " + cinsiyet;
	}

}
