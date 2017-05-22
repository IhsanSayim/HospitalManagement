package com.hastane.entity;

public class Ilac {
	private String ilacAdi;
	private int miktar;
	private String kod;

	private Hasta hasta;
	private Doktor doktor;

	public Ilac(String ilacAdi, int miktar, String kod, Hasta hasta) {
		super();
		this.ilacAdi = ilacAdi;
		this.miktar = miktar;
		this.kod = kod;
		this.hasta = hasta;
	}

	public Ilac(String ilacAdi, int miktar, String kod, Doktor doktor) {
		super();
		this.ilacAdi = ilacAdi;
		this.miktar = miktar;
		this.kod = kod;
		this.doktor = doktor;
	}

	public Hasta getHasta() {
		return hasta;
	}

	public void setHasta(Hasta hasta) {
		this.hasta = hasta;
	}

	public Doktor getDoktor() {
		return doktor;
	}

	public void setDoktor(Doktor doktor) {
		this.doktor = doktor;
	}

	public int getMiktar() {
		return miktar;
	}

	public void setMiktar(int miktar) {
		this.miktar = miktar;
	}

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public String getIlacAdi() {
		return ilacAdi;
	}

	public void setIlacAdi(String ilacAdi) {
		this.ilacAdi = ilacAdi;
	}

	public Ilac(String ilacAdi, int miktar, String kod) {
		super();
		this.ilacAdi = ilacAdi;
		this.miktar = miktar;
		this.kod = kod;
	}

	public Ilac() {
		super();
	}

	public Ilac(String ilacAdi) {
		super();
		this.ilacAdi = ilacAdi;
	}

	@Override
	public String toString() {
		return "Ýlac adi: " + ilacAdi + "Ýlac miktari: " + miktar + "Ýlac kodu: " + kod;
	}

}
