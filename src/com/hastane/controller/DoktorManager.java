package com.hastane.controller;

import java.util.ArrayList;
import java.util.List;

import com.hastane.entity.Doktor;

import Interfaces.Interface2;

public class DoktorManager implements Interface2 {

	private List<Doktor> listofDoktor;

	public DoktorManager() {
		this.listofDoktor = new ArrayList<>();
	}

	public DoktorManager(List<Doktor> listofDoktor) {
		this.listofDoktor = listofDoktor;
	}

	public List<Doktor> getListOfDoktor() {
		return listofDoktor;
	}

	public int addDoktor(Doktor d) {
		this.listofDoktor.add(d);
		return this.listofDoktor.size();
	}

	public int count() {
		return this.listofDoktor.size();
	}

	public Doktor getDoktor(int index) {
		if (index < 0 || index >= count()) {
			return null;
		}
		return this.listofDoktor.get(index);
	}

	public Doktor doktorBul(String ad) {

		int index = -1;
		for (int i = 0, n = count(); i < n; i++) {
			if (this.listofDoktor.get(i).getDoktorAdi().equals(ad)) {
				index = i;
				break;
			}
		}

		return this.listofDoktor.get(index);
	}

	public boolean removeDoktor(int id) {
		int index = -1;
		for (int i = 0, n = count(); i < n; i++) {
			if (this.listofDoktor.get(i).getDoktorId() == id) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			this.listofDoktor.remove(index);
			return true;
		}
		return false;

	}

}
