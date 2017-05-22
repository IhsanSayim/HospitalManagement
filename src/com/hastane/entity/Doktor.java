package com.hastane.entity;

import com.hastane.myenum.DoktorTipi;

public class Doktor {
	private int doktorId;
	private String doktorAdi;
	private String doktorSoyadi;
	private int maas;
	private DoktorTipi doktorTipi;

	public int getDoktorId() {
		return doktorId;
	}

	public void setDoktorId(int doktorId) {
		this.doktorId = doktorId;
	}

	public String getDoktorAdi() {
		return doktorAdi;
	}

	public void setDoktorAdi(String doktorAdi) {
		this.doktorAdi = doktorAdi;
	}

	public int getMaas() {
		return maas;
	}

	public void setMaas(int maas) {
		this.maas = maas;
	}

	public DoktorTipi getDoktorTipi() {
		return doktorTipi;
	}

	public void setDoktorTipi(DoktorTipi doktorTipi) {
		this.doktorTipi = doktorTipi;
	}

	public String getDoktorSoyadi() {
		return doktorSoyadi;
	}

	public void setDoktorSoyadi(String doktorSoyadi) {
		this.doktorSoyadi = doktorSoyadi;
	}

	public Doktor(int doktorId, String doktorAdi, String doktorSoyadi, int maas, DoktorTipi doktorTipi) {
		super();
		this.doktorId = doktorId;
		this.doktorAdi = doktorAdi;
		this.doktorSoyadi = doktorSoyadi;
		this.maas = maas;
		this.doktorTipi = doktorTipi;
	}

	public Doktor() {
		super();
	}

	public Doktor(int doktorId) {
		super();
		this.doktorId = doktorId;
	}

	@Override
	public String toString() {
		return "DoktorId: " + doktorId + " " + "DoktorAdi: " + doktorAdi + " " + "DoktorSoyadi: " + doktorSoyadi + " "
				+ "DoktorMaasi: " + maas + " " + "DoktorTipi: " + doktorTipi;
	}

}
